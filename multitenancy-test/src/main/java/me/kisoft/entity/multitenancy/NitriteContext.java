/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.entity.multitenancy;

import java.security.Principal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import javax.ws.rs.core.SecurityContext;
import org.dizitart.no2.Nitrite;

/**
 *
 * @author tareq
 */
public class NitriteContext implements MultiTenancyContext<NitriteConnection>, SecurityContext {

  private MultiTenancyUser user;
  private static ConcurrentHashMap< String, NitriteContext> tenancyMap = new ConcurrentHashMap<>();
  private NitriteConnection connection;

  public static NitriteContext ofUser(MultiTenancyUser tenant) {
    NitriteContext context = tenancyMap.get(tenant.getUsername());
    if (context == null) {
      context = new NitriteContext();
      context.setPersistenceContext(new NitriteConnection(Nitrite.builder()
        .filePath(tenant.getUsername())
        .openOrCreate(tenant.getUsername(), tenant.getPassword())));
      context.user = tenant;
      tenancyMap.put(tenant.getUsername(), context);
    }
    return context;
  }

  @Override
  public NitriteConnection getPersistenceContext() {
    return connection;
  }

  @Override
  public String getTenant() {
    return user.getName();
  }

  @Override
  public Principal getUserPrincipal() {
    return user;
  }

  @Override
  public boolean isUserInRole(String role) {
    return true;
  }

  @Override
  public boolean isSecure() {
    return true;
  }

  @Override
  public String getAuthenticationScheme() {
    return "Bearer";
  }

  @Override
  public void setPersistenceContext(NitriteConnection context) {
    this.connection = context;
  }

  public static void closeAll() {
    tenancyMap.forEach(new BiConsumer<String, NitriteContext>() {
      @Override
      public void accept(String t, NitriteContext u) {
        u.getPersistenceContext().getDb().commit();
        u.getPersistenceContext().getDb().close();
        tenancyMap.remove(t);
      }
    });
  }

}
