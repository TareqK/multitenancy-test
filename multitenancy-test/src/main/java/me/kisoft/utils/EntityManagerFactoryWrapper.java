/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tareq
 */
public class EntityManagerFactoryWrapper {

  private static EntityManagerFactory EMF;

  public static EntityManager getEntityManager() {
    return EMF.createEntityManager();
  }

  public static void destroy() {
    EMF.close();
  }

  public static void create(String puName) {
    EMF = Persistence.createEntityManagerFactory(puName);
  }
}
