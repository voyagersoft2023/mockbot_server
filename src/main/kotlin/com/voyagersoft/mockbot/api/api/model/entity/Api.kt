package com.voyagersoft.mockbot.api.api.model.entity

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
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
class Api {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("API ID")
    var id: Long = 0

    @Comment("프로젝트ID")
    var projectId: Long = 0

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

    @OneToMany(mappedBy = "api", fetch = FetchType.LAZY)
    val apiRequests: List<ApiRequest> = ArrayList<ApiRequest>()

}