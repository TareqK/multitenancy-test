/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.service;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import me.kisoft.dao.NitriteDao;
import me.kisoft.entity.Box;
import me.kisoft.entity.multitenancy.Secured;

/**
 *
 * @author tareq
 */
@Path("box")
public class BoxService {

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Secured
  public List<Box> listBoxs(@Context SecurityContext sc) {
    return NitriteDao.of(sc, Box.class).listBoxs();
  }

  @GET
  @Path("{box_id}")
  @Produces({MediaType.APPLICATION_JSON})
  @Secured
  public Box getBoxs(@Context SecurityContext sc, @PathParam("box_id") long boxId) {
    return NitriteDao.of(sc, Box.class).getBoxById(boxId);
  }

  @DELETE
  @Path("{box_id}")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  public void deleteBox(@Context SecurityContext sc, @PathParam("box_id") long boxId) {
    NitriteDao.of(sc, Box.class).deleteBox(boxId);
  }

  @PUT
  @Path("{box_id}")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void deleteBox(@Context SecurityContext sc, @PathParam("box_id") long boxId, Box box) {
    NitriteDao.of(sc, Box.class).updateBox(box, boxId);
  }

  @POST
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  @Secured
  public void createBox(@Context SecurityContext sc, Box box) {
    NitriteDao.of(sc, Box.class).createBox(box);
  }
}
