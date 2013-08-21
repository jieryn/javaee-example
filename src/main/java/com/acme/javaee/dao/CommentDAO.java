package com.acme.javaee.dao;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.acme.javaee.domain.Comment;
import com.acme.javaee.domain.Post;

@Stateless
public class CommentDAO
{
  private static final Logger LOG = Logger
                                      .getLogger(CommentDAO.class.getName());

  @EJB
  private DAO                 dao;

  public Comment create(final String author, final String content,
      final long postId)
  {
    final Post post = dao.find(Post.class, postId);

    if (post == null)
    {
      throw new IllegalArgumentException("post with id " + postId
          + " not found");
    }

    final Comment comment = new Comment();
    comment.setAuthor(author);
    comment.setContent(content);
    dao.create(comment);
    comment.setPost(post);

    return comment;
  }

  public Comment delete(final long id)
  {
    return dao.delete(Comment.class, id);
  }

  @PostConstruct
  public void doPostConstruct()
  {
    LOG.info("CommentDAO: " + dao);
  }

  public List<Comment> list(final long postId)
  {
    final Post post = dao.find(Post.class, postId);

    if (post == null)
    {
      throw new IllegalArgumentException("post with id " + postId
          + " not found");
    }

    return Collections.unmodifiableList(post.getComments());
  }

  public Comment update(final long id, final String author, final String content)
  {
    final Comment comment = dao.find(Comment.class, id);

    if (comment == null)
    {
      throw new IllegalArgumentException("comment with id " + id + " not found");
    }

    comment.setAuthor(author);
    comment.setContent(content);

    return dao.update(comment);
  }
}
