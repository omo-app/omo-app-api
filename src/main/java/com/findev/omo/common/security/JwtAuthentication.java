package com.findev.omo.common.security;


import com.findev.omo.model.commons.Id;
import com.findev.omo.model.user.Member;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 인증된 사용자를 표현한다.
 */
public class JwtAuthentication {

  public final Id<Member, String> memberId;

  public final String memberNickName;


  JwtAuthentication(String memberId, String memberNickName) {
//    checkArgument(id != null, "id must be provided.");
//    checkArgument(name != null, "name must be provided.");
    this.memberId = Id.of(Member.class, memberId);
    this.memberNickName = memberNickName;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("memberId", memberId)
      .toString();
  }

}