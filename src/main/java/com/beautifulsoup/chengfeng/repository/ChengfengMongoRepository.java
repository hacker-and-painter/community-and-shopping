package com.beautifulsoup.chengfeng.repository;

import com.beautifulsoup.chengfeng.pojo.Journalism;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ChengfengMongoRepository extends MongoRepository<Journalism,String> {
//    Page<Journalism> findFirst5ByPublishTime(Date publishTime, Pageable pageable);
    List<Journalism> findFirst5(Sort sort);
}
