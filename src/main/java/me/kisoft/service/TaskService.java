/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.service;

import java.util.List;
import java.util.Set;
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
import me.kisoft.entity.EntityId;
import me.kisoft.entity.Task;
import me.kisoft.entity.multitenancy.Secured;

/**
 *
 * @author tareq
 */
@Path("task")
public class TaskService {

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Secured
  public List<Task> listTasks(@Context SecurityContext sc) {
    return NitriteDao.of(sc, Task.class).listTasks();
  }

  @GET
  @Path("{task_id}")
  @Produces({MediaType.APPLICATION_JSON})
  @Secured
  public Task getTasks(@Context SecurityContext sc, @PathParam("task_id") long taskId) {
    return NitriteDao.of(sc, Task.class).getTaskById(taskId);
  }

  @DELETE
  @Path("{task_id}")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  public void deleteTask(@Context SecurityContext sc, @PathParam("task_id") long taskId) {
    NitriteDao.of(sc, Task.class).deleteTask(taskId);
  }

  @PUT
  @Path("{task_id}")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void deleteTask(@Context SecurityContext sc, @PathParam("task_id") long taskId, Task task) {
    NitriteDao.of(sc, Task.class).updateTask(task, taskId);
  }

  @PUT
  @Path("{task_id}/complete")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void addAction(@Context SecurityContext sc, @PathParam("task_id") long taskId) {
    NitriteDao.of(sc, Task.class).markComplete(taskId);
  }

  @PUT
  @Path("{task_id}/completion")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void setStatus(@Context SecurityContext sc, @PathParam("task_id") long taskId, Task task) {
    NitriteDao.of(sc, Task.class).updateCompletion(task, taskId);
  }

  @PUT
  @Path("{task_id}/date")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void updateDueDate(@Context SecurityContext sc, @PathParam("task_id") long taskId, Task task) {
    NitriteDao.of(sc, Task.class).updateDueDate(task, taskId);
  }

  @PUT
  @Path("{task_id}/issue")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void addRelatedIssues(@Context SecurityContext sc, @PathParam("task_id") long taskId, Set<Long> issueIds) {
    NitriteDao.of(sc, Task.class).addRelatedIssues(taskId, issueIds);
  }

  @DELETE
  @Path("{task_id}/issue")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void removeRelatedIssues(@Context SecurityContext sc, @PathParam("task_id") long taskId, Set<Long> issueIds) {
    NitriteDao.of(sc, Task.class).removeRelatedIssues(taskId, issueIds);
  }

  @POST
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  @Secured
  public EntityId createTask(@Context SecurityContext sc, Task task) {
    return NitriteDao.of(sc, Task.class).createTask(task);
  }
}
