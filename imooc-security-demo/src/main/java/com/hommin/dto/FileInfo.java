package com.hommin.dto;/**
 * Created by Hommin on 2018/3/7.
 */

/**
 * @author Hommin
 * @ClassName: FileInfo
 * @Description: 文件信息
 * @data 2018年03月07日 下午2:02
 */
public class FileInfo {

    public FileInfo(String path) {
        this.path = path;
    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
