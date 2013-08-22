package com.acme.javaee.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Comment extends Model
{
  @NotNull
  @Size(min = 1)
  private String author;

  @NotNull
  @Size(min = 1)
  @Lob
  private String content;

  @ManyToOne
  @JoinColumn(name = "post_id")
  @Valid
  @XmlTransient
  private Post   post;

  public String getAuthor()
  {
    return author;
  }

  public String getContent()
  {
    return content;
  }

  public Post getPost()
  {
    return post;
  }

  public void setAuthor(final String author)
  {
    this.author = author;
  }

  public void setContent(final String content)
  {
    this.content = content;
  }

  public void setPost(final Post post)
  {
    post.addComment(this);
    this.post = post;
  }
}
