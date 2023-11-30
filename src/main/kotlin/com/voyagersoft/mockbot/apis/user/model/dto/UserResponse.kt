package com.voyagersoft.mockbot.apis.user.model.dto

import com.voyagersoft.mockbot.apis.jwt.model.dto.JwtDto

class UserResponse: JwtDto() {
    var id: Int = 0
}