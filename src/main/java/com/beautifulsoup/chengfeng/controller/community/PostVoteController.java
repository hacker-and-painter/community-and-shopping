package com.beautifulsoup.chengfeng.controller.community;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.PostVoteVo;
import com.beautifulsoup.chengfeng.controller.vo.PosterVo;
import com.beautifulsoup.chengfeng.controller.vo.VoteOptionVo;
import com.beautifulsoup.chengfeng.service.PostVoteService;
import com.beautifulsoup.chengfeng.service.dto.PostVoteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value="投票帖子",tags= {"投票帖子Controller"},description = "投票帖子",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/vote")
public class PostVoteController {

    @Autowired
    private PostVoteService postVoteService;

    @ApiOperation(value="创建投票贴",notes="创建投票贴")
    @PostMapping(value="/create",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<PostVoteVo> createNewPostVote(@Valid @RequestBody PostVoteDto postVoteDto, BindingResult result){
        PostVoteVo vo = postVoteService.createNewPostVote(postVoteDto,result);
        if(null!=vo) {
            return ResponseResult.createBySuccess(vo);
        }
        return ResponseResult.createByErrorMessage("投票贴创建失败");
    }

    @ApiOperation(value="针对投票贴进行投票",notes="针对投票贴进行投票")
    @PostMapping(value="/submit/{optionId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<VoteOptionVo> submitVoteOption(@PathVariable("optionId")Integer optionId){
        VoteOptionVo vo = postVoteService.submitVoteOption(optionId);
        if(null!=vo) {
            return ResponseResult.createBySuccess(vo);
        }
        return ResponseResult.createByErrorMessage("投票贴创建失败");
    }


}
