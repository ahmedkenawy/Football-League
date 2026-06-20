package com.ahmedkenawy.footballleague.network

import java.io.IOException

class SessionExpiredException : IOException() {
    override val message: String get() = "Session has expired"
}
