package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.PostVoteVo;
import com.beautifulsoup.chengfeng.controller.vo.VoteOptionVo;
import com.beautifulsoup.chengfeng.service.dto.PostVoteDto;
import org.springframework.validation.BindingResult;

public interface PostVoteService {
    PostVoteVo createNewPostVote(PostVoteDto postVoteDto, BindingResult result);
    VoteOptionVo submitVoteOption(Integer optionId);
}
