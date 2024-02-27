package com.ahmedkenawy.footballleague.network

import com.ahmedkenawy.footballleague.R
import com.ahmedkenawy.footballleague.utils.Message


sealed class NetworkError(
    val title: Message,
    val messageBody: Message,
    val isRetryEnabled: Boolean = false,
//    val placeholderId: Int = R.drawable.ic_error_placeholder,
    val code: String = "-1"
) : Throwable() {

    object NoConnection : NetworkError(
        Message(R.string.network_no_connection),
        Message(R.string.network_check_your_mobile_data_or_wi_fi),
        true
    )

    object NoResults : NetworkError(
        Message(R.string.no_results_found),
        Message(R.string.network_please_try_again_later),
        true,
//        R.drawable.ic_empty
    )

    object Cancellation : NetworkError(
        Message(R.string.network_something_went_wrong), Message(R.string.network_error_occurred)
    )

    object UnExpected : NetworkError(
        Message(R.string.network_something_went_wrong),
        Message(R.string.network_unexpected_error_happened)
    )

    object TimeOut : NetworkError(
        Message(R.string.network_server_cannot_be_reached),
        Message(R.string.network_please_try_again_later)
    )

    object Parsing : NetworkError(
        Message(R.string.network_server_error), Message(R.string.network_unexpected_error_happened)
    )

    object SessionExpired : NetworkError(
        Message(R.string.network_session_has_expired), Message(R.string.network_session_has_expired)
    )

    data class ServerError(val errorMsg: Message, val errorCode: String = "-1") :
        NetworkError(Message(R.string.network_server_error), errorMsg, code = errorCode)
}