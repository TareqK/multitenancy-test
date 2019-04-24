/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.service;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import me.kisoft.dao.NitriteDao;
import me.kisoft.entity.Action;
import me.kisoft.entity.EntityId;
import me.kisoft.entity.Issue;
import me.kisoft.entity.multitenancy.Secured;

/**
 *
 * @author tareq
 */
@Path("issue")
public class IssueService {

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Secured
  public List<Issue> listIssues(@Context SecurityContext sc) {
    return NitriteDao.of(sc, Issue.class).listIssues();
  }

  @GET
  @Path("{issue_id}")
  @Produces({MediaType.APPLICATION_JSON})
  @Secured
  public Issue getIssues(@Context SecurityContext sc, @PathParam("issue_id") long issueId) {
    return NitriteDao.of(sc, Issue.class).getIssueById(issueId);
  }

  @DELETE
  @Path("{issue_id}")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  public void deleteIssue(@Context SecurityContext sc, @PathParam("issue_id") long issueId) {
    NitriteDao.of(sc, Issue.class).deleteIssue(issueId);
  }

  @PUT
  @Path("{issue_id}")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void deleteIssue(@Context SecurityContext sc, @PathParam("issue_id") long issueId, Issue issue) {
    NitriteDao.of(sc, Issue.class).updateIssue(issue, issueId);
  }

  @POST
  @Path("{issue_id}/action")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void addAction(@Context SecurityContext sc, @PathParam("issue_id") long issueId, Action action) {
    NitriteDao.of(sc, Issue.class).addAction(issueId, action);
  }

  @PUT
  @Path("{issue_id}/status")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void setStatus(@Context SecurityContext sc, @PathParam("issue_id") long issueId, Issue issue) {
    NitriteDao.of(sc, Issue.class).updateStatus(issue, issueId);
  }

  @POST
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  @Secured
  public EntityId createIssue(@Context SecurityContext sc, Issue issue) {
    return NitriteDao.of(sc, Issue.class).createIssue(issue);
  }
}
