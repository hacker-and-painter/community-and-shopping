package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.pojo.Journalism;
import com.beautifulsoup.chengfeng.repository.JournalismRepository;
import com.beautifulsoup.chengfeng.service.JournalismService;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.google.common.collect.Lists;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class JournalismServiceImpl implements JournalismService {

    @Autowired
    private JournalismRepository journalismRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemcachedClient memcachedClient;

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


}
