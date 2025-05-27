package com.ori.applet.data.mybatis

import org.springframework.stereotype.Service

@Service
class MybatisUserService(val userMapper: UserMapper) {

    fun getUser(id: Long): MybatisUser? {
        return userMapper.findById(id)
    }

    fun findByName(id:Long): MybatisUser? {
        return userMapper.findByName(id)
    }

}