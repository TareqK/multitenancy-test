/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.dao.ActionDao;
import me.kisoft.dao.Storeable;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author tareq
 */
@Getter
@Setter
public class Action implements Serializable, Storeable<ActionDao> {

  @Id
  private NitriteId actionId = NitriteId.newId();

  private String content;

  private String category;

  private ArrayList<Long> relatedActions;

  private long issueId;

  @Override
  public ActionDao getDao() {
    return new ActionDao();
  }
}
