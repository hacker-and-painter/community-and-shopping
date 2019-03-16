package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.controller.vo.PostReplyVo;
import com.beautifulsoup.chengfeng.pojo.PostReply;

import java.util.List;

public interface PostReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostReply record);

    int insertSelective(PostReply record);

    PostReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostReply record);

    int updateByPrimaryKey(PostReply record);

    List<PostReply> selectByPostNewsId(Integer newsId);

    List<PostReply> selectByPostNewsParentId(Integer parentId);
}