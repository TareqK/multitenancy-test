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
import org.mindrot.jbcrypt.BCrypt;

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

  public MultiTenancyUser signin(MultiTenancyUser user) {
    em.getTransaction().begin();
    try {
      MultiTenancyUser foundUser = getUserByUsername(user.getUsername());
      if (BCrypt.checkpw(user.getPassword(), foundUser.getPassword())) {
        foundUser.setToken(Random.randomToken(30));
        return foundUser;
      } else {
        throw new WebApplicationException(403);
      }

    } catch (NoResultException ex) {
      throw new WebApplicationException(403);
    } finally {
      em.getTransaction().commit();
    }
  }

  public MultiTenancyUser signup(MultiTenancyUser user) {
    em.getTransaction().begin();
    try {
      em.createNamedQuery("MultiTenancyUser.checkExisting", Long.class)
        .setParameter("username", user.getUsername())
        .setParameter("email", user.getEmail())
        .getSingleResult();
      throw new WebApplicationException(409);
    } catch (NoResultException ex) {
      em.persist(user);
      return user;
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

  private MultiTenancyUser getUserByUsername(String username) {
    return em.createNamedQuery("MultiTenancyUser.byUsername", MultiTenancyUser.class)
      .setParameter("username", username)
      .getSingleResult();
  }
}
