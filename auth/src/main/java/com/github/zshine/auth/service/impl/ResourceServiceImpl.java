package com.github.zshine.auth.service.impl;


import com.github.zshine.auth.constant.Constants;
import com.github.zshine.auth.dao.ResourceDao;
import com.github.zshine.auth.domain.Resource;
import com.github.zshine.auth.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {


    @javax.annotation.Resource
    private ResourceDao resourceDao;


    @Override
    public List<Resource> generateTree() {
        List<Resource> resources = resourceDao.list();

        //根据父节点分组
        Map<String, List<Resource>> parentGroupMap = resources
                .stream()
                .sorted(Comparator.comparing(Resource::getPriority))
                .collect(Collectors.groupingBy(Resource::getParentId));


        return this.getChild(Constants.ROOT_NODE, parentGroupMap);
    }

    /**
     * 获取当前节点的子节点
     */
    private List<Resource> getChild(String id, Map<String, List<Resource>> parentGroupMap) {
        return parentGroupMap.getOrDefault(id, new ArrayList<>())
                .stream()
                .peek(resource -> resource.setChildren(this.getChild(resource.getId(), parentGroupMap)))
                .collect(Collectors.toList());
    }
}
