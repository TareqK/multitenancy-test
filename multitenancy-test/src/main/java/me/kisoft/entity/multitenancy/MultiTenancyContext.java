/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.entity.multitenancy;

/**
 *
 * @author tareq
 * @param <T>
 */
public interface MultiTenancyContext<T> {

  public T getPersistenceContext();

  public void setPersistenceContext(T context);

  public String getTenant();

}
