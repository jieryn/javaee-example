package com.acme.javaee.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

import com.acme.javaee.domain.Comment;
import com.acme.javaee.domain.Post;

@Stateless
public class CommentDAO extends DAO<Comment>
{
  public Comment create(final String author, final String content,
      final long postId)
  {
    final Post post = find(Post.class, postId);

    if (post == null)
    {
      throw new IllegalArgumentException("post with id " + postId
          + " not found");
    }

    final Comment comment = new Comment();
    comment.setAuthor(author);
    comment.setContent(content);
    create(comment);
    comment.setPost(post);

    return comment;
  }

  public List<Comment> list(final long postId)
  {
    final Post post = find(Post.class, postId);

    if (post == null)
    {
      throw new IllegalArgumentException("post with id " + postId
          + " not found");
    }

    return Collections.unmodifiableList(post.getComments());
  }

  public Comment update(final long id, final String author, final String content)
  {
    final Comment comment = find(Comment.class, id);

    if (comment == null)
    {
      throw new IllegalArgumentException("comment with id " + id + " not found");
    }

    comment.setAuthor(author);
    comment.setContent(content);

    return update(comment);
  }
}
