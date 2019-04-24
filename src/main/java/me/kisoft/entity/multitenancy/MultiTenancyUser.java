/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.entity.multitenancy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.security.Principal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQueries({
  @NamedQuery(name = "MultiTenancyUser.byUsername", query = "SELECT mtu FROM MultiTenancyUser mtu WHERE mtu.username=(:username)"),
  @NamedQuery(name = "MultiTenancyUser.byToken", query = "SELECT mtu FROM MultiTenancyUser mtu WHERE mtu.token=(:token)"),
  @NamedQuery(name = "MultiTenancyUser.checkExisting", query = "SELECT mtu.userId FROM MultiTenancyUser mtu WHERE mtu.username = (:username) OR mtu.email = (:email)")})
public class MultiTenancyUser implements Principal, Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userId;

  @Column(unique = true, nullable = false, length = 32)
  private String username;

  @JsonIgnore
  @Setter(onMethod_ = {
    @JsonProperty})
  @Getter(onMethod_ = {
    @JsonIgnore})
  private String password;

  private String email;

  private String token;

  @Override
  public String getName() {
    return username;
  }

  @JsonIgnore
  private transient NitriteContext persistenceContext;

  @PostLoad
  public void resolvePersistenceContext() {
    this.persistenceContext = NitriteContext.ofUser(this);
  }

  @PrePersist
  public void hashPassword() {
    password = BCrypt.hashpw(password, BCrypt.gensalt());
  }
}
