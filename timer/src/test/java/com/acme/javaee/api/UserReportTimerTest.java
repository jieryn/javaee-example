package com.acme.javaee.api;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acme.javaee.timer.UserReportTimer;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class UserReportTimerTest extends AbstractTimerTest
{
  @EJB
  private UserReportTimer userReportTimer;

  @Test
  public void testInject1()
  {
    Assert.assertNotNull(userReportTimer);
  }

  @Test
  public void testRun1()
  {
    userReportTimer.run();
  }
}
