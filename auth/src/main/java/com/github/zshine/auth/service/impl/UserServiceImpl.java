package com.github.zshine.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.constant.Constants;
import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.dao.UserDao;
import com.github.zshine.auth.domain.User;
import com.github.zshine.auth.service.UserService;
import com.github.zshine.common.exception.BusinessException;
import com.github.zshine.common.valid.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public Page<User> page(Integer page, Integer limit, Integer status) {
        return userDao.page(new Page<>(page, limit), Wrappers.<User>lambdaQuery()
                .eq(!ObjectUtils.isEmpty(status), User::getStatus, status));
    }


    @Override
    public void delete(String username) {
        User user = this.getAndCheckNullByUsername(username);
        if (user.getSupper().equals(StatusEnum.EFFECT)) {
            throw new BusinessException("不能删除超户");
        }
        userDao.removeById(username);
    }

    @Override
    public void add(User user) {
        this.checkExistByUsername(user.getUsername());
        String password = DigestUtils.md5DigestAsHex((user.getPassword() + user.getRandom()).getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public void edit(User user) {
        this.getAndCheckNullByUsername(user.getUsername());
        userDao.updateById(user);
    }

    private User getAndCheckNullByUsername(String username) {
        User user = userDao.getById(username);
        AssertUtil.notNull(user, Constants.NULL_ERROR_MESSAGE);
        return user;
    }

    private void checkExistByUsername(String username) {
        AssertUtil.mustNull(userDao.getById(username), Constants.EXIST_ERROR_MESSAGE);
    }
}
