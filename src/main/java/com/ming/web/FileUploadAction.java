package com.ming.web;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUploadAction extends ActionSupport {
    private File file;
    private String fileContentType;
    private String fileFileName;
    private String id;
    private String lastModifiedDate;
    private String name;
    private Long size;
    private String type;


    private Map<String,Object> json;




    public String upload() throws IOException{
        // 确定上传路径
        String uploadPath = ServletActionContext.getServletContext().getRealPath("/upload");

        // 根据文件分门别类的添加后缀
        String extension = fileFileName.substring(fileFileName.indexOf(".") + 1);

        String fileName = file.getName().substring(0, file.getName().indexOf("."));

        // 从临时文件复制
        FileUtils.copyFile(file, new File(uploadPath + File.separator + fileName + "." + extension));

        // 返回json
        this.json = new HashMap<>();
        json.put("uploadPath", File.separator + "upload" + File.separator + fileName + "." + extension);

        return SUCCESS;
    }

    public Map<String, Object> getJson() {
        return json;
    }

    public void setJson(Map<String, Object> json) {
        this.json = json;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
