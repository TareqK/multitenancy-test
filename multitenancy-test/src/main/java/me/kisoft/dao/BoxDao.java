/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.dao;

import java.util.List;
import me.kisoft.entity.Box;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

/**
 *
 * @author tareq
 */
public class BoxDao extends NitriteDao {

  public Box getBoxById(long boxId) {
    return db.getRepository(Box.class).find(eq("boxId", boxId)).firstOrDefault();
  }

  public List<Box> listBoxs() {
    return db.getRepository(Box.class).find().toList();
  }

  public void createBox(Box box) {
    db.getRepository(Box.class).insert(box);
  }

  public void updateBox(Box box, long boxId) {
    db.getRepository(Box.class).update(eq("boxId", boxId), box, true);
  }

  public void deleteBox(long boxId) {
    db.getRepository(Box.class).remove(eq("boxId", boxId));
  }
}
