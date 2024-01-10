package com.zhangz.demo.spring.cloud.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dao.GoodsCategoryMapper;
import com.zhangz.demo.spring.cloud.platform.entity.GoodsCategory;
import com.zhangz.demo.spring.cloud.platform.service.GoodsCategoryService;
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
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {
    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public CommonPage<GoodsCategory> listByShopIdAndIdName(Integer shopid, String id,String categoryName, Integer pageNum, Integer pageSize) {
        Page page = new Page();
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.listByShopIdAndIdName(categoryName,shopid, id, page);
        CommonPage commonPage = new CommonPage();
        commonPage.setList(goodsCategoryList);
        commonPage.setPageNum(pageNum);
        commonPage.setTotalPage(page.getTotal());
        commonPage.setPageSize(pageSize);
        commonPage.setTotal(page.getTotal());
        return commonPage;
    }

    @Override
    public void removeAllByShopId(Long shopid) throws BussinessException {
        if (null == shopid) {
            throw new BussinessException("商铺id不能为空");
        }
        goodsCategoryMapper.removeAllByShopId(shopid);
    }

    @Override
    public void insertOrUpdate(Long id, String name, Long shopid) throws BussinessException {
        GoodsCategory goodsCategory = new GoodsCategory();

        if (null == id) {
            // 新增
            goodsCategory.setId(goodsCategoryMapper.getMaxId() + 1);
            goodsCategory.setLevel(0);
            goodsCategory.setPaixu(0);
            goodsCategory.setPid(0);
            goodsCategory.setName(name);
            goodsCategory.setShopId(Math.toIntExact(shopid));
            goodsCategory.setUserId(27);
        } else {
            // 更新
            goodsCategory = goodsCategoryMapper.selectById(id);
            if (null == goodsCategory) {
                throw new BussinessException("分类不存在");
            }
            goodsCategory.setName(name);
        }
        saveOrUpdate(goodsCategory);
    }
}
