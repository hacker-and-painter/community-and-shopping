package com.beautifulsoup.chengfeng.controller.purchase;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.EvaluationVo;
import com.beautifulsoup.chengfeng.service.PurchaseEvaluationService;
import com.beautifulsoup.chengfeng.service.dto.EvaluationDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Api(value="商品评价",tags= {"商品评价Controller"},description = "商品评价",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/evaluation")
public class EvaluationController {

    @Autowired
    private PurchaseEvaluationService evaluationService;

    @PostMapping(value = "/publish",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<EvaluationVo> publishNewEvaluation(@Valid @RequestBody EvaluationDto evaluationDto,
                                                             BindingResult result){

        EvaluationVo evaluationVo=evaluationService.publishNewEvaluation(evaluationDto,result);

        if (evaluationVo != null) {
            return ResponseResult.createBySuccess("发表评价成功",evaluationVo);
        }

        return ResponseResult.createByErrorMessage("发表评价失败");
    }


}
