/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dizitart.no2.NitriteId;

/**
 *
 * @author tareq
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EntityId implements Serializable {

  private long id;

  public static EntityId of(NitriteId id) {
    return new EntityId(id.getIdValue());
  }
}
