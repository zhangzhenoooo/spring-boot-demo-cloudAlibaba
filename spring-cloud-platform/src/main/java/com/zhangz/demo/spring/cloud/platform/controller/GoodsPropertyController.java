package com.zhangz.demo.spring.cloud.platform.controller;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dto.GoodsInfoDTO;
import com.zhangz.demo.spring.cloud.platform.entity.GoodsProperty;
import com.zhangz.demo.spring.cloud.platform.service.GoodInfoService;
import com.zhangz.demo.spring.cloud.platform.service.GoodsPropertyService;
import com.zhangz.demo.spring.cloud.platform.vo.GoodsPropertyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.springcloudplatform.controller
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodsController
 * 
 * @Date：2023/12/28 11:03
 * 
 * @Filename：GoodsController
 */
@RestController
@Slf4j
@RequestMapping("/api/goods/property")
@Api(tags = "商品分类管理")
public class GoodsPropertyController {

    @Resource
    private GoodsPropertyService goodsPropertyService;

    @ApiOperation(value = "商品规格列表", notes = "商品规格列表")
    @GetMapping("/list")
    @ResponseBody
    public CommonResult list(Integer propertyId) {
        List<GoodsProperty> list = goodsPropertyService.listByPropertyId(propertyId);
        return CommonResult.success(list);
    }
    

    @ApiOperation(value = "新增|修改商品规格", notes = "新增|修改商品规格")
    @PostMapping("/save")
    @ResponseBody
    public CommonResult saveOrUpdate(String id, String name,Integer parentPropertyId) throws BussinessException {
        log.info("新增|修改商品，商品信息：id : {},name :{}", id, name);
        Long idd = StringUtils.isEmpty(id)|| "null".equals(id) ? null : Long.parseLong(id);
        goodsPropertyService.addOrUpdate(idd,name,parentPropertyId);
        return CommonResult.success();
    } 
    @ApiOperation(value = "删除商品规格", notes = "删除商品规格")
    @GetMapping("/delete")
    @ResponseBody
    public CommonResult delete(Long id) throws BussinessException {
        log.info("删除改商品，商品信息：id : {}", id);
        goodsPropertyService.removeById(id);
        return CommonResult.success();
    }
    
    @ApiOperation(value = "商品规格树", notes = "商品规格树")
    @GetMapping("/tree")
    @ResponseBody
    public CommonResult tree(String name) {
        List<GoodsPropertyVO> list = goodsPropertyService.tree(name);
        return CommonResult.success(list);
    }
}
