package com.acme.javaee.timer;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.wink.client.RestClient;

@Singleton
@Startup
public class FetchGoogleCom
{
  private static final Logger LOG = Logger.getLogger(FetchGoogleCom.class
                                      .getName());

  @PostConstruct
  public void doPostConstruct()
  {
    LOG.info("Google: "
        + new RestClient().resource("http://www.google.com").get(String.class));
  }
}
