package com.ori.applet.data

data class User(val id: Long? = null, val userName: String? = null, val sex: SexEnum? = null, val note: String? = null)

enum class SexEnum(val id: Int, val nameStr: String) {
    MALE(1, "男"), FEMALE(2, "女");

    companion object {
        fun getSexById(id: Int): SexEnum {
            return SexEnum.values()[id]
        }
    }
}
