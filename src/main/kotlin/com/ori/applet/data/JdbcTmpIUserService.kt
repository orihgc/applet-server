package com.ori.applet.data

interface JdbcTmpIUserService {
    fun getUser(id: Long): User?

    fun findUsers(userName:String,note:String):List<User>

    fun insertUser(user: User)

    fun updateUser(user: User)

    fun deleteUser(id: Long)
}