package com.findev.omo.controller.dto;

import com.findev.omo.model.user.Member;
import lombok.Builder;
import lombok.Getter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
public class MemberDto {

  private String memberPk;
  private String memberNickName;

  private String memberId;
//
//  public MemberDto(Member source) {
//    return new MemberDto.builder()
//
//
////    copyProperties(source, this);
//  }

  @Builder
  public MemberDto(String memberPk, String memberNickName, String memberId) {
    this.memberId = memberId;
    this.memberPk = memberPk;
    this.memberNickName = memberNickName;
  }
}
