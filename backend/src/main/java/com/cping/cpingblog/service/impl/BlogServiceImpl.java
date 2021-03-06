package com.cping.cpingblog.service.impl;

import com.cping.cpingblog.entity.Blog;
import com.cping.cpingblog.mapper.BlogMapper;
import com.cping.cpingblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chonghe
 * @since 2021-03-04
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
