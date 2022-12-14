-- 网关路由表
DROP TABLE IF EXISTS gateway_route;
CREATE TABLE gateway_route
(
    id          VARCHAR(10) PRIMARY KEY COMMENT 'id',
    uri         VARCHAR(512) NOT NULL COMMENT 'uri路径',
    predicates  VARCHAR(512) NOT NULL COMMENT '判定器',
    filters     VARCHAR(512) NOT NULL COMMENT '过滤器',
    orders      INT     DEFAULT 0 COMMENT '排序',
    description VARCHAR(512) COMMENT '说明',
    status      TINYINT DEFAULT 0 COMMENT '状态：1-有效，0-无效'
) COMMENT '网关路由表';
INSERT INTO gateway_route(id, uri, predicates, filters, orders, description, status)
values ('auth', 'lb://auth', '[{"name":"Path","args":{"pattern":"/auth/**"}}]', '[]', 1, '权限路由', 1);

-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    username   VARCHAR(32) PRIMARY KEY COMMENT '用户名',
    `password` VARCHAR(32) NOT NULL COMMENT '密码',
    supper     TINYINT DEFAULT 0 COMMENT '是否为超户：1-是，0-否',
    `status`   TINYINT DEFAULT 0 COMMENT '状态：1-有效，0-无效',
    fullname   VARCHAR(64) NOT NULL COMMENT '全称',
    `random`   VARCHAR(64) NOT NULL COMMENT '随机数',
    remark     VARCHAR(512) COMMENT '说明'
) COMMENT '用户表';
INSERT INTO user(username, password, supper, status, fullname, random, remark)
values ('admin', '982050fab9a36cb3fc3882ee9d306d11', 1, 1, '管理员', '715d886a-113b-43f2-b05a-aa9c00a10624', null);

-- 资源表
DROP TABLE IF EXISTS resource;
CREATE TABLE resource
(
    id        VARCHAR(32) PRIMARY KEY comment 'id',
    parent_id VARCHAR(32)  NOT NULL COMMENT '父节点',
    `name`    VARCHAR(32)  NOT NULL COMMENT '名称',
    url       VARCHAR(255) NOT NULL COMMENT 'url',
    priority  int(4) DEFAULT 0 comment '优先级'
) comment '资源表';

-- 角色表
DROP TABLE IF EXISTS role;
CREATE TABLE role
(
    id       int(4) PRIMARY KEY AUTO_INCREMENT comment 'id',
    `name`   VARCHAR(32) NOT NULL COMMENT '名称',
    `status` TINYINT DEFAULT 0 COMMENT '状态：1-有效，0-无效'
) comment '角色表';
-- 角色与资源关联表
DROP TABLE IF EXISTS role_join_resource;
CREATE TABLE role_join_resource
(
    role_id     int(4)      NOT NULL comment '角色ID',
    resource_id VARCHAR(32) NOT NULL COMMENT '资源ID',
    PRIMARY KEY (role_id, resource_id)
) comment '角色与资源关联表';
-- 用户与角色关联表
DROP TABLE IF EXISTS user_join_role;
CREATE TABLE user_join_role
(
    username VARCHAR(32) COMMENT '用户名',
    role_id  int(4) NOT NULL comment '角色ID',
    PRIMARY KEY (username, role_id)
) comment '用户与角色关联表';