package com.voyagersoft.mockbot.apis.api.model.entity

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.voyagersoft.mockbot.utils.common.model.entity.CommonEntity
import com.voyagersoft.mockbot.apis.project.model.entity.Project
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.jetbrains.annotations.NotNull


@Entity
@Table(
    name = "Apis", indexes = [
        Index(name = "i_apis_id", columnList = "id"),
    ]
)
@DynamicInsert
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
class Api: CommonEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("API ID")
    var id: Long = 0

    @NotNull
    @Comment("API명")
    var apiName: String = ""

    @NotNull
    @Comment("그룹여부")
    var groupYn: String = ""

    @Comment("상위 API ID")
    var upperId: Long = 0

    @Comment("트리 깊이")
    @NotNull
    var depth: Long = 0

    @Comment("트리 순서")
    var order: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    var project: Project? = null

}