package com.koonsland.kotlinspringshop.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member(
    val name: String,
    val age: Int,
) {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    val id: Long? = null
}