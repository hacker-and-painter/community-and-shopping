package com.beautifulsoup.chengfeng.memcached;

import com.beautifulsoup.chengfeng.ChengfengApplicationTests;
import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeoutException;

@Slf4j
public class MemcachedTest extends ChengfengApplicationTests {

    @Autowired
    private MemcachedClient memcachedClient;

    @Test
    public void casMemcachedTest() throws InterruptedException, MemcachedException, TimeoutException {
        memcachedClient.cas("age", 0, new CASOperation<Integer>() {
            @Override
            public int getMaxTries() {
                return ChengfengConstant.MEMCACHED.CAS_RETRIES;
            }

            @Override
            public Integer getNewValue(long l, Integer integer) {
                return 2;
            }
        });
    }

}
