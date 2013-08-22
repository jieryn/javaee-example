package com.acme.javaee.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Post extends Model
{
  @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
  private List<Comment> comments = new ArrayList<>();

  @NotNull
  @Size(min = 1)
  @Lob
  private String        content;

  private Date          created;

  @NotNull
  @Size(min = 1)
  private String        title;

  @ManyToOne
  @Valid
  private User          user;

  public void addComment(final Comment comment)
  {
    getComments().add(comment);
  }

  @PrePersist
  public void create()
  {
    created = new Date();
  }

  public List<Comment> getComments()
  {
    return comments;
  }

  public String getContent()
  {
    return content;
  }

  public Date getCreated()
  {
    return created;
  }

  public String getTitle()
  {
    return title;
  }

  public User getUser()
  {
    return user;
  }

  public void setComments(final List<Comment> comments)
  {
    this.comments = comments;
  }

  public void setContent(final String content)
  {
    this.content = content;
  }

  public void setCreated(final Date created)
  {
    this.created = created;
  }

  public void setTitle(final String title)
  {
    this.title = title;
  }

  public void setUser(final User user)
  {
    this.user = user;
  }
}
