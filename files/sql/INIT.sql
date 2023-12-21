
 

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
CREATE TABLE IF NOT EXISTS `order_info` (
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `create_time` VARCHAR(255) NOT NULL,
    `remark` TEXT,
    `ordered` BOOLEAN NOT NULL,
    `ordered_time` BOOLEAN NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- 订单明细表
DROP TABLE IF EXISTS `order_good`;
CREATE TABLE IF NOT EXISTS `order_good` (
    `id` VARCHAR(255) NOT NULL PRIMARY KEY,
    `order_id` VARCHAR(255) NOT NULL,
    `good_id` BIGINT NOT NULL,
    `number` INT NOT NULL,
    `sku` TEXT,
    `price` INT NOT NULL COMMENT '价格 = number * goods unit price',
    `create_time` VARCHAR(255) NOT NULL,
    `remark` TEXT
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;