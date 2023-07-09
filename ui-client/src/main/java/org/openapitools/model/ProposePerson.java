package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ProposePerson
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-30T15:40:38.765073900+02:00[Europe/Brussels]")
public class ProposePerson {

  private String lastname;

  private String firstname;

  private Object birthday;

  /**
   * Default constructor
   * @deprecated Use {@link ProposePerson#ProposePerson(String, String, Object)}
   */
  @Deprecated
  public ProposePerson() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProposePerson(String lastname, String firstname, Object birthday) {
    this.lastname = lastname;
    this.firstname = firstname;
    this.birthday = birthday;
  }

  public ProposePerson lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * Get lastname
   * @return lastname
  */
  @NotNull 
  @Schema(name = "lastname", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("lastname")
  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public ProposePerson firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * Get firstname
   * @return firstname
  */
  @NotNull 
  @Schema(name = "firstname", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("firstname")
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public ProposePerson birthday(Object birthday) {
    this.birthday = birthday;
    return this;
  }

  /**
   * Get birthday
   * @return birthday
  */
  @NotNull 
  @Schema(name = "birthday", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("birthday")
  public Object getBirthday() {
    return birthday;
  }

  public void setBirthday(Object birthday) {
    this.birthday = birthday;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProposePerson proposePerson = (ProposePerson) o;
    return Objects.equals(this.lastname, proposePerson.lastname) &&
        Objects.equals(this.firstname, proposePerson.firstname) &&
        Objects.equals(this.birthday, proposePerson.birthday);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastname, firstname, birthday);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProposePerson {\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    birthday: ").append(toIndentedString(birthday)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

