package com.ahmedkenawy.footballleague.network


import android.content.Context
import com.ahmedkenawy.footballleague.utils.Constants.Network.AUTHRIZATION_TYPE
import com.ahmedkenawy.footballleague.utils.Constants.Network.AUTHRIZATION_TYPE_VALUE
import com.ahmedkenawy.footballleague.utils.Constants.Network.HEADER_CONTENT_TYPE
import com.ahmedkenawy.footballleague.utils.Constants.Network.HEADER_CONTENT_TYPE_VALUE
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


/**
 * Interceptor class responsible for adding headers to network requests.
 *
 * @param context The application context provided by Dagger Hilt for accessing resources and system services.
 */
class NetworkHeaderInterceptor @Inject constructor(
    @ApplicationContext val context: Context
) : Interceptor {

    /**
     * Intercepts the network request and adds headers before proceeding.
     *
     * @param chain The interceptor chain.
     * @return The response of the network request.
     */
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        // Add headers to the request builder
        chain.request().newBuilder()
            .addHeader(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE) // Content-Type header
            .addHeader(
                AUTHRIZATION_TYPE,
                AUTHRIZATION_TYPE_VALUE
            ) // Authorization header
            .build() // Build the modified request
    )
}