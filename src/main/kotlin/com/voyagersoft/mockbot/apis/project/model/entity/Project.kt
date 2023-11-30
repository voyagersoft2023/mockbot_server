package com.voyagersoft.mockbot.apis.project.model.entity

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.voyagersoft.mockbot.utils.common.model.entity.CommonEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.jetbrains.annotations.NotNull


@Entity
@Table(
    name = "Projects", indexes = [
        Index(name = "i_projects_id", columnList = "id"),
    ]
)
@DynamicInsert
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
class Project: CommonEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("프로젝트ID")
    var id: Long = 0

    @NotNull
    @Comment("프로젝트명")
    var name: String = ""

    @Comment("context")
    var context: String = ""

    @Comment("접근권한")
    var accessAuthority: String = ""

    @Comment("설명")
    var explanation: String = ""
}