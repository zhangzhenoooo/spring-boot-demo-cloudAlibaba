package com.zhangz.demo.spring.cloud.platform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.platform.dto.DeleteGoodsDTO;
import com.zhangz.demo.spring.cloud.platform.dto.GoodsInfoDTO;
import com.zhangz.demo.spring.cloud.platform.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.platform.service.GoodInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/goods")
@Api(tags = "商品管理")
public class GoodsController {

    @Resource
    private GoodInfoService goodInfoService;

    @ApiOperation(value = "商品列表", notes = "商品列表")
    @PostMapping("/list")
    @ResponseBody
    public CommonResult list(String goodsName, String categoryName, Integer page, Integer pageSize) {
    
        CommonPage commonPage = goodInfoService.listByNameCategoryName(goodsName, categoryName, page, pageSize);
        return CommonResult.success(commonPage);
    }

    @ApiOperation(value = "删除商品", notes = "删除商品")
    @GetMapping("/delete")
    @ResponseBody
    public CommonResult delete(Long id) {
        log.info("删除商品，商品id：{}", id);
        goodInfoService.removeById(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "新增商品", notes = "新增商品")
    @PostMapping("/add")
    @ResponseBody
    public CommonResult add(GoodsInfoDTO goodsInfoDTO) {
        log.info("新增商品，商品信息：{}", JSON.toJSONString(goodsInfoDTO));
        goodInfoService.add(goodsInfoDTO);
        return CommonResult.success();
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @PostMapping("/deleteBatch")
    @ResponseBody
    public CommonResult deleteBatch(@RequestBody DeleteGoodsDTO dto) {
        log.info("批量删除：{}", JSON.toJSONString(dto));
        goodInfoService.removeByIds(dto.getIds());
         return CommonResult.success();
    }
}
