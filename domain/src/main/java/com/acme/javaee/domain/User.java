package com.acme.javaee.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.acme.javaee.base.Model;

@Entity
@XmlRootElement
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class User extends Model
{
  @NotNull
  @Pattern(regexp = ".+@.+\\.[a-z]+")
  private String email;

  @NotNull
  @Size(min = 3, max = 15)
  private String fullname;

  @NotNull
  @Size(min = 5, max = 15)
  private String password;

  public String getEmail()
  {
    return email;
  }

  public String getFullname()
  {
    return fullname;
  }

  public String getPassword()
  {
    return password;
  }

  public void setEmail(final String email)
  {
    this.email = email;
  }

  public void setFullname(final String fullname)
  {
    this.fullname = fullname;
  }

  public void setPassword(final String password)
  {
    this.password = password;
  }
}
