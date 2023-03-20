package com.tony.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.Link;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.dto.LinkListDto;
import com.tony.framework.domain.vo.LinkVo;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.service.LinkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    @GetMapping("/list")
    public ResultBean listPage(Integer pageNum, Integer pageSize, LinkListDto param) {
        PageVo<LinkVo> vo = linkService.listPage(new Page<>(pageNum, pageSize), param);
        return new ResultBean(vo);
    }

    @GetMapping("/{id}")
    public ResultBean<Link> getById(@PathVariable Integer id) {
        Link link = linkService.getById(id);
        return new ResultBean<>(link);
    }

    @DeleteMapping("/{id}")
    public ResultBean<Void> delete(@PathVariable Integer id) {
        linkService.removeById(id);
        return new ResultBean<>();
    }

    @PutMapping
    public ResultBean<Void> update(@RequestBody Link link) {
        linkService.updateById(link);
        return new ResultBean<>();
    }

    @PostMapping
    public ResultBean<Void> insert(@RequestBody Link link) {
        linkService.save(link);
        return new ResultBean<>();
    }
}
