package com.acme.javaee.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acme.javaee.domain.Model;
import com.acme.javaee.domain.User;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class UserDAOTest
{
  private static final Logger LOG = Logger.getLogger(UserDAOTest.class
                                      .getName());

  @Deployment
  public static Archive<?> createDeployment()
  {
    final Archive<?> archive = ShrinkWrap.create(JavaArchive.class)
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
        .addAsManifestResource("persistence.xml")
        .addClasses(DAO.class, Model.class, User.class, UserDAO.class);

    LOG.info("DEPLOYMENT: " + archive.toString(true));

    return archive;
  }

  @EJB
  private UserDAO userDAO;

  @Test
  public void testCreate1()
  {
    LOG.info("testCreate1()");

    Assert.assertNotNull(userDAO.create("username1", "password1",
        "username1@email.com"));
    Assert.assertNotNull(userDAO.create("username2", "password2",
        "username2@email.com"));
    Assert.assertNotNull(userDAO.create("username3", "password3",
        "username3@email.com"));
  }

  @Test(expected = EJBException.class)
  public void testCreate2()
  {
    LOG.info("testCreate2()");

    Assert.assertNotNull(userDAO.create("username", "password",
        "username@email.com"));
    userDAO.create("username", "password", "username@email.com");
    Assert.fail("should have taken UniqueConstraint validation error");
  }

  @Test(expected = EJBException.class)
  public void testDelete1()
  {
    LOG.info("testDelete1()");

    Assert.assertEquals(0, userDAO.findTotal());

    userDAO.delete((User) null);
  }

  @Test(expected = EJBException.class)
  public void testDelete2()
  {
    LOG.info("testDelete2()");

    Assert.assertEquals(0, userDAO.findTotal());

    userDAO.delete(-1L);
  }

  @Test
  public void testDelete3()
  {
    LOG.info("testDelete3()");

    Assert.assertEquals(0, userDAO.findTotal());

    userDAO.delete(userDAO.create("username1", "password1",
        "username1@email.com"));

    Assert.assertEquals(0, userDAO.findTotal());
  }

  @Test
  public void testFindAll1()
  {
    LOG.info("testFindAll1()");

    Assert.assertEquals(0, userDAO.findTotal());

    final List<User> result = userDAO.findAll();

    Assert.assertNotNull(result);
    Assert.assertEquals(0, result.size());
  }

  @Test
  public void testFindAll2()
  {
    LOG.info("testFindAll2()");

    Assert.assertEquals(0, userDAO.findTotal());

    Assert.assertNotNull(userDAO.create("username1", "password1",
        "username1@email.com"));
    Assert.assertNotNull(userDAO.create("username2", "password2",
        "username2@email.com"));
    Assert.assertNotNull(userDAO.create("username3", "password3",
        "username3@email.com"));

    final List<User> result = userDAO.findAll();

    Assert.assertNotNull(result);
    Assert.assertEquals(3, result.size());
  }

  @Test
  public void testFindById1()
  {
    LOG.info("testFindById1()");

    Assert.assertEquals(0, userDAO.findTotal());

    Assert.assertNull(userDAO.findById(-1L));
  }

  @Test
  public void testInject1()
  {
    LOG.info("testInject1()");

    Assert.assertNotNull(userDAO);
  }

  @Test(expected = EJBException.class)
  public void testUpdate1()
  {
    LOG.info("testUpdate1()");

    Assert.assertEquals(0, userDAO.findTotal());

    userDAO.update((User) null);
  }

  @Test(expected = EJBException.class)
  public void testUpdate2()
  {
    LOG.info("testUpdate2()");

    Assert.assertEquals(0, userDAO.findTotal());

    userDAO.update(-1L, (String) null, (String) null, (String) null);
  }
}
