
 

CREATE TABLE  IF NOT EXISTS good_category (
                                  id BIGINT(20) PRIMARY KEY,
                                  isUse BOOLEAN,
                                  level INT(11),
                                  name VARCHAR(255),
                                  paixu INT(11),
                                  pid INT(11),
                                  shop_id INT(11),
                                  user_id INT(11)
);
 

CREATE TABLE IF NOT EXISTS good_info (
                            after_sale VARCHAR(255),
                            category_id BIGINT(20),
                            commission INT(11),
                            commission_settle_type INT(11),
                            commission_type INT(11),
                            commission_user_type INT(11),
                            date_add VARCHAR(255),
                            date_update VARCHAR(255),
                            fx_type INT(11),
                            got_score INT(11),
                            got_score_type INT(11),
                            has_addition BOOLEAN,
                            has_tour_journey BOOLEAN,
                            hidden INT(11),
                            id BIGINT(20),
                            kanjia BOOLEAN,
                            kanjia_price INT(11),
                            limitation BOOLEAN,
                            logistics_id BIGINT(20),
                            max_coupons INT(11),
                            miaosha BOOLEAN,
                            min_buy_number INT(11),
                            min_price INT(11),
                            min_score INT(11),
                            name VARCHAR(255),
                            number_fav INT(11),
                            number_good_reputation INT(11),
                            number_orders INT(11),
                            number_reputation INT(11),
                            number_sells INT(11),
                            original_price INT(11),
                            overseas BOOLEAN,
                            paixu INT(11),
                            persion INT(11),
                            pic VARCHAR(255),
                            pingtuan BOOLEAN,
                            pingtuan_price INT(11),
                            property_ids VARCHAR(255),
                            recommend_status INT(11),
                            recommend_status_str VARCHAR(255),
                            seckill_buy_number INT(11),
                            sell_end BOOLEAN,
                            sell_start BOOLEAN,
                            shop_id INT(11),
                            status INT(11),
                            status_str VARCHAR(255),
                            store_alert BOOLEAN,
                            stores BIGINT(20),
                            stores0_unsale BOOLEAN,
                            stores_ext1 INT(11),
                            stores_ext2 INT(11),
                            type INT(11),
                            unit VARCHAR(255),
                            unuseful_number INT(11),
                            useful_number INT(11),
                            user_id INT(11),
                            vet_status INT(11),
                            views INT(11),
                            weight INT(11)
);

ALTER TABLE good_info ADD content varchar(1000) NULL COMMENT '详细介绍';


CREATE TABLE IF NOT EXISTS notice (
                        id INT(11) PRIMARY KEY,
                        content VARCHAR(255),
                        date_add DATE,
                        is_remind BOOLEAN,
                        is_show BOOLEAN,
                        remind_uid INT(11),
                        title VARCHAR(255),
                        user_id INT(11)
);

CREATE TABLE IF NOT EXISTS shop_info (
    activity VARCHAR(255),
    address VARCHAR(255),
    bind_uid BIGINT,
    characteristic VARCHAR(255),
    city_id VARCHAR(255),
    cy_table_pay_mod INT,
    date_add DATETIME,
    date_update DATETIME,
    express_type VARCHAR(255),
    goods_need_check BOOLEAN,
    id BIGINT PRIMARY KEY,
    introduce TEXT,
    latitude DECIMAL(9,6),
    link_phone VARCHAR(255),
    link_phone_show BOOLEAN,
    longitude DECIMAL(9,6),
    name VARCHAR(255),
    number VARCHAR(255),
    number_fav INT,
    number_good_reputation INT,
    number_order INT,
    number_reputation INT,
    open_scan BOOLEAN,
    open_waimai BOOLEAN,
    open_ziqu BOOLEAN,
    opening_hours VARCHAR(255),
    paixu INT,
    pic TEXT,
    province_id VARCHAR(255),
    recommend_status INT,
    service_amount_min INT,
    service_distance INT,
    status INT,
    status_str VARCHAR(255),
    tax_gst INT,
    tax_service INT,
    type VARCHAR(255),
    user_id INT,
    work_status INT
    );

-- 商品属性表
CREATE TABLE IF NOT EXISTS good_property (
    date_add VARCHAR(255),
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    paixu INT,
    property_id INT,
    user_id INT
    );

-- 订单表
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
                              `id` varchar(36) NOT NULL,
                              `user_id` varchar(36) NOT NULL,
                              `create_time` varchar(255) DEFAULT NULL,
                              `remark` varchar(255) DEFAULT NULL,
                              `order_status` int(11) DEFAULT NULL,
                              `ordered_time` varchar(255) DEFAULT NULL,
                              `pay_time` varchar(255) DEFAULT NULL,
                              `amount` decimal(18,2) DEFAULT NULL,
                              `amount_real` decimal(18,2) DEFAULT NULL,
                              `goods_number` int(11) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 

-- 订单明细表
DROP TABLE IF EXISTS `order_good`;
CREATE TABLE IF NOT EXISTS `order_good` (
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `order_id` VARCHAR(255) NOT NULL,
    `good_id` BIGINT NOT NULL,
    `number` INT NOT NULL,
    `sku` TEXT,
    `price` decimal(18,2) NOT NULL COMMENT '价格 = number * goods unit price',
    `create_time` VARCHAR(255) NOT NULL,
    `remark` TEXT,
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 
ALTER TABLE order_good ADD status TINYINT DEFAULT 0 NOT NULL COMMENT '菜品状态 0 :未下单，1:已经下单,2：制作中，3：已上菜，4 :已支付';

-- 用户表

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE IF NOT EXISTS `user_info` (
    `user_id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `user_name` VARCHAR(255) NOT NULL,
    `phone`  VARCHAR(15) NOT NULL,
    `create_time` VARCHAR(255) NOT NULL,
    `remark` VARCHAR(500) ,
    `status` tinyint NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE user_info ADD password varchar(100) NOT NULL;
ALTER TABLE user_info ADD openid varchar(100) NULL COMMENT '微信openid';
ALTER TABLE user_info ADD nick varchar(100) NULL COMMENT '昵称';
ALTER TABLE user_info CHANGE nick nick varchar(100) NULL COMMENT '昵称' AFTER user_name;
ALTER TABLE user_info ADD avatar_url varchar(100) NULL COMMENT '头像地址';
ALTER TABLE curry.user_info ADD `level` TINYINT DEFAULT 0 NOT NULL COMMENT '用户等级 0：普通用户，1 : vip ,2:vvip , 3 :vvvip , 4: 大爷，5;上帝';
