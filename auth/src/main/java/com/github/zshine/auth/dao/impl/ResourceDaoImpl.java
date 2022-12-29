package com.github.zshine.auth.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zshine.auth.dao.ResourceDao;
import com.github.zshine.auth.domain.Resource;
import com.github.zshine.auth.mapper.ResourceMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceDaoImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceDao {

}
