package com.acme.javaee.api;

import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import com.acme.javaee.dao.PostDAO;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public abstract class AbstractServiceTest
{
  @Deployment(testable = false)
  public static final WebArchive createDeployment()
  {
    return ShrinkWrap.create(WebArchive.class)
        .addPackages(true, "com.acme.javaee")
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
        .addAsManifestResource("persistence.xml");
  }

  @EJB
  protected PostDAO postDAO;

  @ArquillianResource
  protected URL     url;

  public final String makePrefixedUrlString(final String... fragments)
      throws MalformedURLException
  {
    return url.toExternalForm() + StringUtils.join(fragments, '/');
  }
}
