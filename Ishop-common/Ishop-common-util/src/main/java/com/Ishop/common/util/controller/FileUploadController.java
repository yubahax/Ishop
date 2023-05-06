package com.Ishop.common.util.controller;


import com.Ishop.common.util.util.OssUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class FileUploadController {

    @PostMapping("/api/upload")
    public String upload(@RequestParam("file") MultipartFile uploadFile) throws IOException {
        return OssUtil.saveFile(uploadFile);
    }
}
