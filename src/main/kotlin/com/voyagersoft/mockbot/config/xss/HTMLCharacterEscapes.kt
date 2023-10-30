package com.voyagersoft.mockbot.config.xss

import com.fasterxml.jackson.core.SerializableString
import com.fasterxml.jackson.core.io.CharacterEscapes
import com.fasterxml.jackson.core.io.SerializedString
import org.apache.commons.text.StringEscapeUtils

class HtmlCharacterEscapesBack : CharacterEscapes() {

    override fun getEscapeCodesForAscii(): IntArray {
        // 2023.10.20[shiningtrue]: XSS 방지 처리할 특수 문자 지정
        val asciiEscapes: IntArray = standardAsciiEscapesForJSON()
        asciiEscapes['<'.code] = ESCAPE_CUSTOM
        asciiEscapes['>'.code] = ESCAPE_CUSTOM
        asciiEscapes['\"'.code] = ESCAPE_CUSTOM
        asciiEscapes['('.code] = ESCAPE_CUSTOM
        asciiEscapes[')'.code] = ESCAPE_CUSTOM
        asciiEscapes['#'.code] = ESCAPE_CUSTOM
        asciiEscapes['\''.code] = ESCAPE_CUSTOM
        return asciiEscapes
    }

    override fun getEscapeSequence(ch: Int): SerializableString {
        return SerializedString(
            StringEscapeUtils.escapeHtml4(
                ch.toChar().toString()
            )
        )
    }
}
