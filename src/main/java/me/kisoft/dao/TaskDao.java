/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ws.rs.WebApplicationException;
import me.kisoft.entity.EntityId;
import me.kisoft.entity.Task;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

/**
 *
 * @author tareq
 */
public class TaskDao extends NitriteDao {

  public Task getTaskById(long taskId) {
    Task t = db.getRepository(Task.class).find(eq("_id", taskId)).firstOrDefault();
    if (t == null) {
      throw new WebApplicationException(404);
    } else {
      return t;
    }
  }

  public List<Task> listTasks() {
    List<Task> tasks = db.getRepository(Task.class).find().toList();
    if (tasks.isEmpty()) {
      throw new WebApplicationException(404);
    } else {
      return tasks;
    }
  }

  public EntityId createTask(Task task) {
    return EntityId.of(db.getRepository(Task.class).insert(task).iterator().next());
  }

  public void updateTask(Task task, long taskId) {
    if (db.getRepository(Task.class).update(eq("_id", taskId), task, true).getAffectedCount() < 1) {
      throw new WebApplicationException(404);
    }
  }

  public void deleteTask(long taskId) {
    if (db.getRepository(Task.class).remove(eq("_id", taskId)).getAffectedCount() < 1) {
      throw new WebApplicationException(404);
    }
  }

  public void markComplete(long taskId) {
    Task t = getTaskById(taskId);
    t.setCompleted(true);
    t.setPercentComplete(100l);
    t.setCompletionDate(new Date());
    updateTask(t, taskId);
  }

  public void updateCompletion(Task task, long taskId) {
    if (task.getPercentComplete() >= 100l) {
      markComplete(taskId);
    } else {
      Task t = getTaskById(taskId);
      t.setPercentComplete(task.getPercentComplete());
      updateTask(t, taskId);
    }
  }

  public void addRelatedIssues(long taskId, Set<Long> issueIds) {
    Task t = getTaskById(taskId);
    if (t.getRelatedIssues() == null) {
      t.setRelatedIssues(new HashSet<>());
    }
    t.getRelatedIssues().addAll(issueIds);
    updateTask(t, taskId);
  }

  public void removeRelatedIssues(long taskId, Set<Long> issueIds) {
    Task t = getTaskById(taskId);
    if (t.getRelatedIssues() == null) {
      t.setRelatedIssues(new HashSet<>());
    }
    t.getRelatedIssues().removeAll(issueIds);
    updateTask(t, taskId);
  }

  public void updateDueDate(Task task, long taskId) {
    Task t = getTaskById(taskId);
    if (t.isCompleted()) {
      throw new WebApplicationException(409);
    } else {
      t.setDueDate(task.getDueDate());
      updateTask(t, taskId);
    }
  }
}
