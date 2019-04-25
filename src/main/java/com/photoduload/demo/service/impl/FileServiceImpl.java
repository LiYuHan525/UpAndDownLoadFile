package com.photoduload.demo.service.impl;

import com.photoduload.demo.dataobject.FileInfo;
import com.photoduload.demo.exception.FileException;
import com.photoduload.demo.service.FileService;
import com.photoduload.demo.dao.FileInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileInfoDao fileInfoDao;

    public FileInfo findByFileName(String fileName) {
        return fileInfoDao.findByFileName(fileName);
    }

    public FileInfo findById(Integer id){
        return fileInfoDao.findById(id);
    }

    public List<FileInfo> findAllByFileName(String fileName){
        return fileInfoDao.findAllByFileName(fileName);
    }

    public List<FileInfo> findAll(){
        return fileInfoDao.findAll();
    }


    public FileInfo save(FileInfo fileInfo){
        return fileInfoDao.save(fileInfo);
    }

    public void downloadFile(Integer id, HttpServletResponse response) throws FileException {

        if (id == null)
            throw new FileException("plz input id!");

        FileInfo fileInfo = findById(id);

        if (fileInfo == null)
            throw new FileException("can not find this file by id");

        response.setContentType("application/octet-stream"); // .*（ 二进制流，不知道下载文件类型）
        response.addHeader("Content-Disposition", "attachment;fileName=" + // 表示处理方式“attachment”附件，就是下载了
                fileInfo.getFileName());
        response.setHeader("Context-Type", "application/xmsdownload");

        Path path = Paths.get(fileInfo.getFilePath());

        if(Files.exists(path)){ // 路径存在
            if(path.toFile().isFile()){ // 是文件
                try {
                    byte[] buff = Files.readAllBytes(path); // Reads all the bytes from a file.
                    response.getOutputStream().write(buff);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new FileException(e.getMessage());
                }
            }else
                throw new FileException("file does not exists!");
        }else
            throw new FileException("path does not exists!");
    }
}
