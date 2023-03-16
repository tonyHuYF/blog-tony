package com.tony.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.dto.TagListDto;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.domain.vo.TagVo;
import com.tony.framework.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/list")
    public ResultBean<PageVo<TagVo>> getTagList(Integer pageNum, Integer pageSize, TagListDto param) {
        PageVo<TagVo> vo = tagService.getTagList(new Page(pageNum, pageSize), param);
        return new ResultBean<>(vo);
    }

}
