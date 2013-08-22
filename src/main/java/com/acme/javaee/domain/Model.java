package com.acme.javaee.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.FIELD)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Model
{
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;

  @Version
  @Transient
  private Long version;

  public Long getId()
  {
    return id;
  }

  public Long getVersion()
  {
    return version;
  }

  public void setId(final Long id)
  {
    this.id = id;
  }

  public void setVersion(final Long version)
  {
    this.version = version;
  }
}
