package com.koonsland.kotlinspringshop.dto

import com.koonsland.kotlinspringshop.domain.Member

data class MemberReqDto(
    val name: String,
    val age: Int
)

data class MemberRespDto(
    val name: String,
    val age: Int
) {
    constructor(member: Member): this(member.name, member.age)
}
