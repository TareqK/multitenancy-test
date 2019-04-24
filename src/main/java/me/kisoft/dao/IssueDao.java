/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.dao;

import java.util.List;
import javax.ws.rs.WebApplicationException;
import me.kisoft.entity.Action;
import me.kisoft.entity.EisenhowerStatus;
import me.kisoft.entity.EntityId;
import me.kisoft.entity.Issue;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

/**
 *
 * @author tareq
 */
public class IssueDao extends NitriteDao {

  public Issue getIssueById(long issueId) {
    Issue i = db.getRepository(Issue.class).find(eq("_id", issueId)).firstOrDefault();
    if (i == null) {
      throw new WebApplicationException(404);
    } else {
      return i;
    }
  }

  public List<Issue> listIssues() {
    List<Issue> issues = db.getRepository(Issue.class).find().toList();
    if (issues.isEmpty()) {
      throw new WebApplicationException(404);
    } else {
      return issues;
    }
  }

  public EntityId createIssue(Issue issue) {
    return EntityId.of(db.getRepository(Issue.class).insert(issue).iterator().next());
  }

  public void updateIssue(Issue issue, long issueId) {
    if (db.getRepository(Issue.class).update(eq("_id", issueId), issue, false).getAffectedCount() < 1) {
      throw new WebApplicationException(404);
    }
  }

  public void deleteIssue(long issueId) {
    if (db.getRepository(Issue.class).remove(eq("_id", issueId)).getAffectedCount() < 1) {
      throw new WebApplicationException(404);
    }
  }

  public void addAction(long issueId, Action action) {
    Issue i = getIssueById(issueId);
    action.setIssueId(issueId);
    i.getActions().add(action);
    updateIssue(i, issueId);
  }

  public void updateStatus(Issue issue, long issueId) {
    Issue i = getIssueById(issueId);
    if (i.getCurrentStatus() != EisenhowerStatus.COMPLETED && i.getCurrentStatus() != EisenhowerStatus.CANCELLED) {
      i.setCurrentStatus(issue.getCurrentStatus());
      updateIssue(i, issueId);
    } else {
      throw new WebApplicationException(409);
    }
  }

}
