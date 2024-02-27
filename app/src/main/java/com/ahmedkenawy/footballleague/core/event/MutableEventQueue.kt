package com.ahmedkenawy.footballleague.core.event

import com.ahmedkenawy.footballleague.core.base.BaseCoroutineDispatchers
import com.ahmedkenawy.footballleague.utils.Constants.Configuration.FLOW_BUFFER_CAPACITY
import com.ahmedkenawy.footballleague.utils.Constants.Configuration.FLOW_REPLAY_CACHE
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class MutableEventQueue<T> @Inject constructor(
    private val dispatcher: BaseCoroutineDispatchers,
    capacity: Int = FLOW_REPLAY_CACHE,
    extraBufferCapacity: Int = FLOW_BUFFER_CAPACITY,
) : EventQueue<T> {

    private val innerQueue = MutableSharedFlow<Event<T>>(
        capacity,
        extraBufferCapacity,
        BufferOverflow.SUSPEND
    )

    /**
     * push single event which consumer can consume just once.
     * Even if the event still exists in the replay cache it will be filtered out to the consumer.
     * */
    suspend fun pushSingle(state: T) {
        pushToReplayCache(SingleEvent(state))
    }

    private suspend fun pushToReplayCache(state: Event<T>) {
        if (!innerQueue.tryEmit(state)) innerQueue.emit(state)
    }

    /**
     * push replayed event which consumers can consume multiple times through the replay cache.
     * Consumer will always receive the event whenever it gets pushed.
     * */
    suspend fun push(state: T) {
        pushToReplayCache(ReplayedEvent(state))
    }

    override fun getFor(consumerId: String): Flow<T> = innerQueue.transform { state ->
        state.getContent(consumerId)?.let { emit(it) }
    }.flowOn(dispatcher.Main)
}
