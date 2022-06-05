package com.koonsland.kotlinspringshop.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member(
    var name: String,
    var age: Int,
) {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    val id: Long? = null

    //== 비지니스 로직==//
    fun updateMember(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}