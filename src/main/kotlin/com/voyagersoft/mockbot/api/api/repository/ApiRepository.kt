package com.voyagersoft.mockbot.api.api.repository

import com.voyagersoft.mockbot.api.api.model.dto.response.ApiRequestDto
import com.voyagersoft.mockbot.api.api.model.entity.Api
import com.voyagersoft.mockbot.api.api.model.entity.ApiRequest
import com.voyagersoft.mockbot.api.api.model.entity.ApiResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface ApiRepository : JpaRepository<Api?, Long?>, JpaSpecificationExecutor<Api?> {

    fun findByProjectId(projectId: Long): List<Api>

    @Query(
        "select a from ApiRequest a left join fetch a.api"
    )
    fun loadApiRequest(): List<ApiRequest>

    @Query(
        "select a from ApiResponse a left join fetch a.api"
    )
    fun loadApiResponse(): List<ApiResponse>


}