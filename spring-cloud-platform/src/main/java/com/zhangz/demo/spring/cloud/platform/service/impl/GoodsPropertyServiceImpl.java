package com.zhangz.demo.spring.cloud.platform.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dao.GoodsPropertyMapper;
import com.zhangz.demo.spring.cloud.platform.entity.GoodsProperty;
import com.zhangz.demo.spring.cloud.platform.service.GoodsPropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.platform.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodsPropertyServiceImpl
 * 
 * @Date：2024/1/2 16:49
 * 
 * @Filename：GoodsPropertyServiceImpl
 */
@Service
@Slf4j
public class GoodsPropertyServiceImpl extends ServiceImpl<GoodsPropertyMapper, GoodsProperty> implements GoodsPropertyService {

    @Resource
    private GoodsPropertyMapper goodsPropertyMapper;

    @Override
    public List<GoodsProperty> listByPropertyId(Integer propertyId) {
        return goodsPropertyMapper.listByPropertyId(propertyId);
    }

    @Override
    public void addOrUpdate(Long id, String name, Integer parentPropertyId) throws BussinessException {

        GoodsProperty goodsProperty = null;
        if (null == id) {
            if (null == parentPropertyId) {
                throw new BussinessException("parentPropertyId不能为空");
            }
            goodsProperty = new GoodsProperty();
            goodsProperty.setId(goodsPropertyMapper.getMaxId() + 1);
            goodsProperty.setPropertyId(parentPropertyId);
            goodsProperty.setName(name);
            goodsProperty.setPaixu(0);
            goodsProperty.setPropertyId(parentPropertyId);
            goodsProperty.setUserId(27);
            goodsProperty.setDateAdd(DateUtil.formatDateTime(new Date()));
            goodsPropertyMapper.insert(goodsProperty);
        } else {
            goodsProperty = goodsPropertyMapper.selectById(id);
            goodsProperty.setName(name);
            goodsPropertyMapper.updateById(goodsProperty);
        }
    }

}
