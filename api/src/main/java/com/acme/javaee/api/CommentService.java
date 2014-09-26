package com.acme.javaee.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
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

import com.acme.javaee.dao.CommentDAO;
import com.acme.javaee.domain.Comment;

@Singleton
@Path("/comments")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public class CommentService
{
  @EJB
  private CommentDAO commentDao;

  @PUT
  public Comment create(@QueryParam("author") final String author,
      @QueryParam("content") final String content,
      @QueryParam("postId") final long postId)
  {
    return commentDao.create(author, content, postId);
  }

  @Path("{id}")
  @DELETE
  public void delete(@PathParam("id") final long id)
  {
    commentDao.delete(id);
  }

  @Path("{postId}")
  @GET
  public List<Comment> list(@PathParam("postId") final long postId)
  {
    return commentDao.list(postId);
  }

  @Path("{id}")
  @POST
  public Comment update(@PathParam("id") final long id,
      @QueryParam("author") final String author,
      @QueryParam("content") final String content)
  {
    return commentDao.update(id, author, content);
  }
}
