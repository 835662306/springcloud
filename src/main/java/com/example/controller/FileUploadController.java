package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 文件上传
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/26 10:47
 * @Version 1.0
 */
@Controller
public class FileUploadController {

    @PostMapping("/uploadFile")
    @ResponseBody
    public String fileUpload(@RequestParam(value = "file", required = true)MultipartFile file) throws Exception{
        byte[] bytes = file.getBytes();
        File fileToSave = new File("F:/"+file.getOriginalFilename());
        //通过流的形式复制文件
        FileCopyUtils.copy(bytes, fileToSave);
        return fileToSave.getAbsolutePath();
    }
}
