package com.findev.omo.model.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

import static java.util.regex.Pattern.matches;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

  @Column(name = "email")
  private String address;

  public Email(String address) {
    // TODO validation
//    checkArgument(isNotEmpty(address), "address must be provided.");
//    checkArgument(
//      address.length() >= 4 && address.length() <= 50,
//      "address length must be between 4 and 50 characters."
//    );
//    checkArgument(checkAddress(address), "Invalid email address: " + address);
    this.address = address;
  }


  private static boolean checkAddress(String address) {
    return matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+", address);
  }

  public String getName() {
    String[] tokens = address.split("@");
    if (tokens.length == 2)
      return tokens[0];
    return null;
  }

  public String getDomain() {
    String[] tokens = address.split("@");
    if (tokens.length == 2)
      return tokens[1];
    return null;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Email email = (Email) o;
    return Objects.equals(address, email.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address);
  }

  @Override
  public String toString() {
    return "Email{" +
      "address='" + address + '\'' +
      '}';
  }
}