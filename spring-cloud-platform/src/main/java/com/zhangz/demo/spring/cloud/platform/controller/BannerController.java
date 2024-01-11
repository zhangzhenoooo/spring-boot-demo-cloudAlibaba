package com.zhangz.demo.spring.cloud.platform.controller;

import com.alibaba.fastjson.JSON;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dto.BannerDTO;
import com.zhangz.demo.spring.cloud.platform.dto.DeleteGoodsDTO;
import com.zhangz.demo.spring.cloud.platform.dto.NoticeDTO;
import com.zhangz.demo.spring.cloud.platform.entity.Banner;
import com.zhangz.demo.spring.cloud.platform.entity.Notice;
import com.zhangz.demo.spring.cloud.platform.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/api/banner")
@Api(tags = "banner管理")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @ApiOperation(value = "banner列表", notes = "banner列表")
    @PostMapping("/list")
    @ResponseBody
    public CommonResult list(Integer page, Integer pageSize) {

        CommonPage commonPage = bannerService.pageList(page, pageSize);
        return CommonResult.success(commonPage);
    }

    @ApiOperation(value = "banner详情", notes = "banner详情")
    @GetMapping("/detail")
    @ResponseBody
    public CommonResult detail(Long id) throws BussinessException {
        log.info("banner详情，bannerid：{}", id);
        Banner banner = bannerService.getById(id);
        return CommonResult.success(banner);
    }

    @ApiOperation(value = "删除banner", notes = "删除banner")
    @DeleteMapping("/delete")
    @ResponseBody
    public CommonResult delete(Long id) {
        log.info("删除banner，bannerid：{}", id);
        bannerService.removeById(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "新增banner", notes = "新增banner")
    @PostMapping("/add")
    @ResponseBody
    public CommonResult add(BannerDTO bannerDTO) {
        log.info("新增banner，banner信息：{}", JSON.toJSONString(bannerDTO));
        bannerService.insertOrUpdate(bannerDTO);
        return CommonResult.success();
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @PostMapping("/deleteBatch")
    @ResponseBody
    public CommonResult deleteBatch(@RequestBody DeleteGoodsDTO dto) {
        log.info("批量删除：{}", JSON.toJSONString(dto));
        bannerService.removeByIds(dto.getIds());
        return CommonResult.success();
    }

    @ApiOperation(value = "修改banner是否展示", notes = "修改banner是否展示")
    @GetMapping("/changeStatus")
    @ResponseBody
    public CommonResult changeStatus(Integer id, Integer status) throws BussinessException {
        log.info("修改用户状态：id{}:status{}", id, status);
        bannerService.changeStatus(id, status);
        return CommonResult.success();
    }

}
