package com.zhangz.demo.spring.cloud.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.product.dao.GoodPropertyMapper;
import com.zhangz.demo.spring.cloud.product.dto.GoodPropertyDTO;
import com.zhangz.demo.spring.cloud.product.entity.GoodProperty;
import com.zhangz.demo.spring.cloud.product.service.GoodPropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodPropertyServiceImpl
 * 
 * @Date：2023/12/13 9:55
 * 
 * @Filename：GoodPropertyServiceImpl
 */
@Service
@Slf4j
public class GoodPropertyServiceImpl extends ServiceImpl<GoodPropertyMapper, GoodProperty> implements GoodPropertyService {

    @Resource
    private GoodPropertyMapper goodPropertyMapper;

    @Override
    public Set<GoodPropertyDTO> queryBypropertyIds(Set<String> ids) {
        Set<GoodPropertyDTO> list = new HashSet<>();

        Collection<GoodProperty> goodProperties = this.listByIds(ids);
        for (GoodProperty goodProperty : goodProperties) {
            GoodPropertyDTO dto = BeanUtil.copyProperties(goodProperty, GoodPropertyDTO.class);
            Set<GoodProperty> childsCurGoods = queryByPropertyId(goodProperty.getPropertyId());
            dto.setChildsCurGoods(childsCurGoods);
            list.add(dto);
        }
        return list;
    }

    @Override
    public Set<GoodProperty> queryByPropertyId(int propertyId) {
        Set<GoodProperty> list = goodPropertyMapper.queryByPropertyId(propertyId);
        return list;
    }
}
