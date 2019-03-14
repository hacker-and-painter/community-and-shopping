package com.beautifulsoup.chengfeng.controller.purchase;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.CategoryVo;
import com.beautifulsoup.chengfeng.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value="商品分类",tags= {"商品分类Controller"},description = "商品分类",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value="获取父分类列表",notes="获取父分类列表")
    @GetMapping(value="/parent/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<CategoryVo>> getParentCategoryList() {
        List<CategoryVo> parentCategories = categoryService.getParentCategories();
        return ResponseResult.createBySuccess(parentCategories);
    }

    @ApiOperation(value="获取子分类列表",notes="获取子分类列表")
    @GetMapping(value="/children/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<CategoryVo>> getChildrenCategoryList() {
        List<CategoryVo> childrenCategories = categoryService.getChildrenCategories();
        return ResponseResult.createBySuccess(childrenCategories);
    }


}
