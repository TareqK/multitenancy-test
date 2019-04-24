/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.dao;

import java.util.List;
import lombok.AllArgsConstructor;
import me.kisoft.entity.Action;
import me.kisoft.entity.EntityId;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

/**
 *
 * @author tareq
 */
@AllArgsConstructor
public class ActionDao extends NitriteDao {

  public Action getActionById(long actionId) {
    return db.getRepository(Action.class).find(eq("_id", actionId)).firstOrDefault();
  }

  public List<Action> listActions() {
    return db.getRepository(Action.class).find().toList();
  }

  public EntityId createAction(Action action) {
    return EntityId.of(db.getRepository(Action.class).insert(action).iterator().next());
  }

  public void updateAction(Action action, long actionId) {
    db.getRepository(Action.class).update(eq("_id", actionId), action, true);
  }

  public void deleteAction(long actionId) {
    db.getRepository(Action.class).remove(eq("_id", actionId));
  }

}
