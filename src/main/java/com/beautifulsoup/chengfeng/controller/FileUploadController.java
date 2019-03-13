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

@Api(tags = "/file",description = "文件上传",protocols = "http")
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

    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDfsFile file = new FastDfsFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDfsClientUtil.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            log.error("upload file Exception!",e);
        }
        if (fileAbsolutePath==null) {
            log.error("upload file failed,please upload again!");
        }
        String path= FastDfsClientUtil.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        return path;
    }
}
