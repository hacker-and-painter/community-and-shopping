package com.beautifulsoup.chengfeng.constant;

public class RedisConstant {
    public static final String TOKEN_SALT="token:";
    public static final String USERS = "users:";
    public static final String USERINFOS="userinfos:";
    public static final String POSTERS_INFO="posters_info:";
    public static final String PROPER_NOTICE_PREFIX="pnotice_prefix:";
    public static final String COMMUNITY_NOTICE_PREFIX="cnotice_prefix:";
    public static final String PROPER_NOTICE_ORDER="pnotice_order:";
    public static final String COMMUNITY_NOTICE_ORDER="cnotice_order:";
    public static final String PROPER_NOTICES="proper_notices:";
    public static final String COMMUNITY_NOTICES="community_notices:";
    public static final String PRODUCT_STOCKS="product_stocks:";
    public static final String PRODUCT_PREFIX_SKU="product_prefix_sku:";
    public static final String EMAIL_VALIDATE_CODE="email_validate_code";
    public static final String EMAIL_VALIDATE_CODE_PREFIX="email_validate_code_prefix:";
    //帖子模块
    public static final String POST_NEWS_BELONGTO_ORDER="post_news_belongto_order:";
    public static final String POST_NEWS_BELONGTO="post_news_belongto:";
    public static final String POST_NEWS_COMMUNITY_ORDER="post_news_community_order:";
    public static final String POST_NEWS_PREFIX="post_news_prefix:";
    public static final String POST_ALLNEWS="post_allnews:";
    public static final String POST_REPLY_BELONGTO_ORDER="post_reply_belongto_order:";
    public static final String POST_REPLY_BELONGTO="post_reply_belongto:";
    public static final String POST_REPLY_PREFIX="post_reply_prefix:";

    //购物车模块
    public static final String CART_BELONG_TO="cart_belong_to:";
    public static final String CART_PRODUCT_PREFIX="cart_product_prefix:";
    //计数器
    public static final String COUNTER_FOLLOWER="counter_follower:";
    public static final String COUNTER_FOLLOWING="counter_following:";
    public static final String COUNTER_POST_NEWS="counter_post_news:";
    public static final String COUNTER_POST_REPLYS="counter_post_replys:";
    public static final String COUNTER_COLLECTIONS="counter_collections:";
    public static final String COUNTER_POST_VOTE="counter_post_vote:";
    public static final String COUNTER_VOTE_OPTION_NUMS="counter_vote_option_nums:";
    public static final String COUNTER_ORDER="COUNTER_ORDER:";

    public interface Redisson{
        String REDIS_ADDRESS="redis://47.95.244.237:6379";
        String LOCK_SPELL_ORDER="lock_spell_order";
    }

}
