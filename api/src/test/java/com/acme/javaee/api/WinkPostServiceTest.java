package com.acme.javaee.api;

import java.net.MalformedURLException;

import org.apache.wink.client.ClientWebException;
import org.apache.wink.client.RestClient;
import org.junit.Assert;
import org.junit.Test;

/**
 * @deprecated use shiny new JAX-RS 2.0 Client API
 */
@Deprecated
public class WinkPostServiceTest extends AbstractServiceTest
{
  @Test(expected = ClientWebException.class)
  public void testDelete1() throws MalformedURLException
  {
    Assert.assertEquals(0, postDAO.findTotal());

    new RestClient().resource(makePrefixedUrlString("api", "posts", "123"))
        .delete(String.class);
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
        makePrefixedUrlString("api", "posts")).get(String.class);

    Assert.assertNotNull(response);
    Assert.assertEquals("{\"post\":[]}", response);
  }

  @Test
  public void testShow1() throws MalformedURLException
  {
    Assert.assertEquals(0, postDAO.findTotal());

    final String response = new RestClient().resource(
        makePrefixedUrlString("api", "posts", "123")).get(String.class);

    Assert.assertNotNull(response);
    Assert.assertEquals("", response);
  }
}
