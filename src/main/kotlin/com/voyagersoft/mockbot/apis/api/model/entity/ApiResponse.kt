package com.voyagersoft.mockbot.apis.api.model.entity

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.voyagersoft.mockbot.apis.common.model.entity.CommonEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.jetbrains.annotations.NotNull


@Entity
@Table(
    name = "ApiResponses", indexes = [
        Index(name = "i_apiResponses_id", columnList = "id"),
    ]
)
@DynamicInsert
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
class ApiResponse: CommonEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("ID")
    var id: Long = 0

    @NotNull
    @Comment("KEY")
    var key: String = ""

    @NotNull
    @Comment("API REQUEST명")
    var name: String = ""

    @NotNull
    @Comment("DATA TYPE")
    var dataType: String = ""

    @Comment("상위 API RESPONSE ID")
    var upperId: Long = 0

    @Comment("깊이")
    @NotNull
    var depth: Long = 0

    @Comment("순서")
    var order: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_id")
    var api: Api? = null
}