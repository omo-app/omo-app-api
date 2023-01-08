package com.findev.omo.service;

import com.findev.omo.common.security.Jwt;
import com.findev.omo.controller.dto.SignUpRequest;
import com.findev.omo.controller.dto.MemberDto;
import com.findev.omo.model.user.Member;
import com.findev.omo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public MemberDto signUp(SignUpRequest req){
    Member savedMember = memberRepository.save(req.toEntity());

    return MemberDto.builder()
      .memberNickName(savedMember.getMemberNickname())
      .memberPk(savedMember.getMemberPk())
      .memberId(savedMember.getMemberId())
      .build();
  }

  @Transactional
  public MemberDto login(String memberId, String memberPassword){

    Member member = memberRepository.findByMemberId(memberId);
    if(!memberPassword.equals(member.getMemberPassword())){
      return null; // TODO password 로그인 실패, 인코딩
    }
    return new MemberDto(member.getMemberPk(),member.getMemberNickname(),member.getMemberId());
  }

  public String newApiToken(Jwt jwt, String[] roles,MemberDto memberDto) {
    Jwt.Claims claims = Jwt.Claims.of(memberDto.getMemberId(), memberDto.getMemberNickName(), roles);
    return jwt.newToken(claims);
  }
}
