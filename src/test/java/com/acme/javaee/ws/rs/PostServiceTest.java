package com.acme.javaee.ws.rs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.apache.wink.client.ClientWebException;
import org.apache.wink.client.RestClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
  private PostDAO postDAO;

  @ArquillianResource
  private URL     url;

  @After
  public void doAfter()
  {
    LOG.info("AFTER: " + postDAO.findTotal() + " : " + postDAO.findAll());
    Assert.assertEquals(0, postDAO.findTotal());
  }

  @Before
  public void doBefore()
  {
    LOG.info("BEFORE: " + postDAO.findTotal() + " : " + postDAO.findAll());
    Assert.assertEquals(0, postDAO.findTotal());
  }

  private String makePrefixedUrlString(final String fragment)
      throws MalformedURLException
  {
    return new URL(url, fragment).toExternalForm();
  }

  @Test(expected = ClientWebException.class)
  public void testDelete1() throws MalformedURLException
  {
    LOG.info("testDelete1()");

    new RestClient().resource(makePrefixedUrlString("post/delete/123")).delete(
        String.class);
  }

  @Test
  public void testInjectUrl1()
  {
    LOG.info("testInjectUrl1()");

    Assert.assertNotNull(url);
  }

  @Test
  public void testList1() throws MalformedURLException
  {
    LOG.info("testList1()");

    final String response = new RestClient().resource(
        makePrefixedUrlString("post/list")).get(String.class);

    Assert.assertNotNull(response);
    Assert.assertEquals("{\"post\":[]}", response);
  }

  @Test
  public void testShow1() throws MalformedURLException
  {
    LOG.info("testShow1()");

    final String response = new RestClient().resource(
        makePrefixedUrlString("post/show/123")).get(String.class);

    Assert.assertNotNull(response);
    Assert.assertEquals("", response);
  }
}
