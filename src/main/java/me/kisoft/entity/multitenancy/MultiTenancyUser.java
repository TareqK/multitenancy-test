/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.entity.multitenancy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.security.Principal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQueries({
  @NamedQuery(name = "MultiTenancyUser.login", query = "SELECT mtu FROM MultiTenancyUser mtu WHERE mtu.username=(:username) AND mtu.password=(:password)"),
  @NamedQuery(name = "MultiTenancyUser.byToken", query = "SELECT mtu FROM MultiTenancyUser mtu WHERE mtu.token=(:token)")})
public class MultiTenancyUser implements Principal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userId;

  private String username;

  @JsonIgnore
  @Setter(onMethod_ = {
    @JsonProperty})
  @Getter(onMethod_ = {
    @JsonIgnore})
  private String password;

  private String token;

  @Override
  public String getName() {
    return username;
  }

  @JsonIgnore
  private transient NitriteContext persistenceContext;

  @PostLoad
  private void resolvePersistenceContext() {
    this.persistenceContext = NitriteContext.ofUser(this);
  }

}
