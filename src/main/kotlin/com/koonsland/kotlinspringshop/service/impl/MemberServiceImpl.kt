package com.koonsland.kotlinspringshop.service.impl

import com.koonsland.kotlinspringshop.domain.Member
import com.koonsland.kotlinspringshop.dto.MemberReqDto
import com.koonsland.kotlinspringshop.dto.MemberRespDto
import com.koonsland.kotlinspringshop.repository.MemberRepository
import com.koonsland.kotlinspringshop.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(
    val memberRepository: MemberRepository
): MemberService {

    @Transactional
    override fun addMember(memberReq: MemberReqDto): Long {
        val findMember = memberRepository.findByName(memberReq.name)
        if(findMember.isPresent)
            throw IllegalStateException("이미 존재하는 사용자입니다")

        val member = Member(memberReq.name, memberReq.age)

        memberRepository.save(member)

        return member.id!!
    }

    override fun findAllMember(): List<MemberRespDto> {
        val members = memberRepository.findAll()

        return members.map { MemberRespDto(it) }.toList()
    }

    override fun findMemberById(memberId: Long): MemberRespDto {
        val findMember = memberRepository.findById(memberId)
            .orElseThrow { IllegalStateException("회원을 찾을 수 없습니다") }

        return MemberRespDto(findMember)
    }

    override fun findMemberByName(memberName: String): MemberRespDto {
        val findMember = memberRepository.findByName(memberName)
            .orElseThrow { IllegalStateException("회원을 찾을 수 없습니다") }

        return MemberRespDto(findMember)
    }

    @Transactional
    override fun updateMember(memberId: Long, memberReq: MemberReqDto): Long {
        val findMember = memberRepository.findById(memberId)
            .orElseThrow { IllegalStateException("회원을 찾을 수 없습니다") }

        findMember.updateMember(memberReq.name, memberReq.age)

        return findMember.id!!
    }

    @Transactional
    override fun deleteMember(memberId: Long) {
        val findMember = memberRepository.findById(memberId)
            .orElseThrow { IllegalStateException("회원을 찾을 수 없습니다") }

        memberRepository.delete(findMember)
    }
}