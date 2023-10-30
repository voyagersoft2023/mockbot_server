package com.voyagersoft.mockbot.api.user.repository

import com.voyagersoft.mockbot.api.user.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepository : JpaRepository<User?, Long?>, JpaSpecificationExecutor<User?> {
    @Throws(Exception::class)
    fun findBySocialId(socialId: String): User?
}