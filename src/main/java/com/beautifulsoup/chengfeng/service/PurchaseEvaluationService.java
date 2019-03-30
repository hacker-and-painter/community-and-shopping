package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.EvaluationVo;
import com.beautifulsoup.chengfeng.service.dto.EvaluationDto;
import org.springframework.validation.BindingResult;

public interface PurchaseEvaluationService {
    EvaluationVo publishNewEvaluation(EvaluationDto evaluationDto, BindingResult result);
}
