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
values ('admin', '982050fab9a36cb3fc3882ee9d306d11', 1, 1, '管理员', '715d886a-113b-43f2-b05a-aa9c00a10624', null)