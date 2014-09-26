package com.acme.javaee.api;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractServiceTest
{
  @Deployment
  public static WebArchive createDeployment()
  {
    return ShrinkWrap.create(WebArchive.class)
        .addPackages(true, "com.acme.javaee")
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
        .addAsManifestResource("persistence.xml");
  }
}
