package com.voyagersoft.mockbot.api.common.model.entity

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.voyagersoft.mockbot.api.project.model.entity.Project
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.jetbrains.annotations.NotNull
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class CommonEntity {

    @NotNull
    @CreationTimestamp
    @Comment("최초 등록일")
    var createDate: String = ""

    @NotNull
    @CreationTimestamp
    @Comment("최종 수정일")
    var updateDate: String = ""

    @Column(nullable = false)
    @Comment("최초 등록자")
    var createUserId: Long = 0
}