-- 网关路由表
DROP TABLE IF EXISTS gateway_route;
CREATE TABLE gateway_route
(
    id          VARCHAR(10) PRIMARY KEY COMMENT 'id',
    uri         VARCHAR(512) NOT NULL COMMENT 'uri路径',
    predicates  VARCHAR(512) NOT NULL COMMENT '判定器',
    filters     VARCHAR(512) NOT NULL COMMENT '过滤器',
    `order`     INT     DEFAULT 0 COMMENT '排序',
    description VARCHAR(512) COMMENT '说明',
    status      TINYINT DEFAULT 0 COMMENT '状态：1-有效，0-无效'
) COMMENT '网关路由表';