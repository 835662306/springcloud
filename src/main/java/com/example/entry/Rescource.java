package com.example.entry;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/21 14:22
 * @Version 1.0
 */
@javax.persistence.Table
@Entity(name = "sys_rescource")
public class Rescource implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "source")
    private String source;

    @Column(name = "web_url")
    private String webUrl;

    @Column(name = "hash")
    private String hash;

    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "file_type")
    private String fileType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Rescource() {
    }

    public Rescource(String fileName, String source, String webUrl, String hash, String fileSize, String fileType) {
        this.fileName = fileName;
        this.source = source;
        this.webUrl = webUrl;
        this.hash = hash;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }
}
