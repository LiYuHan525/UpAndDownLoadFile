package com.photoduload.demo.service.impl;

import com.photoduload.demo.dataobject.FileInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class FileServiceImplTest {

    @Autowired
    private FileServiceImpl fileService;

    @Test
    public void findByFileName() {
        List<FileInfo> fileInfo = fileService.findAllByFileName("课程目录.txt");
        Assert.assertEquals(2, fileInfo.size());
    }

    @Test
    public void save() {
        FileInfo fileInfo = new FileInfo();
        File file = new File("E:\\Ahian Li\\桌面\\课程目录.txt");
        Assert.assertNotNull(file);
        fileInfo.setFileName(file.getName());
        fileInfo.setFilePath(file.getAbsolutePath());
        fileInfo.setFileSize(file.length());

        FileInfo re = fileService.save(fileInfo);
        Assert.assertNotNull(re);
    }
}