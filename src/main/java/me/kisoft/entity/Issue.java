/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.kisoft.dao.IssueDao;
import me.kisoft.dao.Storeable;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@NoArgsConstructor
public class Issue implements Serializable, Storeable<IssueDao> {

  @Id
  private NitriteId IssueId;

  private EisenhowerStatus currentStatus;

  private ArrayList<Action> actions;

  @Override
  public IssueDao getDao() {
    return new IssueDao();
  }

}
