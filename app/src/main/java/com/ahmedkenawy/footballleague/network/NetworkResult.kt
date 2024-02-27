package com.ahmedkenawy.footballleague.network

import com.ahmedkenawy.footballleague.core.mapper.Mapper

/**
 * Sealed class representing the different states of an operation.
 *
 * @param value The value associated with the state. Can be null for failure state.
 */
sealed class State<out T>(val value: T?) {

    /**
     * Subclass of State representing a successful operation with associated data.
     *
     * @param data The data associated with the success state.
     */
    data class Success<out T>(val data: T) : State<T>(data)

    /**
     * Subclass of State representing a failed operation with an associated throwable.
     *
     * @param throwable The throwable associated with the failure state.
     */
    data class Failure(var throwable: Throwable) : State<Nothing>(null)
}

/**
 * Extension function to transform the elements of a list within a Success state to a new type.
 *
 * @param mapper The mapper function to transform elements.
 * @return A new State with transformed list elements if the original state is Success, otherwise returns the original Failure state.
 */
fun <T, R> State<MutableList<T>>.mapList(mapper: Mapper<T, R>): State<MutableList<R>> {
    return when (this) {
        is State.Success -> State.Success(data.map { mapper.map(it) }.toMutableList())
        is State.Failure -> this
    }
}

/**
 * Extension function to perform an action on the data if the state is Success.
 *
 * @param block The action to perform on the data.
 * @return The original state with the action performed if the state is Success, otherwise returns the original Failure state.
 */
suspend inline fun <T : Any> State<T>.withSuccess(
    block: T.() -> Unit
): State<T> {
    if (this is State.Success) block.invoke(data)
    return this
}

/**
 * Extension function to map the data to a new state if the current state is Success.
 *
 * @param block The mapping function to map the data to a new state.
 * @return The new state mapped from the data if the current state is Success, otherwise returns the original Failure state.
 */
suspend inline fun <T : Any, R : Any> State<T>.mapOnSuccess(
    block: (T) -> State<R>
): State<R> {
    return when (this) {
        is State.Success -> block.invoke(data)
        is State.Failure -> this
    }
}

/**
 * Function to handle the result of an operation by providing callbacks for success and error cases.
 *
 * @param onError The callback function to be invoked in case of failure.
 * @param onSuccess The callback function to be invoked in case of success.
 */
suspend inline fun <T : Any> State<T>.process(
    noinline onError: ((errorMsg: String?) -> Unit)? = null, onSuccess: (data: T) -> Unit
) {
    when (this@process) {
        is State.Success -> onSuccess.invoke(data)
        is State.Failure -> {
            if (onError == null && throwable.message?.contains("Job was cancelled") == false)
                onError?.invoke(throwable.message)
        }
    }
}

/**
 * Extension function to map the data of a Success state to a new type.
 *
 * @param mapper The mapper function to map the data to a new type.
 * @return A new State with the data mapped to a new type if the original state is Success, otherwise returns the original Failure state.
 */
inline fun <T, R> State<T>.map(mapper: Mapper<T, R>): State<R> {
    return when (this) {
        is State.Success -> State.Success(mapper.map(data))
        is State.Failure -> this
    }
}
