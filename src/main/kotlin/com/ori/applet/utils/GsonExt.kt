package com.ori.applet.utils

import com.google.gson.Gson

val gson = Gson()

fun Any.toJson(): String = gson.toJson(this)