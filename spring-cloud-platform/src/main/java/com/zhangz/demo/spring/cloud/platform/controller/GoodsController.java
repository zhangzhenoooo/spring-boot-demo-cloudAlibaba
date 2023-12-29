package com.zhangz.demo.spring.cloud.platform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.platform.dto.GoodsInfoDTO;
import com.zhangz.demo.spring.cloud.platform.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.platform.service.GoodInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/goods")
@Api(tags = "商品管理")
public class GoodsController {

    @Resource
    private GoodInfoService goodInfoService;

    @ApiOperation(value = "商品列表", notes = "商品列表")
    @PostMapping("/list")
    @ResponseBody
    public CommonResult list(String goodsName, String categoryName, Integer page, Integer pageSize) {
        String str =
            "[{\"addUser\": null, \"editUser\": null, \"addTime\": 1521062371000, \"editTime\": 1526700200000, \"deptId\": 2, \"deptName\": \"XX分公司\", \"deptNo\": \"1\", \"parentId\": 1 }, {\"addUser\": null, \"editUser\": null, \"addTime\": 1521063247000, \"editTime\": 1526652291000, \"deptId\": 3, \"deptName\": \"上海测试\", \"deptNo\": \"02\", \"parentId\": 1 }, {\"addUser\": null, \"editUser\": null, \"addTime\": 1526349555000, \"editTime\": 1526349565000, \"deptId\": 12, \"deptName\": \"1\", \"deptNo\": \"11\", \"parentId\": 1 }, {\"addUser\": null, \"editUser\": null, \"addTime\": 1526373178000, \"editTime\": 1526373178000, \"deptId\": 13, \"deptName\": \"5\", \"deptNo\": \"5\", \"parentId\": 1 }, {\"addUser\": null, \"editUser\": null, \"addTime\": 1526453107000, \"editTime\": 1526453107000, \"deptId\": 17, \"deptName\": \"v\", \"deptNo\": \"v\", \"parentId\": 1 } ]";
        JSONArray objects = JSONArray.parseArray(str);
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
}
