package com.tony.admin.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.Tag;
import com.tony.framework.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/list")
    public ResultBean<List<Tag>> list() {
        List<Tag> list = tagService.list();
        return new ResultBean<>(list);
    }

}
