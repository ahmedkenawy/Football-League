package com.ahmedkenawy.footballleague.core.event

import java.util.concurrent.CopyOnWriteArraySet

class SingleEvent<out T>(override val content: T) : Event<T> {

    private val handlers = CopyOnWriteArraySet<String>()

    /** @param asker Used to identify, whether this "asker" has already handled this Event.
     *
     * @return Event content or null if it has been already handled by asker
     */
    override fun getContent(asker: String): T? = if (handlers.add(asker)) content else null

}