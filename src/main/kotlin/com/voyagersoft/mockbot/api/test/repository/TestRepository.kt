package com.voyagersoft.mockbot.api.test.repository

import com.voyagersoft.mockbot.api.api.model.dto.response.ApiRequestDto
import com.voyagersoft.mockbot.api.api.model.entity.Api
import com.voyagersoft.mockbot.api.api.model.entity.ApiRequest
import com.voyagersoft.mockbot.api.api.model.entity.ApiResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface TestRepository : JpaRepository<Api?, Long?>, JpaSpecificationExecutor<Api?> {

}