package com.ori.applet.data.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface JpsUserRepository:JpaRepository<JpaUser,Long> {

    @Query("""from user where userName like concat('%',?1,'%') and note like concat('%',?2,'%')""")
    fun findUsers(userName:String,note:String):List<JpaUser>

    fun getJpaUserById(id: Long): JpaUser?

    fun findByUserNameLikeOrNoteLike(userName: String, note: String): List<JpaUser>
}