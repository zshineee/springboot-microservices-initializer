package com.github.zshine.auth.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zshine.auth.dao.UserJoinRoleDao;
import com.github.zshine.auth.domain.UserJoinRole;
import com.github.zshine.auth.mapper.UserJoinRoleMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserJoinRoleDaoImpl extends ServiceImpl<UserJoinRoleMapper, UserJoinRole> implements UserJoinRoleDao {

}
