package com.acme.javaee.ws.rs;

import java.net.URL;
import java.util.logging.Logger;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.acme.javaee.dao.DAO;
import com.acme.javaee.dao.PostDAO;
import com.acme.javaee.domain.Model;
import com.acme.javaee.domain.Post;

@RunWith(Arquillian.class)
@Transactional
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

  @Drone
  private WebDriver driver;

  @ArquillianResource
  private URL       url;

  @Test
  public void testInjectDrone1()
  {
    Assert.assertNotNull(driver);
  }

  @Test
  public void testInjectUrl1()
  {
    Assert.assertNotNull(url);
  }

  @Test
  public void testList1()
  {
    driver.get(url.toExternalForm() + "post/list");
    LOG.info("GET: " + driver.getCurrentUrl());
    Assert.assertEquals("{\"post\":[]}", driver.getPageSource());
  }
}
