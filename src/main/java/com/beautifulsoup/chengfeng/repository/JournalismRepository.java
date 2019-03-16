package com.beautifulsoup.chengfeng.repository;

import com.beautifulsoup.chengfeng.pojo.Journalism;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface JournalismRepository extends MongoRepository<Journalism,String> {
    List<Journalism> findTop5ByOrderByPublishTimeDesc();
    Journalism findFirstByOrderByStarNumsAsc();

    Journalism findTopByOrderByStarNumsDesc();

    List<Journalism> findTop5ByCommunityId(Integer communityId, Sort sort);
}
