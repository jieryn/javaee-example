package com.acme.javaee.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.acme.javaee.domain.User;

@Stateless
public class UserDAO
{
  private static final Logger LOG = Logger.getLogger(UserDAO.class.getName());

  @EJB
  private DAO                 dao;

  public User create(final String name, final String pwd, final String mail)
  {
    final User user = new User();

    user.setFullname(name);
    user.setPassword(pwd);
    user.setEmail(mail);

    return dao.create(user);
  }

  public User delete(final long id)
  {
    return dao.delete(User.class, id);
  }

  @PostConstruct
  public void doPostConstruct()
  {
    LOG.info("UserDAO: " + dao);
  }

  public User find(final long id)
  {
    return dao.findById(User.class, id);
  }

  public List<User> findAll()
  {
    return dao.findAll(User.class);
  }

  public User update(final long id, final String name, final String pwd,
      final String mail)
  {
    final User user = dao.find(User.class, id);

    if (user == null)
    {
      throw new IllegalArgumentException("setUser id " + id + " not found");
    }

    user.setFullname(name);
    user.setPassword(pwd);
    user.setEmail(mail);

    return dao.update(user);
  }
}
