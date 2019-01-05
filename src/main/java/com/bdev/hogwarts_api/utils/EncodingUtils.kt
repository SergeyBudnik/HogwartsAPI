package com.bdev.hogwarts_api.utils

import java.nio.charset.Charset
import java.util.Base64.getDecoder
import java.util.Base64.getEncoder

object EncodingUtils {
    fun toBase64(s: String): String {
        return getString(getEncoder().encode(getBytes(s)))
    }

    fun fromBase64(s: String): String {
        return getString(getDecoder().decode(getBytes(s)))
    }

    private fun getString(ba: ByteArray): String {
        return String(ba, Charset.forName("UTF-8"))
    }

    private fun getBytes(s: String): ByteArray {
        return s.toByteArray(charset("UTF-8"))
    }
}
