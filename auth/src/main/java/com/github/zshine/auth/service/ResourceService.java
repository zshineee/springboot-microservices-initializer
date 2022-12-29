package com.github.zshine.auth.service;


import com.github.zshine.auth.domain.Resource;

import java.util.List;

public interface ResourceService {


    /**
     * 根据父节点构建树
     *
     * @return tree
     */
    List<Resource> generateTree();
}
