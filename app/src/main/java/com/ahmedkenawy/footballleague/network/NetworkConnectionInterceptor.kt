package com.ahmedkenawy.footballleague.network

import android.content.Context
import android.net.ConnectivityManager
import com.ahmedkenawy.footballleague.MyApplication
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Interceptor class responsible for checking network connection before making a network request.
 */
class NetworkConnectionInterceptor @Inject constructor() : Interceptor {

    /**
     * Checks if the device is connected to the internet.
     *
     * @return true if the device is connected, false otherwise.
     */
    private val isConnected: Boolean
        get() {
            val connectivityManager =
                MyApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

    /**
     * Intercepts the network request and checks for network connection.
     *
     * @param chain The interceptor chain.
     * @return The response of the network request.
     * @throws NoConnectionException If there is no network connection.
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        // Check if device is connected to the internet
        if (!isConnected) throw NoConnectionException()

        // Proceed with the network request if device is connected
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}