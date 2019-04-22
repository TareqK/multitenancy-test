/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.dao;

import lombok.Getter;
import lombok.Setter;
import org.dizitart.no2.Nitrite;

/**
 *
 * @author tareq
 */
@Getter
@Setter
public abstract class NitriteDao {

  protected Nitrite db;
}
