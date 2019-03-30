package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.EvaluationVo;
import com.beautifulsoup.chengfeng.service.dto.EvaluationDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface PurchaseEvaluationService {
    EvaluationVo publishNewEvaluation(EvaluationDto evaluationDto, BindingResult result);
    List<EvaluationVo> getAllEvaluations(Integer productId,Integer pageNum,Integer pageSize);
    List<EvaluationVo> getGoodEvaluations(Integer productId,Integer pageNum,Integer pageSize);
}
