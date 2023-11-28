package com.voyagersoft.mockbot.api.api.repository

import com.voyagersoft.mockbot.api.api.model.dto.ApiResponse
import com.voyagersoft.mockbot.api.api.model.entity.Api
import com.voyagersoft.mockbot.api.api.model.entity.ApiRequest
import com.voyagersoft.mockbot.api.user.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface ApiRepository : JpaRepository<Api?, Long?>, JpaSpecificationExecutor<Api?> {


    @Query(
        "select a, b from Api a left join a.apiRequests b"
        /*"select a from Api a "*/
    )
    fun testSelect(): List<Api>

}