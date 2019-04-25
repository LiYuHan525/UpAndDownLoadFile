package com.photoduload.demo.controller;

import com.photoduload.demo.dataobject.FileInfo;
import com.photoduload.demo.exception.FileException;
import com.photoduload.demo.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class DownController {

    @Autowired
    private FileServiceImpl fileService;

    /**
     * 显示所有的文件
     * @return
     */
    @GetMapping("/allFiles")
    public ModelAndView  getFileList(){
        ModelAndView model = new ModelAndView("fileList");

        List<FileInfo> allFiles = fileService.findAll();

        int nums[] = {1, 2, 3, 4};

        model.addObject("allFiles", allFiles);
        model.addObject("name", "Resse");
        model.addObject("nums", nums);

        return model;
    }

    /**
     * 前端传来需要下载文件的id
     * @param filesId
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/selectedFiles")
    public String selected(@RequestParam("files") Integer filesId[], // 包含选中文件id的数组
                           RedirectAttributes redirectAttributes){

        List<FileInfo> fileInfoList = new ArrayList<>();

        for (int i = 0; i < filesId.length ; i++) {
            System.out.println(filesId[i]);
            fileInfoList.add(fileService.findById(filesId[i]));

        }
//        System.out.println(fileInfoList.size());

        redirectAttributes.addFlashAttribute("message", "the count of number you submit is: " + filesId.length);

        return "redirect:/index";
    }



    @PostMapping("/downloadFile")
    public void daowload(@RequestParam("fileId") Integer id,
                HttpServletResponse response){

        try {
            if (id == null)
                throw new FileException("plz input id!");

            FileInfo fileInfo = fileService.findById(id);

            if (fileInfo == null)
                throw new FileException("can not find this file by id");

            response.setContentType("application/octet-stream"); // .*（ 二进制流，不知道下载文件类型）
            response.addHeader("Content-Disposition", "attachment;fileName=" + // 表示处理方式“attachment”附件，就是下载了
//                    fileInfo.getFileName());
                    new String(fileInfo.getFileName().getBytes("UTF-8"), "iso8859-1")); // 设置文件名，需要解决乱码
//            response.setHeader("Context-Type", "application/xmsdownload");

            Path path = Paths.get(fileInfo.getFilePath());
            if(Files.exists(path)) { // 路径存在
                if (path.toFile().isFile()) { // 是文件
                    response.getOutputStream().write(Files.readAllBytes(path));
                }else
                    throw new FileException("file does not exists!");
            }else
                throw new FileException("path does not exists!");

        } catch (Exception e) {
            e.printStackTrace();
        }

//        return "index"; // 不能有返回页面，不然会出现getOutputStream() has already been called 的错误
    }


    /**
     * 调试页面
     * @return
     */
    @GetMapping("index")
    public String goIndex(){
        return "index";
    }
}
