package com.koonsland.kotlinspringshop.repository

import com.koonsland.kotlinspringshop.domain.Member
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemberRepositoryTest(
    @Autowired val memberRepository: MemberRepository
) {

    @Test
    @DisplayName("회원 저장 성공")
    fun `member save`()
    {
        //given
        val member = Member("koon", 30)

        //when
        val savedMember = memberRepository.save(member)

        //then
        assertEquals(savedMember.id, member.id)
        assertEquals(savedMember.name, member.name)
    }

    @Test
    @DisplayName("회원 Id 조회")
    fun `member find by id`() {
        // given
        val member = Member("koon", 30)
        memberRepository.save(member)

        // when
        val findMember = memberRepository.findById(member.id!!)
            .orElseThrow { IllegalStateException("Member not found.") }

        // then
        assertEquals(findMember.id, member.id)
        assertEquals(findMember.name, member.name)
    }

    @Test
    @DisplayName("회원 이름 조회")
    fun `member find by name`() {
        // given
        val member = Member("koon", 30)
        memberRepository.save(member)

        // when
        val findMember = memberRepository.findByName(member.name)
            .orElseThrow { IllegalStateException("Member not found.") }

        // then
        assertEquals(findMember.id, member.id)
        assertEquals(findMember.name, member.name)
    }
}