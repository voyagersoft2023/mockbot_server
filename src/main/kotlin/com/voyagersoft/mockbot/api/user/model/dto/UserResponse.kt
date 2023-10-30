package com.voyagersoft.mockbot.api.user.model.dto

import com.voyagersoft.mockbot.api.jwt.model.dto.JwtDto

class UserResponse: JwtDto() {
    var id: Int = 0
}