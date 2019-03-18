package com.beautifulsoup.chengfeng.utils;

import com.beautifulsoup.chengfeng.exception.ParamException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import java.util.List;

@Slf4j
public class ParamValidatorUtil {

    public static void validateBindingResult(BindingResult result){

        if (result.hasErrors()){
//            List<String> errors= Lists.newArrayList();
            result.getAllErrors().stream().forEach(error->{
                String errorMsg = error.getDefaultMessage();
                log.error(errorMsg);
                throw new ParamException(error.getDefaultMessage());
//                errors.add(errorMsg);
            });
//            if(!CollectionUtils.isEmpty(errors)) {
//
//            }
        }
    }
}
