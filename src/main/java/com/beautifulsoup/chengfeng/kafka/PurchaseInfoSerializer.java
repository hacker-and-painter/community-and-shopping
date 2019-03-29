package com.beautifulsoup.chengfeng.kafka;

import com.beautifulsoup.chengfeng.service.dto.PurchaseInfoDto;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class PurchaseInfoSerializer  implements Serializer<PurchaseInfoDto> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public byte[] serialize(String topic, PurchaseInfoDto data) {
        if(data==null) {
            return null;
        }

        Schema schema = RuntimeSchema.getSchema(data.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] protostuff=null;
        try {
            protostuff= ProtostuffIOUtil.toByteArray(data, schema, buffer);

        }catch (Exception e) {
            log.error(e.getMessage());
        }finally {
            buffer.clear();
        }


        return protostuff;
    }

    @Override
    public void close() {}
}
