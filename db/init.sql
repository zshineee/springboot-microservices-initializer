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