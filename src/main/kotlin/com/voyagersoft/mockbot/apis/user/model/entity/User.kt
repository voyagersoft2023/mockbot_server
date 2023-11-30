package com.voyagersoft.mockbot.apis.user.model.entity

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
    name = "Users", indexes = [
        Index(name = "i_users_email", columnList = "email"),
    ]
)
@DynamicInsert


















































































































@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "email")
class User: CommonEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("사용자 번호")
    var id: Long = 0

    @Column(unique = true, nullable = false)
    @NotNull
    @Comment("사용자 이메일")
    var email: String = ""

    @Column(nullable = false)
    @NotNull
    @Comment("이름")
    var name: String = ""

    @Comment("이전 패스워드")
    var prePassword: String = ""

    @Comment("패스워드")
    @NotNull
    var password: String = ""

    @Comment("휴대폰번호")
    var phoneNumber: String = ""

    @Comment("회사명")
    var company: String = ""

    @Comment("사용여부")
    var useYn: String = ""

    @Comment("약관동의여부")
    var privacy: String = ""

    @Comment("마지막 로그인 일자")
    var lastLoginDt: String = ""

    @Comment("권한")
    var role: String = ""

    @Column(nullable = false)
    @Comment("소셜ID")
    var socialId: String = ""

    @Column(nullable = false)
    @NotNull
    @Comment("로그인 경로")
    var loginType: String = ""
}