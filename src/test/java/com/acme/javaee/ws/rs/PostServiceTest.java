package com.acme.javaee.ws.rs;

import java.util.logging.Logger;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acme.javaee.dao.DAO;
import com.acme.javaee.dao.PostDAO;
import com.acme.javaee.domain.Model;
import com.acme.javaee.domain.Post;

@RunWith(Arquillian.class)
public class PostServiceTest
{
  private static final Logger LOG = Logger.getLogger(PostServiceTest.class
                                      .getName());

  @Deployment
  public static Archive<?> createDeployment()
  {
    final Archive<?> archive = ShrinkWrap
        .create(WebArchive.class)
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
        .addAsManifestResource("persistence.xml")
        .addClasses(DAO.class, Model.class, Post.class, PostDAO.class,
            PostService.class);

    LOG.info("DEPLOYMENT: " + archive.toString(true));

    return archive;
  }

  @EJB
  private PostService postService;

  @Test
  public void testCreate1()
  {
    Assert.assertNotNull(postService.create("title", "content", 1));
  }

  @Test
  public void testInject1()
  {
    Assert.assertNotNull(postService);
  }
}
