package com.github.zshine.auth.controller;

import com.github.zshine.auth.domain.Resource;
import com.github.zshine.auth.service.ResourceService;
import com.github.zshine.common.response.FormJsonRsp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "资源管理")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/resource")
@Validated
public class ResourceController {


    @javax.annotation.Resource
    private ResourceService resourceService;

    @ApiOperation(value = "查询")
    @GetMapping("/list")
    public FormJsonRsp<List<Resource>> list() {
        return FormJsonRsp.ok(resourceService.generateTree());
    }

}
