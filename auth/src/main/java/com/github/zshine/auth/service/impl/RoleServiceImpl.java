package com.github.zshine.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.constant.Constants;
import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.dao.RoleDao;
import com.github.zshine.auth.dao.RoleJoinResourceDao;
import com.github.zshine.auth.domain.Role;
import com.github.zshine.auth.domain.RoleJoinResource;
import com.github.zshine.auth.service.RoleService;
import com.github.zshine.common.valid.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private RoleJoinResourceDao roleJoinResourceDao;


    @Override
    public Page<Role> page(Integer page, Integer limit, Integer status) {
        return roleDao.page(new Page<>(page, limit), Wrappers.<Role>lambdaQuery()
                .eq(!ObjectUtils.isEmpty(status), Role::getStatus, status));
    }

    @Override
    public Map<Integer, String> getRoleIdRoleNameMap() {
        return roleDao.list()
                .stream()
                .collect(Collectors.toMap(Role::getId, Role::getName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        roleDao.removeById(id);
        roleJoinResourceDao.remove(Wrappers.<RoleJoinResource>lambdaQuery()
                .eq(RoleJoinResource::getRoleId, id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(String name, List<String> resourceIds, Integer status) {
        Role role = Role.getInstance(name, status);
        roleDao.save(role);
        this.addResourceIds(role.getId(), resourceIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(Integer roleId, String name, List<String> resourceIds, Integer status) {
        Role role;
        AssertUtil.notNull((role = roleDao.getById(roleId)), Constants.NULL_ERROR_MESSAGE);
        role.setName(name);
        role.setStatus(StatusEnum.getInstance(status));
        roleDao.updateById(role);
        roleJoinResourceDao.remove(Wrappers.<RoleJoinResource>lambdaQuery()
                .eq(RoleJoinResource::getRoleId, roleId));
        this.addResourceIds(role.getId(), resourceIds);
    }


    private void addResourceIds(Integer roleId, List<String> resourceIds) {
        if (!ObjectUtils.isEmpty(resourceIds)) {
            roleJoinResourceDao.saveBatch(resourceIds.stream()
                    .map(resourceId -> RoleJoinResource.getInstance(roleId, resourceId))
                    .collect(Collectors.toList()));
        }
    }
}
