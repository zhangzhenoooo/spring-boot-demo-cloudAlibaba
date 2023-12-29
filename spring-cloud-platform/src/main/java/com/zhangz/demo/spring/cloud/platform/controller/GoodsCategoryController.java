package com.zhangz.demo.spring.cloud.platform.controller;

import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.entity.GoodsCategory;
import com.zhangz.demo.spring.cloud.platform.service.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
@RequestMapping("/api/goods/category")
@Api(tags = "商品分类")
public class GoodsCategoryController {

    @Resource
    private GoodsCategoryService goodsCategoryService;

    @ApiOperation(value = "商品分类列表", notes = "商品分类列表")
    @PostMapping("/list")
    @ResponseBody
    public CommonResult list(Integer shopid, String id, String categoryName, Integer page, Integer pageSize) {

        CommonPage<GoodsCategory> commonPage = goodsCategoryService.listByShopIdAndIdName(shopid, id, categoryName, page, pageSize);
        return CommonResult.success(commonPage);
    }

    @ApiOperation(value = "删除商品分类", notes = "删除商品分类")
    @GetMapping("/delete")
    @ResponseBody
    public CommonResult delete(Long id) throws BussinessException {
        log.info("删除商品分类，商品分类id：{}", id);
        if (null == id) {
            throw new BussinessException("参数不能为空");
        }
        goodsCategoryService.removeById(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除全部商品分类", notes = "删除全部商品分类")
    @GetMapping("/deleteAll")
    @ResponseBody
    public CommonResult removeAllByShopId(Long shopid) throws BussinessException {
        log.info("删除全部商品分类，商品分类shopid：{}", shopid);
        goodsCategoryService.removeAllByShopId(shopid);
        return CommonResult.success();
    }

    @ApiOperation(value = "保存或者修改", notes = "保存或者修改")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public CommonResult saveOrUpdate(Long id, String name, Long shopid) throws BussinessException {
        goodsCategoryService.insertOrUpdate(id, name, shopid);
        return CommonResult.success();
    }

}
