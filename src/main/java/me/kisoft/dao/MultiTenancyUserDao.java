/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import me.kisoft.entity.multitenancy.MultiTenancyUser;
import me.kisoft.utils.EntityManagerFactoryWrapper;
import me.kisoft.utils.Random;

/**
 *
 * @author tareq
 */
public class MultiTenancyUserDao implements AutoCloseable {

  private EntityManager em = EntityManagerFactoryWrapper.getEntityManager();

  @Override
  public void close() {
    em.close();
  }

  public MultiTenancyUser login(MultiTenancyUser user) {
    em.getTransaction().begin();
    try {
      MultiTenancyUser foundUser = em.createNamedQuery("MultiTenancyUser.login", MultiTenancyUser.class)
        .setParameter("username", user.getUsername())
        .setParameter("password", user.getPassword())
        .getSingleResult();
      foundUser.setToken(Random.randomToken(30));
      return foundUser;
    } catch (NoResultException ex) {
      throw new WebApplicationException(403);
    } finally {
      em.getTransaction().commit();
    }
  }

  public MultiTenancyUser authorize(String token) {
    try {
      return em.createNamedQuery("MultiTenancyUser.byToken", MultiTenancyUser.class)
        .setParameter("token", token)
        .getSingleResult();
    } catch (NoResultException ex) {
      throw new WebApplicationException(403);
    }
  }
}
