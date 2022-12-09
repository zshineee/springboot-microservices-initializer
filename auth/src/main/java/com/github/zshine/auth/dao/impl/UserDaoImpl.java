package com.github.zshine.auth.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zshine.auth.dao.UserDao;
import com.github.zshine.auth.domain.User;
import com.github.zshine.auth.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {

}
