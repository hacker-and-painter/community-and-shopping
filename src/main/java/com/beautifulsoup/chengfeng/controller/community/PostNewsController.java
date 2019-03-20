package com.beautifulsoup.chengfeng.controller.community;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.PostNewsDetailVo;
import com.beautifulsoup.chengfeng.controller.vo.PostNewsVo;
import com.beautifulsoup.chengfeng.controller.vo.PostReplyVo;
import com.beautifulsoup.chengfeng.pojo.Journalism;
import com.beautifulsoup.chengfeng.pojo.PostNews;
import com.beautifulsoup.chengfeng.service.PostNewsService;
import com.beautifulsoup.chengfeng.service.dto.PostNewsDto;
import com.beautifulsoup.chengfeng.service.dto.PostReplyDto;
import com.beautifulsoup.chengfeng.utils.ParamValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Api(value="普通帖子",tags= {"普通帖子Controller"},description = "普通帖子",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/news")
public class PostNewsController {

    @Autowired
    private PostNewsService postNewsService;

    @ApiOperation(value="发新贴",notes="发新帖方法")
    @PostMapping(value="/create",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<PostNewsVo> createPostNews(@Valid @RequestBody PostNewsDto messageDto, BindingResult result){

        PostNewsVo vo = postNewsService.createPostNews(messageDto,result);

        if(null!=vo) {
            return ResponseResult.createBySuccess(vo);
        }

        return ResponseResult.createByErrorMessage("帖子创建失败");
    }

    @GetMapping(value = "/all",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<List<PostNewsVo>> getAllPostNews(
            @RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "4",required = false)Integer pageSize){

        List<PostNewsVo> postNewsByPage= postNewsService.getAllPostNewsByPage(pageNum,pageSize);

        return ResponseResult.createBySuccess(postNewsByPage);
    }

    @GetMapping(value = "/nice",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<List<PostNewsVo>> getNicePostNews(
            @RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "4",required = false)Integer pageSize){

        List<PostNewsVo> postNewsByPage= postNewsService.getNicePostNewsByPage(pageNum,pageSize);

        return ResponseResult.createBySuccess(postNewsByPage);
    }

    @GetMapping(value = "/detail/{newsId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<PostNewsDetailVo> getPostNewsDetail(@PathVariable("newsId")Integer newsId){

        PostNewsDetailVo postNewsDetail = postNewsService.getPostNewsDetail(newsId);
        return ResponseResult.createBySuccess(postNewsDetail);
    }

    //获取平级回帖列表
    @GetMapping(value = "/replys/{newsId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<PostReplyVo>> getPostReplys(@PathVariable("newsId")Integer newsId){
        List<PostReplyVo> postNewsVoList=postNewsService.getPostReplysByNewsId(newsId);
        return ResponseResult.createBySuccess(postNewsVoList);
    }

    //递归获取指定回帖的所有子回帖
    @GetMapping(value = "/replys/children/{replyId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<PostReplyVo>> getPostReplysChildren(@PathVariable("replyId")Integer replyId){
        List<PostReplyVo> postNewsVoList=postNewsService.getPostReplysChildrenById(replyId);
        return ResponseResult.createBySuccess(postNewsVoList);
    }

    @ApiOperation(value="创建回帖",notes="发新回帖方法")
    @PostMapping(value="/reply/create",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<PostReplyVo> createPostNews(@Valid @RequestBody PostReplyDto postReplyDto,
                                                     @RequestParam(value = "images",required = false) MultipartFile[] files, BindingResult result){
        ParamValidatorUtil.validateBindingResult(result);

        PostReplyVo vo = postNewsService.createNewPostReply(postReplyDto,files);

        if(null!=vo) {
            return ResponseResult.createBySuccess(vo);
        }

        return ResponseResult.createByErrorMessage("帖子创建失败");
    }
}
