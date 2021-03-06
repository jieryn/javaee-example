package com.acme.javaee.api;

import java.net.MalformedURLException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostServiceTest extends AbstractServiceTest
{
  private Client    client;

  private WebTarget target;

  @Before
  public void before() throws MalformedURLException
  {
    client = ClientBuilder.newClient();
    target = client.target(makePrefixedUrlString("api"));
  }

  @Test(expected = NullPointerException.class)
  public void testDelete1() throws MalformedURLException
  {
    Assert.assertEquals(0, postDAO.findTotal());

    target.path("posts/123").request().delete(String.class);
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

    final String response = target.path("posts").request().get(String.class);

    Assert.assertNotNull(response);
    Assert.assertEquals("{\"post\":[]}", response);
  }

  @Test
  public void testShow1() throws MalformedURLException
  {
    Assert.assertEquals(0, postDAO.findTotal());

    final String response = target.path("posts/123").request()
        .get(String.class);

    Assert.assertNotNull(response);
    Assert.assertEquals("", response);
  }
}
