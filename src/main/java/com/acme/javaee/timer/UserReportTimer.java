package com.acme.javaee.timer;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.acme.javaee.dao.UserDAO;
import com.acme.javaee.domain.User;

@Lock(LockType.READ)
@Singleton
@Startup
public class UserReportTimer
{
  private static final Logger LOG = Logger.getLogger(UserReportTimer.class
                                      .getName());

  @EJB
  private UserDAO             dao;

  @Schedule(second = "0", minute = "*/1", hour = "*", persistent = false)
  public void run()
  {
    LOG.info("POP!");

    for (final User user : dao.findAll())
    {
      LOG.info("Found User: " + user);
    }
  }
}
