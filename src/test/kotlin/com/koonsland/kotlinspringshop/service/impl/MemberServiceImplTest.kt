package com.koonsland.kotlinspringshop.service.impl

import com.koonsland.kotlinspringshop.domain.Member
import com.koonsland.kotlinspringshop.dto.MemberReqDto
import com.koonsland.kotlinspringshop.repository.MemberRepository
import com.koonsland.kotlinspringshop.service.MemberService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class MemberServiceImplTest(
    @Autowired val memberService: MemberService,
    @Autowired val memberRepository: MemberRepository
) {

    @Test
    fun `save member`()
    {
        //given
        val newMember = MemberReqDto("test1", 30)

        //when
        val memberId = memberService.addMember(newMember)
        val findMember = memberRepository.findById(memberId).get()

        //then
        assertEquals(memberId, findMember.id)
    }


    @Test
    fun `member save fail`()
    {
        //given
        val member = Member("koon", 30)
        memberRepository.save(member)
        val newMember = MemberReqDto("koon", 30)

        //when
        val assertThrows = assertThrows(IllegalStateException::class.java) {
            memberService.addMember(newMember)
        }

        //then
        assertEquals(assertThrows.message, "이미 존재하는 사용자입니다")
    }

    @Test
    fun `update member`()
    {
        //given
        val newMember = MemberReqDto("test1", 30)
        val memberId = memberService.addMember(newMember)
        val updateMember = MemberReqDto("test2", 31)

        //when
        memberService.updateMember(memberId, updateMember)
        val findMember = memberRepository.findById(memberId).get()

        //then
        assertEquals(findMember.name, updateMember.name)
        assertEquals(findMember.id, memberId)
        assertEquals(findMember.age, updateMember.age)
    }
}