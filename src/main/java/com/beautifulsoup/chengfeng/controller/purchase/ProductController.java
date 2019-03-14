package com.beautifulsoup.chengfeng.controller.purchase;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.CategoryVo;
import com.beautifulsoup.chengfeng.controller.vo.ProductVo;
import com.beautifulsoup.chengfeng.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value="商品信息",tags= {"商品信息Controller"},description = "商品信息",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value="查询商品信息",notes="根据分类id查询商品信息")
    @GetMapping(value="/list/{categoryId}/{pageSize}/{pageNum}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<ProductVo>> getProductsByCategoryId(
            @PathVariable("categoryId")Integer categoryId,@PathVariable("pageNum")Integer pageNum, @PathVariable("pageSize")Integer pageSize) {
        List<ProductVo> productVos = productService.findProductsByCategoryId(categoryId,pageNum,pageSize);
        return ResponseResult.createBySuccess(productVos);
    }


}
