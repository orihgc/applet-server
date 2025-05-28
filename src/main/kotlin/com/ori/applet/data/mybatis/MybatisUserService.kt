package com.ori.applet.data.mybatis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MybatisUserService : UserMapper {

    @Autowired
    private lateinit var userMapper: UserMapper

    override fun findById(id: Long?): MybatisUser? {
        return userMapper.findById(id)
    }

    override fun findByName(id: Long): MybatisUser? {
        return userMapper.findByName(id)
    }

}