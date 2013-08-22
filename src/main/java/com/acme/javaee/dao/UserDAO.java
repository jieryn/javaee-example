package com.acme.javaee.dao;

import javax.ejb.Stateless;

import com.acme.javaee.domain.User;

@Stateless
public class UserDAO extends DAO<User>
{
  public User create(final String name, final String pwd, final String mail)
  {
    final User user = new User();

    user.setFullname(name);
    user.setPassword(pwd);
    user.setEmail(mail);

    return create(user);
  }

  public User update(final long id, final String name, final String pwd,
      final String mail)
  {
    final User user = find(User.class, id);

    if (user == null)
    {
      throw new IllegalArgumentException("setUser id " + id + " not found");
    }

    user.setFullname(name);
    user.setPassword(pwd);
    user.setEmail(mail);

    return update(user);
  }
}
