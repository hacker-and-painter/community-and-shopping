package com.beautifulsoup.chengfeng.controller;

import com.beautifulsoup.chengfeng.common.FastDfsFile;
import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.constant.ErrorConstant;
import com.beautifulsoup.chengfeng.utils.FastDfsClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.beautifulsoup.chengfeng.utils.FastDfsClientUtil.saveFile;

@Api(value="文件上传",tags= {"文件上传Controller"},description = "文件上传",protocols = "http")
@Controller
@RequestMapping("/file")
@Slf4j
public class FileUploadController {

    @GetMapping("/")
    public String getUploadHtml(){
        return "upload";
    }

    @ApiOperation(value = "文件上传",notes = "上传文件",produces = "multipart/form-data",
            response = ResponseResult.class,httpMethod = "POST")
    @PostMapping("/upload")
    @ResponseBody
    public ResponseResult uploadFile(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            throw new MultipartException(ErrorConstant.UPLOAD_EMPTY_ERROR);
        }
        try {
            String path = saveFile(file);
            return ResponseResult.createBySuccess(path);
        } catch (IOException e) {
            throw new MultipartException(ErrorConstant.UPLOAD_FAILURE);
        }
    }

    @ApiOperation(value = "文件上传",notes = "批量上传文件",produces = "multipart/form-data",
            response = ResponseResult.class,httpMethod = "POST")
    @PostMapping("/uploads")
    @ResponseBody
    public ResponseResult uploadFiles(@RequestParam("files") MultipartFile[] files){
        if(files==null||files.length<1){
            throw new MultipartException(ErrorConstant.UPLOAD_EMPTY_ERROR);
        }
        try {
            StringBuffer stringBuffer=new StringBuffer();
            for (int i=0;i<files.length;i++){
                if (i!=files.length-1){
                    stringBuffer.append(saveFile(files[i])).append(",");
                }else{
                    stringBuffer.append(saveFile(files[i]));
                }
            }
            return ResponseResult.createBySuccess("图片上传成功",stringBuffer.toString());
        } catch (IOException e) {
            throw new MultipartException(ErrorConstant.UPLOAD_FAILURE);
        }
    }



}
