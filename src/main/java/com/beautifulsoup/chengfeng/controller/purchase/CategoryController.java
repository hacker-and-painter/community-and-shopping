package com.beautifulsoup.chengfeng.controller.purchase;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.CategoryVo;
import com.beautifulsoup.chengfeng.pojo.PurchaseCategory;
import com.beautifulsoup.chengfeng.service.PurchaseCategoryService;
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

@Api(value="商品分类",tags= {"商品分类Controller"},description = "商品分类",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private PurchaseCategoryService purchaseCategoryService;

    @ApiOperation(value="获取父分类列表",notes="获取父分类列表")
    @GetMapping(value="/parent/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<CategoryVo>> getParentCategoryList() {
        List<CategoryVo> parentCategories = purchaseCategoryService.getParentCategories();
        return ResponseResult.createBySuccess(parentCategories);
    }

    @ApiOperation(value="获取子分类列表",notes="获取子分类列表")
    @GetMapping(value="/children/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<CategoryVo>> getChildrenCategoryList() {
        List<CategoryVo> childrenCategories = purchaseCategoryService.getChildrenCategories();
        return ResponseResult.createBySuccess(childrenCategories);
    }

    @ApiOperation(value="获取首页内容",notes="首页展示内容")
    @GetMapping(value="/index/{categoryId}/{pageNum}/{pageSize}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<PurchaseCategory>> getPurchaseIndexInfo(@PathVariable("categoryId")Integer categoryId
    ,@PathVariable(value = "pageNum")Integer pageNum,@PathVariable(value = "pageSize")Integer pageSize) {
        List<PurchaseCategory> childrenCategories = purchaseCategoryService.getPurchaseIndexInfo(categoryId,pageNum,pageSize);
        return ResponseResult.createBySuccess(childrenCategories);
    }



}
