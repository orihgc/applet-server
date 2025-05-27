package com.ori.applet.data.mybatis

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select


@Mapper
interface UserMapper {

    // 方式1：使用注解编写SQL
    @Select("SELECT * FROM users WHERE id = #{id}")
    fun findById(id: Long?): MybatisUser?

    // 方式2：在XML中编写SQL（方法名需与XML中id一致）
    fun findByName(id:Long): MybatisUser?
}