/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.kisoft.dao.BoxDao;
import me.kisoft.dao.Storeable;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@NoArgsConstructor
public class Box implements Serializable, Storeable<BoxDao> {

  @Id
  private long boxId;

  private String boxContent;

  @Override
  public BoxDao getDao() {
    return new BoxDao();
  }

}
