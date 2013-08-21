package com.acme.javaee.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.acme.javaee.domain.Post;
import com.acme.javaee.domain.User;

@Stateless
public class PostDAO
{
  private static final Logger LOG = Logger.getLogger(PostDAO.class.getName());

  @EJB
  private DAO                 dao;

  public Post create(final String title, final String content, final long userId)
  {
    final User user = dao.find(User.class, userId);

    final Post post = new Post();
    post.setTitle(title);
    post.setContent(content);
    post.setUser(user);

    return dao.create(post);
  }

  public Post delete(final long id)
  {
    return dao.delete(Post.class, id);
  }

  @PostConstruct
  public void doPostConstruct()
  {
    LOG.info("PostDAO: " + dao);
  }

  public Post find(final long id)
  {
    return dao.findById(Post.class, id);
  }

  public List<Post> findAll()
  {
    return dao.findAll(Post.class);
  }

  public Post update(final long id, final long userId, final String title,
      final String content)
  {
    final User user = dao.find(User.class, userId);

    if (user == null)
    {
      throw new IllegalArgumentException("user id " + id + " not found");
    }

    final Post post = dao.find(Post.class, id);

    if (post == null)
    {
      throw new IllegalArgumentException("post id " + id + " not found");
    }

    post.setTitle(title);
    post.setContent(content);
    post.setUser(user);

    return dao.update(post);
  }
}
