package com.github.zshine.auth.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zshine.auth.dao.RoleDao;
import com.github.zshine.auth.domain.Role;
import com.github.zshine.auth.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends ServiceImpl<RoleMapper, Role> implements RoleDao {

}
