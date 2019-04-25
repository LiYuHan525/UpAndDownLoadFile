package com.photoduload.demo.dao;

import com.photoduload.demo.dataobject.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public interface FileInfoDao extends JpaRepository<FileInfo, Integer> {

    FileInfo findByFileName(String fileName);

    List<FileInfo> findAllByFileName(String fileName);

    FileInfo findById(Integer id);
}
