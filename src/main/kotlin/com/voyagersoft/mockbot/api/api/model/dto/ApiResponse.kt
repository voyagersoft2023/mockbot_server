package com.voyagersoft.mockbot.api.api.model.dto

import com.voyagersoft.mockbot.api.api.model.entity.ApiRequest
import org.hibernate.annotations.Comment
import org.jetbrains.annotations.NotNull

class ApiResponse{
    var id: Long = 0

    var projectId: Long = 0

    var apiName: String = ""

    var groupYn: String = ""

    var upperId: Long = 0

    var depth: Long = 0

    var order: Long = 0

    /*var apiRequests: List<ApiRequest> = ArrayList<ApiRequest>()*/

}