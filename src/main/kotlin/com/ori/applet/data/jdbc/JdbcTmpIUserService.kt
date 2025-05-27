package com.ori.applet.data.jdbc

interface JdbcTmpIUserService {
    fun getUser(id: Long): JdbcUser?

    fun findUsers(userName:String,note:String):List<JdbcUser>

    fun insertUser(jdbcUser: JdbcUser)

    fun updateUser(jdbcUser: JdbcUser)

    fun deleteUser(id: Long)
}