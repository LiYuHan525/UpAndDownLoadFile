package com.photoduload.demo.dataobject;

import lombok.Data;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@Table(name = "file_info")
public class FileInfo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private String fileName;

//    @NonNull
    private String filePath;

    private String fileType;

    private Long FileSize; //文件大小

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

}
