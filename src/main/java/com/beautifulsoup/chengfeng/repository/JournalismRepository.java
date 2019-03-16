package com.beautifulsoup.chengfeng.repository;

import com.beautifulsoup.chengfeng.pojo.Journalism;
import jdk.internal.jline.internal.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalismRepository extends MongoRepository<Journalism,String> {
    @Nullable
    List<Journalism> findTop5ByOrderByPublishTimeDesc();
    @Nullable
    Journalism findFirstByOrderByStarNumsAsc();
    @Nullable
    Journalism findTopByOrderByStarNumsDesc();
    @Nullable
    List<Journalism> findTop5ByCommunityId(Integer communityId, Sort sort);
    @Nullable
    List<Journalism> findByCommunityId(Integer communityId, Pageable pageable);

//    List<Journalism> findFirstBy
}
