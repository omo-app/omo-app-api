package com.findev.omo.common.security;


import com.findev.omo.model.commons.Id;
import com.findev.omo.model.user.Email;
import com.findev.omo.model.user.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 인증된 사용자를 표현한다.
 */
public class JwtAuthentication {

  public final Id<User, Long> id;

  public final String name;

  public final Email email;

  JwtAuthentication(Long id, String name, Email email) {
//    checkArgument(id != null, "id must be provided.");
//    checkArgument(name != null, "name must be provided.");
//    checkArgument(email != null, "email must be provided.");

    this.id = Id.of(User.class, id);
    this.name = name;
    this.email = email;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("id", id)
      .append("email", email)
      .toString();
  }

}