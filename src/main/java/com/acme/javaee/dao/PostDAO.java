package com.acme.javaee.dao;

import javax.ejb.Stateless;

import com.acme.javaee.domain.Post;
import com.acme.javaee.domain.User;

@Stateless
public class PostDAO extends DAO<Post>
{
  public Post create(final String title, final String content, final long userId)
  {
    final User user = find(User.class, userId);

    final Post post = new Post();
    post.setTitle(title);
    post.setContent(content);
    post.setUser(user);

    return create(post);
  }

  public Post update(final long id, final long userId, final String title,
      final String content)
  {
    final User user = find(User.class, userId);

    if (user == null)
    {
      throw new IllegalArgumentException("user id " + id + " not found");
    }

    final Post post = find(Post.class, id);

    if (post == null)
    {
      throw new IllegalArgumentException("post id " + id + " not found");
    }

    post.setTitle(title);
    post.setContent(content);
    post.setUser(user);

    return update(post);
  }
}
