package com.tony.framework.service.impl;

import com.tony.framework.domain.Error;
import com.tony.framework.exception.CommonException;
import com.tony.framework.service.UploadService;
import com.tony.framework.utils.QiniuUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class OssUploadServiceImpl implements UploadService {

    @Override
    public String upload(MultipartFile file) {
        String url;
        try {
            String originalFilename = file.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");
            String suff = originalFilename.substring(index);
            String fileName = UUID.randomUUID() + suff;
            url = QiniuUtils.upload2QiniuReturn(file.getBytes(), fileName);

        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(Error.file_upload_error);
        }

        return url;
    }
}
