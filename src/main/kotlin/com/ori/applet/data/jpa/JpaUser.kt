package com.ori.applet.data.jpa

import com.ori.applet.data.jdbc.SexEnum
import javax.persistence.*


@Entity(name = "user")
@Table(name = "t_user")
class JpaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(name="user_name")
    private val userName: String? = null

    private val note:String?=null

    @Convert(converter = SexConverter::class)
    private val sex: SexEnum?=null
}