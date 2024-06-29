package cz.schrek.tuzex.common.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LoggerUtils {

    fun Any.getLoggerForClass(): Logger = LoggerFactory.getLogger(this::class.simpleName)
}
