/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author tareq
 */
public interface Storeable<T> {

  @JsonIgnore
  public T getDao();
}
