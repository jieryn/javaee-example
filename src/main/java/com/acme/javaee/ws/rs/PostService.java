package com.acme.javaee.ws.rs;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.acme.javaee.dao.PostDAO;
import com.acme.javaee.domain.Post;

@Path("/post")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public class PostService
{
  @EJB
  private PostDAO dao;

  @Path("/create")
  @PUT
  public Post create(@QueryParam("title") final String title,
      @QueryParam("content") final String content,
      @QueryParam("userId") final long userId)
  {
    return dao.create(title, content, userId);
  }

  @Path("/delete/{id}")
  @DELETE
  public void delete(@PathParam("id") final long id)
  {
    dao.delete(id);
  }

  @Path("/list")
  @GET
  public List<Post> list()
  {
    return dao.findAll();
  }

  @Path("/show/{id}")
  @GET
  public Post show(@PathParam("id") final long id)
  {
    return dao.findById(id);
  }

  @Path("/update/{id}")
  @POST
  public Post update(@PathParam("id") final long id,
      @QueryParam("userId") final long userId,
      @QueryParam("title") final String title,
      @QueryParam("content") final String content)
  {
    return dao.update(id, userId, title, content);
  }
}
