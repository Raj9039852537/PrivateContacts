/*
 * Private Contacts
 * Copyright (c) 2022.
 * Florian Gubler
 */

package ch.abwesend.privatecontacts.domain.lib.logging

import android.util.Log

class LogcatLogger(
    override val loggingTag: String,
    override val logToCrashlytics: () -> Boolean,
    private val prefix: String? = null,
) : AbstractLogger() {
    override fun verboseImpl(messages: Collection<String>) {
        for (message in messages) {
            Log.v(loggingTag, prefix(message))
        }
    }

    override fun debugImpl(messages: Collection<String>) {
        for (message in messages) {
            Log.d(loggingTag, prefix(message))
            LogCache.tryAddLog(LogLevel.DEBUG, message)
        }
    }

    override fun infoImpl(messages: Collection<String>) {
        for (message in messages) {
            Log.i(loggingTag, prefix(message))
            LogCache.tryAddLog(LogLevel.INFO, message)
        }
    }

    override fun warningImpl(messages: Collection<String>) {
        for (message in messages) {
            Log.w(loggingTag, prefix(message))
            LogCache.tryAddLog(LogLevel.WARNING, message)
        }
    }

    override fun errorImpl(messages: Collection<String>) {
        for (message in messages) {
            Log.e(loggingTag, prefix(message))
            LogCache.tryAddLog(LogLevel.ERROR, message)
        }
    }

    private fun prefix(message: String) =
        prefix?.let { "${it.take(30)}: $message" } ?: message
}
