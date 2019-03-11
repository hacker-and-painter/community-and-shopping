package com.beautifulsoup.chengfeng.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FastDfsFile {
    private String name;        //文件名
    private byte[] content;     //文件内容
    private String ext;         //文件类型
    private String md5;         //加密方式
    private String author;      //作者

    public FastDfsFile(String name, byte[] content, String ext, String height,
                       String width, String author) {
        super();
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.author = author;
    }

    public FastDfsFile(String name, byte[] content, String ext) {
        super();
        this.name = name;
        this.content = content;
        this.ext = ext;
    }

}
