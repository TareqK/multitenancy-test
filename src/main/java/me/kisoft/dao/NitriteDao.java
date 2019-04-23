/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.dao;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.SecurityContext;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.entity.multitenancy.MultiTenancyUser;
import me.kisoft.entity.multitenancy.NitriteContext;
import org.dizitart.no2.Nitrite;

/**
 *
 * @author tareq
 */
@Getter
@Setter
public class NitriteDao {

  protected Nitrite db;

  public NitriteDao() {

  }

  public NitriteDao(SecurityContext sc) {
    this.db = NitriteContext.ofUser((MultiTenancyUser) sc.getUserPrincipal()).getPersistenceContext().getDb();
  }

  public NitriteDao(Nitrite db) {
    this.db = db;
  }

  public static <T> T of(SecurityContext sc, Class<? extends Storeable<T>> clazz) {
    try {
      Storeable<T> newInstance = clazz.newInstance();
      T t = newInstance.getDao();
      NitriteDao.class.cast(t).setDb(NitriteContext.ofUser((MultiTenancyUser) sc.getUserPrincipal()).getPersistenceContext().getDb());
      return t;
    } catch (InstantiationException | IllegalAccessException ex) {
      throw new WebApplicationException(ex.getMessage(), 500);
    }

  }
}
