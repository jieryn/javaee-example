package com.acme.javaee.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Application extends javax.ws.rs.core.Application
{
  @Override
  public Set<Class<?>> getClasses()
  {
    return new HashSet<Class<?>>(Arrays.asList(CommentService.class,
        PostService.class, UserService.class));
  }
}
