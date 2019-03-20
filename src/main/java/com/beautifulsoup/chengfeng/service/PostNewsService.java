package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.PostNewsDetailVo;
import com.beautifulsoup.chengfeng.controller.vo.PostNewsVo;
import com.beautifulsoup.chengfeng.controller.vo.PostReplyVo;
import com.beautifulsoup.chengfeng.service.dto.PostNewsDto;
import com.beautifulsoup.chengfeng.service.dto.PostReplyDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostNewsService {
    PostNewsVo createPostNews(PostNewsDto postNewsDto, BindingResult result);
    PostNewsDetailVo getPostNewsDetail(Integer newsId);
    List<PostNewsVo> getAllPostNewsByPage(Integer pageNum,Integer pageSize);
    List<PostNewsVo> getNicePostNewsByPage(Integer pageNum, Integer pageSize);
    List<PostReplyVo> getPostReplysByNewsId(Integer newsId);
    List<PostReplyVo> getPostReplysChildrenById(Integer replyId);
    PostReplyVo createNewPostReply(PostReplyDto postReplyDto, MultipartFile[] files);
}
