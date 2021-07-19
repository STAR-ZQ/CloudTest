package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.context.StrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class TestController {
    @Autowired
    private StrategyContext context;

    @RequestMapping("/testMethod")
    public String testMethod(String code) {
        return context.testMethod(code).testMethod();
    }

    @PostMapping("/test")
    public void test(String code, String token, String name, @RequestBody JSONObject jsonObject) {
        System.out.println("===========================================" + "/" + token + "/" + code + "/" + name + "\n" + jsonObject);
    }

    @PostMapping("/savePicByFormData")
    public String savePicByFormData(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = savePicByFormDatas(file);
        return fileName;
    }

    public static String savePicByFormDatas(MultipartFile file) throws IOException {

        // 图片存储路径
        String path = "C:\\image\\factory";
        // 判断是否有路径
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File tempFile = new File(path, fileName);
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        return fileName;
    }
}
