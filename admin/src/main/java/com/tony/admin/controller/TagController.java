package com.tony.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.Tag;
import com.tony.framework.domain.dto.TagListDto;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.domain.vo.TagVo;
import com.tony.framework.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping
    public ResultBean<Void> insert(@RequestBody Tag tag) {
        tagService.save(tag);
        return new ResultBean<>();
    }

    @PutMapping
    public ResultBean<Void> update(@RequestBody Tag tag) {
        tagService.updateById(tag);
        return new ResultBean<>();
    }

    @GetMapping("/{id}")
    public ResultBean<TagVo> getById(@PathVariable Integer id) {
        Tag tag = tagService.getById(id);
        TagVo vo = new TagVo();
        BeanUtil.copyProperties(tag, vo);
        return new ResultBean<>(vo);
    }

    @DeleteMapping("/{id}")
    public ResultBean<Void> delete(@PathVariable Integer id) {
        tagService.removeById(id);
        return new ResultBean<>();
    }

    @GetMapping("/listAllTag")
    public ResultBean<List<TagVo>> listAllTag() {
        List<TagVo> vo = tagService.listAllTag();
        return new ResultBean<>(vo);
    }

}
