/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import me.kisoft.dao.MultiTenancyUserDao;
import me.kisoft.entity.multitenancy.MultiTenancyUser;

/**
 *
 * @author tareq
 */
@Path("signin")
public class SignInService {

  @POST
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public MultiTenancyUser login(MultiTenancyUser user) {
    try (MultiTenancyUserDao dao = new MultiTenancyUserDao()) {
      return dao.signin(user);
    }
  }

}
