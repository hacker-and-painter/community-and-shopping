package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.Community;

import java.util.List;


public interface CommunityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);

	Community selectByName(String name);

	List<Community> selectAllCommunities();
}