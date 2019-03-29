package com.beautifulsoup.chengfeng.kafka;


import com.beautifulsoup.chengfeng.service.dto.PurchaseInfoDto;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class PurchaseInfoDeSerializer implements Deserializer<PurchaseInfoDto> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public PurchaseInfoDto deserialize(String topic, byte[] data) {
        if(data==null) {
            return null;
        }

        Schema<PurchaseInfoDto> schema = RuntimeSchema.getSchema(PurchaseInfoDto.class);
        PurchaseInfoDto purchaseInfoDto=new PurchaseInfoDto();
        ProtostuffIOUtil.mergeFrom(data, purchaseInfoDto, schema);
        return purchaseInfoDto;
    }

    @Override
    public void close() {}
}
