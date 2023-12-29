package com.zhangz.demo.spring.cloud.platform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.platform.dao.GoodInfoMapper;
import com.zhangz.demo.spring.cloud.platform.dto.GoodsInfoDTO;
import com.zhangz.demo.spring.cloud.platform.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.platform.service.GoodInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodInfoServiceImpl
 * 
 * @Date：2023/12/12 17:42
 * 
 * @Filename：GoodInfoServiceImpl
 */
@Service
@Slf4j
public class GoodInfoServiceImpl extends ServiceImpl<GoodInfoMapper, GoodInfo> implements GoodInfoService {
    @Resource
    private GoodInfoMapper goodInfoMapper;

    @Override
    public CommonPage listByNameCategoryName(String goodsName, String categoryName, Integer page, Integer pageSize) {
        Page p = new Page();
        p.setSize(pageSize);
        p.setCurrent(page);
        String categoryId = null;
        List<GoodInfo> goodInfoList = goodInfoMapper.listByNameCategoryName(goodsName, categoryId, p);
        CommonPage commonPage = new CommonPage();
        commonPage.setList(goodInfoList);
        commonPage.setPageNum(page);
        commonPage.setTotalPage(p.getTotal());
        return commonPage;
    }

    @Override
    public void add(GoodsInfoDTO goodsInfoDTO) {
        GoodInfo goodInfo = BeanUtil.copyProperties(goodsInfoDTO, GoodInfo.class);
        long maxId = goodInfoMapper.getMaxId();
        goodInfo.setId(maxId + 1);
        goodInfoMapper.insert(goodInfo);
    }

}
