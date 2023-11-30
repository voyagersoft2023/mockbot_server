package com.voyagersoft.mockbot.api.api.model.dto.response

import com.voyagersoft.mockbot.api.api.model.entity.Api

class ApiRequestDto{
    var id: Long = 0

    var key: String = ""

    var name: String = ""

    var dataType: String = ""

    var upperId: Long = 0

    var depth: Long = 0

    var order: Long = 0

    var jsonData: String = ""

    var api: ApiDto? = null
}