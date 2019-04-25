package com.photoduload.demo.controller;


import com.photoduload.demo.dataobject.FileInfo;
import com.photoduload.demo.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Controller
//@EnableAutoConfiguration
public class UpController {

    private String FILE_ROOT_FOLDER = "E://Ahian Li//桌面//相机后端//photoUDload//files//" ;

    @Autowired
    private FileServiceImpl fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String getUpLoadPage(){
        return "uploadPage";
    }

//    @PostMapping("/upload")这个是下面这个简写
    @RequestMapping(value = "/uploadAction", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, // 使用spring传输文件封装类
                             RedirectAttributes redirectAttributes){ // 跳转中的参数



        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "chose a file, plz!");
            return "redirect:/uploadStatus";
        }

        // get file and save it
        try {
            // get bytes of file posted
            byte[] bytes = file.getBytes();
            // reduce a local file
            Path path = Paths.get(FILE_ROOT_FOLDER + file.getOriginalFilename());
            // write file to local
            Files.write(path, bytes);

            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(path.getFileName().toString());
            fileInfo.setFileSize(Files.size(path));
            fileInfo.setFilePath(path.toRealPath().toString());
            fileInfo.setFileType(Files.probeContentType(path));
            fileInfo.setCreateTime(new Date());

            fileService.save(fileInfo);


            redirectAttributes.addFlashAttribute("message", "you upload file: " + file.getOriginalFilename()
                + "  successfully !");

        } catch (IOException e) {
            e.printStackTrace();
        }


        // 注意添加 redirect:
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(){
        return "uploadStatus";
    }
}
