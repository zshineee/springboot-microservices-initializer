package com.github.zshine.auth.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@TableName(value = "resource")
public class Resource {

    @TableId
    @ApiModelProperty(notes = "id")
    private String id;

    @ApiModelProperty(notes = "父节点")
    private String parentId;

    @ApiModelProperty(notes = "名称")
    private String name;

    @ApiModelProperty(notes = "url")
    private String url;

    @ApiModelProperty(notes = "优先级")
    private Integer priority;

    @ApiModelProperty(notes = "子节点")
    @TableField(exist = false)
    private List<Resource> children;
}
