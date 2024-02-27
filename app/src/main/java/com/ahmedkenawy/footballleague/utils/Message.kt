package com.ahmedkenawy.footballleague.utils

import android.content.Context


/**
 * Class representing a message that can be either a plain text or a string resource with optional format arguments.
 *
 * @property text The plain text message.
 * @property formatArgs The format arguments for string resources.
 * @property resource The string resource ID.
 */
class Message {
    var text: String = ""
    var formatArgs: String? = null
    var resource: Int = -1

    /**
     * Constructs a Message object with plain text.
     *
     * @param text The plain text message.
     */
    constructor(text: String) {
        this.text = text
    }

    /**
     * Constructs a Message object with a string resource ID.
     *
     * @param resource The string resource ID.
     */
    constructor(resource: Int) {
        this.resource = resource
    }

    /**
     * Constructs a Message object with a string resource ID and format arguments.
     *
     * @param resource The string resource ID.
     * @param formatArgs The format arguments for the string resource.
     */
    constructor(resource: Int, formatArgs: String) {
        this.resource = resource
        this.formatArgs = formatArgs
    }

    /**
     * Retrieves the value of the message, either plain text or formatted string resource.
     *
     * @param context The context to access resources.
     * @return The value of the message.
     */
    fun getValue(context: Context?): String {
        return if (resource == -1) text
        else formatArgs?.let { args ->
            context?.getString(resource, args)
        } ?: run { context?.getString(resource) ?: "" }
    }

    /**
     * Retrieves the value of the message with provided format arguments.
     *
     * @param context The context to access resources.
     * @param formatArgs The format arguments for the string resource.
     * @return The value of the message.
     */
    fun getValue(context: Context?, formatArgs: String): String {
        return if (resource == -1) text
        else context?.getString(resource, formatArgs) ?: ""
    }

    /**
     * Checks if this message is equal to another object.
     *
     * @param other The other object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Message

        if (text != other.text) return false
        if (resource != other.resource) return false

        return true
    }

    /**
     * Generates a hash code for this message.
     *
     * @return The hash code.
     */
    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + resource
        return result
    }
}
