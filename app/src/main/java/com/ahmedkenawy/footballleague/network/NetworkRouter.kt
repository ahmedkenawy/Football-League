package com.ahmedkenawy.footballleague.network


/**
 * Object responsible for making network calls and handling responses.
 */
object NetworkRouter {

    /**
     * Invokes a suspend function representing a network call and handles its response.
     *
     * @param call The suspend function representing the network call.
     * @return The state representing the result of the network call.
     */
    suspend fun <R : Any> invokeCall(call: suspend () -> ApiResponse<R>): State<R> {
        return try {
            val response = call.invoke()
            if (isSuccessResponse(response)) State.Success(response.responseData)
            else throw (Throwable("Error Happens"))
        } catch (exception: Throwable) {
            exception.printStackTrace()
            if (exception.message.toString().contains("401 Unauthorized")) State.Failure(
                SessionExpiredException()
            )
            else State.Failure(exception)
        }
    }

    /**
     * Checks if the response is successful.
     *
     * @param response The API response.
     * @return true if the response is successful, false otherwise.
     */
    private fun <R> isSuccessResponse(response: ApiResponse<R>?): Boolean {
        return response != null
    }
}
