package com.cping.cpingblog.service.impl;

import com.cping.cpingblog.entity.User;
import com.cping.cpingblog.mapper.UserMapper;
import com.cping.cpingblog.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
