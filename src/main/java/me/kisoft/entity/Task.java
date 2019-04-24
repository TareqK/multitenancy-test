/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.kisoft.dao.Storeable;
import me.kisoft.dao.TaskDao;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@NoArgsConstructor
public class Task implements Serializable, Storeable<TaskDao> {

  @Id
  private NitriteId infologEntryId;

  private String title;

  private String description;

  private Long percentComplete;

  private boolean completed;

  private Date completionDate;

  private Set<Long> relatedIssues;

  private Date dueDate;

  private boolean reminder;

  @Override
  public TaskDao getDao() {
    return new TaskDao();
  }

}
