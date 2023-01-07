package com.github.zshine.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.constant.Constants;
import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.dao.UserDao;
import com.github.zshine.auth.dao.UserJoinRoleDao;
import com.github.zshine.auth.domain.User;
import com.github.zshine.auth.domain.UserJoinRole;
import com.github.zshine.auth.service.UserService;
import com.github.zshine.common.exception.BusinessException;
import com.github.zshine.common.valid.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserJoinRoleDao userJoinRoleDao;


    @Override
    public void login(String username, String password) {

        try {
            User user = this.getAndCheckNullByUsername(username);
            if (user.getStatus().equals(StatusEnum.FAIL)) {
                throw new BusinessException("账户不可用");
            }
            if (!Objects.equals(user.getPassword(), DigestUtils.md5DigestAsHex((password + user.getRandom()).getBytes(StandardCharsets.UTF_8)))) {
                throw new BusinessException("");
            }
            StpUtil.login(username);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        } catch (Exception e) {
            throw new BusinessException("用户名或密码错误");
        }

    }

    @Override
    public Page<User> page(Integer page, Integer limit, Integer status) {
        Page<User> pageData = userDao.page(new Page<>(page, limit), Wrappers.<User>lambdaQuery().eq(!ObjectUtils.isEmpty(status), User::getStatus, status));
        //查询相关用户和角色的映射
        Map<String, List<Integer>> usernameRoleIdsMap = this.getUsernameRoleIdsMapByUsernames(pageData.getRecords()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList()));
        //构建用户的角色
        pageData.setRecords(pageData.getRecords().stream()
                .peek(user -> user.setRoleIds(usernameRoleIdsMap.getOrDefault(user.getUsername(), new ArrayList<>())))
                .collect(Collectors.toList()));
        return pageData;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String username) {
        User user = this.getAndCheckNullByUsername(username);
        if (user.getSupper().equals(StatusEnum.EFFECT)) {
            throw new BusinessException("不能删除超户");
        }
        userDao.removeById(username);
        this.deleteRoleIdsByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(User user) {
        this.checkExistByUsername(user.getUsername());
        String password = DigestUtils.md5DigestAsHex((user.getPassword() + user.getRandom()).getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);
        userDao.save(user);
        this.addRoleIds(user.getUsername(), user.getRoleIds());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(User user) {
        this.getAndCheckNullByUsername(user.getUsername());
        userDao.updateById(user);
        this.deleteRoleIdsByUsername(user.getUsername());
        this.addRoleIds(user.getUsername(), user.getRoleIds());
    }

    /**
     * 查询并校验用户是否为空，为空则抛出异常
     */
    private User getAndCheckNullByUsername(String username) {
        User user = userDao.getById(username);
        AssertUtil.notNull(user, Constants.NULL_ERROR_MESSAGE);
        return user;
    }

    /**
     * 查校验用户是否存在，不存在则抛出异常
     */
    private void checkExistByUsername(String username) {
        AssertUtil.mustNull(userDao.getById(username), Constants.EXIST_ERROR_MESSAGE);
    }

    private Map<String, List<Integer>> getUsernameRoleIdsMapByUsernames(List<String> usernames) {
        if (!ObjectUtils.isEmpty(usernames)) {
            return userJoinRoleDao.list(Wrappers.<UserJoinRole>lambdaQuery()
                            .in(UserJoinRole::getUsername, usernames))
                    .stream()
                    .collect(Collectors.groupingBy(UserJoinRole::getUsername))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, stringListEntry -> stringListEntry.getValue()
                            .stream()
                            .map(UserJoinRole::getRoleId)
                            .collect(Collectors.toList())));
        }
        return new HashMap<>(1);
    }

    /**
     * 删除用户角色
     */
    private void deleteRoleIdsByUsername(String username) {
        userJoinRoleDao.remove(Wrappers.<UserJoinRole>lambdaQuery().eq(UserJoinRole::getUsername, username));
    }

    /**
     * 新增用户角色
     */
    private void addRoleIds(String username, List<Integer> roleIds) {
        if (!ObjectUtils.isEmpty(roleIds)) {
            userJoinRoleDao.saveBatch(roleIds.stream().map(roleId -> UserJoinRole.getInstance(username, roleId)).collect(Collectors.toList()));
        }
    }


}
