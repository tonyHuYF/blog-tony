package com.tony.blog.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResultBean<String> upload(MultipartFile img) {
        String upload = uploadService.upload(img);
        return new ResultBean<>(upload);
    }
}
