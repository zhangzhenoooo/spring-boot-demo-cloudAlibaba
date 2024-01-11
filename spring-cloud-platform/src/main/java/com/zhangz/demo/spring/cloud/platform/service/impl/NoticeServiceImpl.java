package com.zhangz.demo.spring.cloud.platform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dao.NoticeMapper;
import com.zhangz.demo.spring.cloud.platform.dto.NoticeDTO;
import com.zhangz.demo.spring.cloud.platform.entity.Notice;
import com.zhangz.demo.spring.cloud.platform.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
@RefreshScope
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public CommonPage pageList(String title, String dataAddFrom, String dataAddTo, Integer page, Integer pageSize) {
        Page p = new Page();
        p.setSize(pageSize);
        p.setCurrent(page);
        List<Notice> notices = noticeMapper.pageList(title, dataAddFrom, dataAddTo, p);
        CommonPage commonPage = new CommonPage();
        commonPage.setList(notices);
        commonPage.setPageNum(page);
        commonPage.setTotal(p.getTotal());
        commonPage.setTotalPage(p.getTotal());
        return commonPage;
    }

    @Override
    public void insertOrUpdate(NoticeDTO noticeDTO) {
        Integer id = noticeDTO.getId();
        if (null == id) {
            Notice notice = BeanUtil.copyProperties(noticeDTO, Notice.class);
            notice.setId(noticeMapper.getMaxId() + 1);
            notice.setDateAdd(DateUtil.formatDateTime(new Date()));
            noticeMapper.insert(notice);
        } else {
            noticeMapper.updateById(BeanUtil.copyProperties(noticeDTO, Notice.class));
        }
    }

    @Override
    public void changeStatus(Integer id, Integer isShow) throws BussinessException {
        if (null == id || null == isShow) {
            throw new BussinessException("参数不能为空");
        }
        Notice notice = noticeMapper.selectById(id);
        if (null == notice) {
            throw new BussinessException("通知不存在");
        }
        notice.setIsShow(isShow);
        noticeMapper.updateById(notice);
    }
}
