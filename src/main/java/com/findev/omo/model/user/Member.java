package com.findev.omo.model.user;

import com.findev.omo.common.security.Jwt;
import com.findev.omo.model.commons.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

  @Id
  @Column(name = "member_pk")
  private String memberPk; // uuid
  @Column(name = "member_name")
  private String memberName;

  @Column(name = "member_id")
  private String memberId;

  @Column(name = "member_password")
  private String memberPassword;

  @Column(name = "member_birthday")
  private String memberBirthday;

  @Column(name = "member_phone")
  private String memberPhone;

  @Column(name = "member_nickname")
  private String memberNickname;

  @Column(name = "member_introduction")
  private String memberIntroduction;

  @Column(name = "member_email")
  private String memberEmail;

  @Column(name = "member_card_url")
  private String memberCardUrl;

  @Column(name = "member_job")
  private String memberJob;

  @Column(name = "member_job_detail")
  private String memberJobDetail;

  public String newApiToken(Jwt jwt, String[] roles) {
    Jwt.Claims claims = Jwt.Claims.of(memberId, memberNickname, roles);
    return jwt.newToken(claims);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Member member = (Member) o;
    return Objects.equals(memberPk, member.memberPk);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberPk);
  }

//  @Override
//  public String toString() {
//    return "Member{" +
//      "memberPk='" + memberPk + '\'' +
//      ", memberName='" + memberName + '\'' +
//      ", memberId='" + memberId + '\'' +
//      ", memberPassword='" + memberPassword + '\'' +
//      ", memberBirthday='" + memberBirthday + '\'' +
//      ", memberPhone='" + memberPhone + '\'' +
//      ", memberNickname='" + memberNickname + '\'' +
//      ", memberIntroduction='" + memberIntroduction + '\'' +
//      '}';
//  }
}