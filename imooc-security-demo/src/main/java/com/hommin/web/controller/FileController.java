package com.hommin.web.controller;/**
 * Created by Hommin on 2018/3/7.
 */

import com.hommin.dto.FileInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author Hommin
 * @ClassName: FileController
 * @Description: 文件上传与下载
 * @data 2018年03月07日 下午2:00
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping
    public FileInfo uploadFile(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());


        String folder = "/Users/hommin/Documents/workspace-IDEA/imooc-security-parent/imooc-security-demo/src/main/java/com/hommin/web/controller";
        File localFile = new File(folder, Calendar.getInstance().getTimeInMillis()+".txt");

        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public String download(@PathVariable String id , HttpServletRequest request, HttpServletResponse response){

        return null;
    }
}
