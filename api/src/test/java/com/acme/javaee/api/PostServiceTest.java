package com.acme.javaee.api;

import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.EJB;

import org.apache.wink.client.ClientWebException;
import org.apache.wink.client.RestClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acme.javaee.dao.PostDAO;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class PostServiceTest extends AbstractServiceTest
{
  @EJB
  private PostDAO postDAO;

  @ArquillianResource
  private URL     url;

  private String makePrefixedUrlString(final String fragment)
      throws MalformedURLException
  {
    return new URL(url, fragment).toExternalForm();
  }

  @Test(expected = ClientWebException.class)
  public void testDelete1() throws MalformedURLException
  {
    Assert.assertEquals(0, postDAO.findTotal());

    new RestClient().resource(makePrefixedUrlString("posts/123")).delete(
        String.class);
  }

  @Test
  public void testInjectUrl1()
  {
    Assert.assertNotNull(url);
  }

  @Test
  public void testList1() throws MalformedURLException
  {
    Assert.assertEquals(0, postDAO.findTotal());

    final String response = new RestClient().resource(
        makePrefixedUrlString("posts")).get(String.class);

    Assert.assertNotNull(response);
    Assert.assertEquals("{\"post\":[]}", response);
  }

  @Test
  public void testShow1() throws MalformedURLException
  {
    Assert.assertEquals(0, postDAO.findTotal());

    final String response = new RestClient().resource(
        makePrefixedUrlString("posts/123")).get(String.class);

    Assert.assertNotNull(response);
    Assert.assertEquals("", response);
  }
}
