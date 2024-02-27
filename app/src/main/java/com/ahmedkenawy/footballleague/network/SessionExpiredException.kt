package com.ahmedkenawy.footballleague.network

import com.ahmedkenawy.footballleague.MyApplication
import com.ahmedkenawy.footballleague.R
import java.io.IOException

class SessionExpiredException : IOException() {

    // You can send any message whatever you want from here.
    override val message: String
        get() = MyApplication.instance.resources.getString(R.string.network_session_has_expired)
}