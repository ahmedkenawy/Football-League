package com.ahmedkenawy.footballleague.core.event


open class ReplayedEvent<out T>(override val content: T) : Event<T> {

    override fun getContent(asker: String): T? = content

}