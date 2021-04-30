package com.cping.cpingblog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cping.cpingblog.common.lang.Result;
import com.cping.cpingblog.entity.Blog;
import com.cping.cpingblog.entity.vo.BlogVo;
import com.cping.cpingblog.service.BlogService;
import com.cping.cpingblog.util.ShiroUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chonghe
 * @since 2021-03-04
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

//    @GetMapping("/blogs")
//    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
//
//        Page page = new Page(currentPage, 5);
//        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
//
//        return Result.succ(pageData);
//    }
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam String headerSearchTitle) {
        //new一个分页对象
        Page page = new Page(currentPage,5);
        //new一个条件构造器
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        //构造条件
//        //.like模糊查询
//        if (headerSearchTitle != null) {
//            wrapper.like("title", headerSearchTitle);
//        }
//        //.eq 等于 精确查询
//        if (!StringUtils.isEmpty(vo.getBlogType())) {
//            wrapper.eq("blog_type", vo.getBlogType());
//        }

        IPage pageData = blogService.page(page,wrapper.orderByDesc("created").like("title", headerSearchTitle));
        return Result.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已被删除");

        return Result.succ(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {

//        Assert.isTrue(false, "公开版不能任意编辑！");

        Blog temp = null;
        if(blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的文章
            System.out.println(ShiroUtil.getProfile().getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");

        } else {

            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);

        return Result.succ(null);
    }
}
