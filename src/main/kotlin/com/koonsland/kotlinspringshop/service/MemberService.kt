package com.koonsland.kotlinspringshop.service

import com.koonsland.kotlinspringshop.dto.MemberReqDto
import com.koonsland.kotlinspringshop.dto.MemberRespDto

interface MemberService {
    // 회원 생성
    fun addMember(memberReq: MemberReqDto): Long
    // 회원 전체 조회
    fun findAllMember(): List<MemberRespDto>
    // 회원 Id 조회
    fun findMemberById(memberId: Long): MemberRespDto
    // 회원 이름 조회
    fun findMemberByName(memberName: String): MemberRespDto
    // 회원 수정
    fun updateMember(memberId: Long, memberReq: MemberReqDto): Long
    // 회원 삭제
    fun deleteMember(memberId: Long): Unit
}