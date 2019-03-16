package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.pojo.Journalism;

import java.util.List;

public interface JournalismService {
    List<Journalism> getTop5JournalismsOrderByPublishTime();
    Journalism getJournalismById(String journalismId);
    List<Journalism> getAllJournalismsByPage(Integer pageNum,Integer pageSize);
}
