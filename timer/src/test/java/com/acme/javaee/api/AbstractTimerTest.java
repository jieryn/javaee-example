package com.acme.javaee.api;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public abstract class AbstractTimerTest
{
  @Deployment
  public static final JavaArchive createDeployment()
  {
    return ShrinkWrap.create(JavaArchive.class)
        .addPackages(true, "com.acme.javaee")
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
        .addAsManifestResource("persistence.xml");
  }
}
