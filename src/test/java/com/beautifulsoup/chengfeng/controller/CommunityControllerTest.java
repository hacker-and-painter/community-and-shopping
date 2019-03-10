package com.beautifulsoup.chengfeng.controller;

import com.beautifulsoup.chengfeng.ChengfengApplicationTests;
import com.beautifulsoup.chengfeng.controller.community.CommunityController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CommunityControllerTest extends ChengfengApplicationTests {

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(new CommunityController()).build();
    }

    @Test
    public void getAllCommunitiesTest(){

    }
}
