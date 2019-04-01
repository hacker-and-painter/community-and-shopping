package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.controller.vo.PurchaseInfoVo;
import com.beautifulsoup.chengfeng.repository.PurchaseInfoRepository;
import com.beautifulsoup.chengfeng.service.PurchaseInfoService;
import com.beautifulsoup.chengfeng.service.dto.PurchaseInfoDto;
import com.google.common.collect.Lists;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseInfoServiceImpl implements PurchaseInfoService {


    @Autowired
    private PurchaseInfoRepository purchaseInfoRepository;

    @Override
    public List<PurchaseInfoVo> getPurchaseInfo() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        builder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));


        builder.addAggregation(
                AggregationBuilders.terms("subtitle").field("subtitle")
                        .subAggregation(AggregationBuilders.avg("sales").field("sales")));
        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<PurchaseInfoDto> aggPage =
                (AggregatedPage<PurchaseInfoDto>) this.purchaseInfoRepository.search(builder.build());

        StringTerms agg = (StringTerms) aggPage.getAggregation("subtitle");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        List<PurchaseInfoVo> purchaseInfoVos= Lists.newArrayList();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            InternalAvg sales= (InternalAvg) bucket.getAggregations().asMap().get("sales");

            purchaseInfoVos.add(new PurchaseInfoVo(bucket.getKeyAsString(),sales.getValue()));
        }
        return purchaseInfoVos;
    }
}
