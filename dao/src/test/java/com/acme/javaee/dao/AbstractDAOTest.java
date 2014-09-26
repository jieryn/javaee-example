package com.acme.javaee.dao;

import java.util.logging.Logger;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import com.acme.javaee.base.DAO;
import com.acme.javaee.base.Model;
import com.acme.javaee.domain.Post;
import com.acme.javaee.domain.User;

public class AbstractDAOTest
{
  private static final Logger LOG = Logger.getLogger(AbstractDAOTest.class
                                      .getName());

  @Deployment
  public static final Archive<?> createDeployment()
  {
    final Archive<?> archive = ShrinkWrap
        .create(JavaArchive.class)
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
        .addAsManifestResource("persistence.xml")
        .addClasses(DAO.class, Model.class, Post.class, PostDAO.class,
            UserDAO.class, User.class);

    LOG.info("DEPLOYMENT: " + archive.toString(true));

    return archive;
  }
}