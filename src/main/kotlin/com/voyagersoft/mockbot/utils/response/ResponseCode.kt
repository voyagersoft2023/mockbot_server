package com.voyagersoft.mockbot.utils.response

enum class ResponseCode(val code: String, val message: String) {
    SUCCESS("0001", "성공"),
    TOKEN_ERROR("9000", "토큰 에러"),
    FORBIDDEN("0403", "접근 권한이 없습니다."),
    ERROR("9999", "에러가 발생하였습니다. 관리자에게 문의바랍니다.")
}