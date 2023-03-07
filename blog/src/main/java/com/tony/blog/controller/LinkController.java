package com.tony.blog.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.vo.LinkVo;
import com.tony.framework.service.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    @GetMapping("/getAllLink")
    public ResultBean<List<LinkVo>> getAllLink() {
        List<LinkVo> list = linkService.getAllLink();
        return new ResultBean<>(list);
    }
}
