package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.pojo.Journalism;
import com.beautifulsoup.chengfeng.repository.JournalismRepository;
import com.beautifulsoup.chengfeng.service.JournalismService;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class JournalismServiceImpl implements JournalismService {

    @Autowired
    private JournalismRepository journalismRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<Journalism> getTop5JournalismsOrderByPublishTime() {
        try {
            Integer communityId = AuthenticationInfoUtil.getUser(userMapper, memcachedClient).getCommunityId();
            List<Sort.Order> orders= Lists.newArrayList();
            Sort.Order order=new Sort.Order(Sort.Direction.DESC, "publishTime");
            orders.add(order);
            Sort sort=Sort.by(orders);
            return journalismRepository.findTop5ByCommunityId(communityId,sort);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Journalism getJournalismById(String journalismId) {
        Optional<Journalism> journalism = journalismRepository.findById(journalismId);
        if (journalism.isPresent()){
            return journalism.get();
        }
        return null;
    }

    @Override
    public List<Journalism> getAllJournalismsByPage(Integer pageNum,Integer pageSize) {
        try {
            Sort sort=Sort.by(ImmutableList.of(new Sort.Order(Sort.Direction.DESC,"publishTime")));
            Integer communityId = AuthenticationInfoUtil.getUser(userMapper, memcachedClient).getCommunityId();
            return journalismRepository.findByCommunityId(communityId, PageRequest.of(pageNum,pageSize,sort));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Journalism> getHotJournalisms(Integer num) {
        try {
            Integer communityId = AuthenticationInfoUtil.getUser(userMapper, memcachedClient).getCommunityId();
            Query query=new Query();
            query.addCriteria(Criteria.where("communityId").is(communityId));
            query.with(new Sort(Sort.Direction.DESC,"starNums"));
            return mongoOperations.find(query,Journalism.class).stream().limit(num).collect(Collectors.toList());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return null;
    }


}
