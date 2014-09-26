package com.acme.javaee.base;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

  @Version
  @Transient
  private Long version;

  public Long getId()
  {
    return id;
  }

  public Date getUpdated()
  {
    return updated;
  }

  public Long getVersion()
  {
    return version;
  }

  @PrePersist
  @PreUpdate
  public void updateUpdated()
  {
    updated = new Date();
  }
}
