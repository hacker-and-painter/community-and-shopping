package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.controller.vo.PostVoteVo;
import com.beautifulsoup.chengfeng.controller.vo.VoteOptionVo;
import com.beautifulsoup.chengfeng.dao.PostVoteMapper;
import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.dao.VoteOptionMapper;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.beautifulsoup.chengfeng.pojo.PostVote;
import com.beautifulsoup.chengfeng.pojo.User;
import com.beautifulsoup.chengfeng.pojo.VoteOption;
import com.beautifulsoup.chengfeng.service.PostVoteService;
import com.beautifulsoup.chengfeng.service.dto.PostVoteDto;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.google.common.collect.Lists;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class PostVoteServiceImpl implements PostVoteService {

    @Autowired
    private PostVoteMapper postVoteMapper;

    @Autowired
    private VoteOptionMapper voteOptionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;


    @Override
    public PostVoteVo createNewPostVote(PostVoteDto postVoteDto, BindingResult result) {
        try {
            User user = AuthenticationInfoUtil.getUser(userMapper,memcachedClient);
            PostVote postVote=new PostVote();
            postVote.setChoice(postVoteDto.getChoice());
            postVote.setDescription(postVoteDto.getDescription());
            postVote.setTitle(postVoteDto.getTitle());
            postVote.setVoteTotal(0);
            postVote.setUserId(user.getId());
            postVote.setValidityTime(postVoteDto.getValidityTime());
            postVoteMapper.insertSelective(postVote);
            List<VoteOptionVo> voteOptionVos= Lists.newArrayList();
            if (!CollectionUtils.isEmpty(postVoteDto.getOptionList())){
                postVoteDto.getOptionList().forEach(voteOptionDto -> {
                    VoteOption voteOption=new VoteOption();
                    voteOption.setVoteId(postVote.getId());
                    voteOption.setVoteNums(0);
                    voteOption.setContent(voteOptionDto.getContent());
                    voteOptionMapper.insertSelective(voteOption);

                    VoteOptionVo voteOptionVo=new VoteOptionVo();
                    BeanUtils.copyProperties(voteOption,voteOptionVo);
                    voteOptionVos.add(voteOptionVo);
                });
            }
            PostVoteVo postVoteVo=new PostVoteVo();
            BeanUtils.copyProperties(postVote,postVoteVo);
            postVoteVo.setOptionList(voteOptionVos);
            return postVoteVo;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    @Override
    public VoteOptionVo submitVoteOption(Integer optionId) {
        VoteOption voteOption = voteOptionMapper.selectByPrimaryKey(optionId);
        if (null==voteOption){
            throw new ParamException("投票选项不存在,投票失败");
        }
        PostVote postVote=postVoteMapper.selectByPrimaryKey(voteOption.getVoteId());
        if (null==postVote){
            throw new ParamException("投票不存在,投票失败");
        }
        voteOption.setVoteNums(redisTemplate.opsForValue().increment(RedisConstant.COUNTER_VOTE_OPTION_NUMS+optionId).intValue());
        postVote.setVoteTotal(redisTemplate.opsForValue().increment(RedisConstant.COUNTER_POST_VOTE+voteOption.getVoteId()).intValue());
        postVoteMapper.updateByPrimaryKeySelective(postVote);
        voteOptionMapper.updateByPrimaryKeySelective(voteOption);
        VoteOptionVo voteOptionVo=new VoteOptionVo();
        BeanUtils.copyProperties(voteOption,voteOptionVo);
        return voteOptionVo;
    }



}
