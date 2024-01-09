package com.zhangz.demo.spring.cloud.platform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.constant.GoodsInfoEnum;
import com.zhangz.demo.spring.cloud.platform.dao.GoodInfoMapper;
import com.zhangz.demo.spring.cloud.platform.dto.GoodsInfoDTO;
import com.zhangz.demo.spring.cloud.platform.dto.PropertyDTO;
import com.zhangz.demo.spring.cloud.platform.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.platform.service.GoodInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
        Long id = goodsInfoDTO.getId();
        GoodInfo goodInfo = BeanUtil.copyProperties(goodsInfoDTO, GoodInfo.class);
        String properties = goodsInfoDTO.getProperties();
        JSONArray objects = JSONArray.parseArray(properties);
        if (StringUtils.isNotEmpty(properties)) {

            Set<String> set = new HashSet<>();
            for (Object object : objects) {
                for (Object o : (JSONArray)object) {
                    if (o instanceof JSONObject) {
                        JSONObject item = (JSONObject)o;
                        String[] propIds = StringUtils.split(item.getString("propIds"), ",");
                        set.addAll(Arrays.stream(propIds).collect(Collectors.toSet()));
                        log.info(JSONObject.toJSONString(item));
                    }
                }

            }
            goodInfo.setPropertyIds(StringUtils.join(set, ","));

        }
        goodInfo.setDateAdd(DateUtil.formatDateTime(new Date()));
        goodInfo.setRecommendStatusStr("普通");
        goodInfo.setStatusStr(GoodsInfoEnum.getDescByState(goodInfo.getStatus()));

        if (null != id) {
            goodInfo.setId(id);
            goodInfoMapper.updateById(goodInfo);
        } else {
            long maxId = goodInfoMapper.getMaxId();
            goodInfo.setId(maxId + 1);
            goodInfoMapper.insert(goodInfo);
        }
    }

    @Override
    public GoodsInfoDTO goodsDetailById(Long id) throws BussinessException {

        if (null == id) {
            throw new BussinessException("参数不能为空");
        }
        GoodInfo goodInfo = goodInfoMapper.selectById(id);
        GoodsInfoDTO goodsInfoDTO = BeanUtil.copyProperties(goodInfo, GoodsInfoDTO.class);
        return goodsInfoDTO;
    }

}
