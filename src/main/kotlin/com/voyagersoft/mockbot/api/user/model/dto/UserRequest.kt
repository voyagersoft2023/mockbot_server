package com.voyagersoft.mockbot.api.user.model.dto

class UserRequest {

    var id: Int = 0

    lateinit var name: String

    lateinit var email: String

    lateinit var password: String

    lateinit var company: String

    lateinit var privacy: String

    lateinit var role: String

    lateinit var social_id: String

    lateinit var use_yn: String

    lateinit var last_login_dt: String

    lateinit var login_type: String
}