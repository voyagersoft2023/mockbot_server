package com.voyagersoft.mockbot.apis.test.repository

import com.voyagersoft.mockbot.apis.api.model.entity.Api
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface TestRepository : JpaRepository<Api?, Long?>, JpaSpecificationExecutor<Api?> {

}