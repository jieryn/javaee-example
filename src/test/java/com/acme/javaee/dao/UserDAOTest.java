package com.acme.javaee.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
    Assert.assertNotNull(userDAO.create("foo", "dummy", "foo@bar.org"));
  }

  @Test
  public void testFind1()
  {
    Assert.assertNull(userDAO.find(-1));
  }

  @Test
  public void testFindAll1()
  {
    final List<User> result = userDAO.findAll();

    Assert.assertNotNull(result);
    Assert.assertEquals(0, result.size());
  }

  @Test
  public void testInject1()
  {
    Assert.assertNotNull(userDAO);
  }
}
