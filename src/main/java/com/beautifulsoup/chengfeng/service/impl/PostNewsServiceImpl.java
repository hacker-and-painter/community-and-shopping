package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.controller.vo.PostNewsVo;
import com.beautifulsoup.chengfeng.controller.vo.PostReplyVo;
import com.beautifulsoup.chengfeng.dao.PostNewsMapper;
import com.beautifulsoup.chengfeng.dao.PostReplyMapper;
import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.pojo.PostNews;
import com.beautifulsoup.chengfeng.pojo.PostReply;
import com.beautifulsoup.chengfeng.pojo.User;
import com.beautifulsoup.chengfeng.service.PostNewsService;
import com.beautifulsoup.chengfeng.service.dto.PostNewsDto;
import com.beautifulsoup.chengfeng.service.dto.PostReplyDto;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.beautifulsoup.chengfeng.utils.JsonSerializableUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static com.beautifulsoup.chengfeng.utils.FastDfsClientUtil.uploadFiles;

@Service
public class PostNewsServiceImpl implements PostNewsService {

    @Autowired
    private PostNewsMapper postNewsMapper;
    @Autowired
    private PostReplyMapper postReplyMapper;
    @Autowired
    private MemcachedClient memcachedClient;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Transactional
    @Override
    public PostNewsVo createPostNews(PostNewsDto postNewsDto, MultipartFile[] files) {
        try {
            User user = AuthenticationInfoUtil.getUser(userMapper, memcachedClient);
            PostNews postNews=new PostNews();
            BeanUtils.copyProperties(postNewsDto,postNews);
            if (StringUtils.isBlank(postNewsDto.getImgUrl())){
                postNews.setImgUrl(uploadFiles(files));
            }
            postNews.setPosted(new Date());
            postNews.setStar(0);
            postNews.setComments(0);
            if (postNewsDto.getType()==null){
                postNews.setType(new Integer(0).byteValue());
            }
            postNews.setUserId(user.getId());
            postNewsMapper.insert(postNews);

            PostNewsVo postNewsVo=new PostNewsVo();
            BeanUtils.copyProperties(postNews,postNewsVo);
            //加入redis方便维护点赞量和回复量
            redisTemplate.opsForZSet().add(RedisConstant.POST_NEWS_BELONGTO_ORDER+user.getNickname(),postNewsVo,postNews.getStar());
            redisTemplate.opsForHash().put(RedisConstant.POST_NEWS_BELONGTO+user.getNickname(),
                    RedisConstant.POST_NEWS_PREFIX+postNewsVo.getId(), postNewsVo);
            redisTemplate.opsForZSet().add(RedisConstant.POST_NEWS_COMMUNITY_ORDER+user.getCommunityId(),postNewsVo,postNews.getStar());
            return postNewsVo;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return null;
    }

    //从redis中取数据,提高查询性能,
    @Override
    public List<PostNewsVo> getAllPostNewsByPage(Integer pageNum, Integer pageSize) {
        try {

            return getAllPostNews(pageNum,pageSize);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


        return null;
    }

    //热点数据入redis
    @Override
    public List<PostNewsVo> getNicePostNewsByPage(Integer pageNum, Integer pageSize) {
        try {
            List<PostNewsVo> postNewsVos = getAllPostNews(pageNum, pageSize);
            return postNewsVos.stream().filter(news->news.getType().intValue()!=0).collect(Collectors.toList());
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
    public List<PostReplyVo> getPostReplysByNewsId(Integer newsId) {
        List<PostReplyVo> replies=Lists.newArrayList();
        List<PostReply> replys=postReplyMapper.selectByPostNewsId(newsId);
        replys.stream().sorted(Comparator.comparing(PostReply::getReplyTime).reversed()).forEach(reply->{
            PostReplyVo postReplyVo=new PostReplyVo();
            BeanUtils.copyProperties(reply,postReplyVo);
            replies.add(postReplyVo);
        });
        return replies;
    }

    @Override
    public List<PostReplyVo> getPostReplysChildrenById(Integer replyId) {
        List<PostReplyVo> replies=Lists.newArrayList();
        Set<PostReply> replySet= Sets.newHashSet();
        getChildrenPostReplys(replySet,replyId).stream().forEach(postReply -> {
            PostReplyVo postReplyVo=new PostReplyVo();
            BeanUtils.copyProperties(postReply,postReplyVo);
            replies.add(postReplyVo);
        });
        return replies;
    }

    @Override
    public PostReplyVo createNewPostReply(PostReplyDto postReplyDto, MultipartFile[] files) {
            PostReply postReply=new PostReply();
            BeanUtils.copyProperties(postReplyDto,postReply);
            if (StringUtils.isBlank(postReply.getImgUrl())){
                postReply.setImgUrl(uploadFiles(files));
            }
            postReply.setReplyTime(new Date());
            postReply.setStar(0);
            postReply.setIsParent(0);
            postReply.setParentId(0);

            postReplyMapper.insert(postReply);
            PostReplyVo postReplyVo=new PostReplyVo();
            BeanUtils.copyProperties(postReply,postReplyVo);

            //加入redis方便维护点赞量
            redisTemplate.opsForZSet().add(RedisConstant.POST_REPLY_BELONGTO_ORDER+postReply.getParentId(),postReplyVo,postReply.getStar());
            redisTemplate.opsForHash().put(RedisConstant.POST_REPLY_BELONGTO+postReply.getParentId(),
                    RedisConstant.POST_REPLY_PREFIX+postReplyVo.getId(), postReplyVo);
            return postReplyVo;

    }

    private List<PostNewsVo> getAllPostNews(Integer pageNum,Integer pageSize) throws InterruptedException, MemcachedException, TimeoutException {
        List<PostNewsVo> postNewsVoList= Lists.newArrayList();
        User user = AuthenticationInfoUtil.getUser(userMapper, memcachedClient);
        redisTemplate.opsForZSet().reverseRange(RedisConstant.POST_NEWS_COMMUNITY_ORDER
                +user.getCommunityId(), pageNum - 1, pageSize).stream().forEach(e->{
            postNewsVoList.add((PostNewsVo) e);
        });
        return postNewsVoList;
    }

    private Set<PostReply> getChildrenPostReplys(Set<PostReply> postReplies,Integer replyId){
        PostReply postReply1 = postReplyMapper.selectByPrimaryKey(replyId);
        if (null!=postReply1){
            postReplies.add(postReply1);
        }
        List<PostReply> replies = postReplyMapper.selectByPostNewsParentId(replyId);
        if (!CollectionUtils.isEmpty(replies)){
            for (PostReply postReply:replies){
                getChildrenPostReplys(postReplies,postReply.getId());
            }
        }
        return postReplies;

    }

}
