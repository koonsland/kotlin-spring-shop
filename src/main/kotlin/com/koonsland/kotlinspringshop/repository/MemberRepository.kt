package com.koonsland.kotlinspringshop.repository

import com.koonsland.kotlinspringshop.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MemberRepository: JpaRepository<Member, Long>{
    fun findByName(name: String): Optional<Member>
}