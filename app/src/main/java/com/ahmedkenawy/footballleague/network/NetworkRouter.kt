package com.ahmedkenawy.footballleague.network

import retrofit2.HttpException

object NetworkRouter {

    suspend fun <R : Any> invokeCall(call: suspend () -> ApiResponse<R>): State<R> {
        return try {
            State.Success(call.invoke().responseData)
        } catch (e: HttpException) {
            if (e.code() == 401) State.Failure(SessionExpiredException())
            else State.Failure(e)
        } catch (exception: Throwable) {
            exception.printStackTrace()
            State.Failure(exception)
        }
    }
}
