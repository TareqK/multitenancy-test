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
import me.kisoft.dao.NoteDao;
import me.kisoft.entity.Note;
import me.kisoft.entity.multitenancy.Secured;

/**
 *
 * @author tareq
 */
@Path("note")
public class NoteService {

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Secured
  public List<Note> listNotes(@Context SecurityContext sc) {
    return new NoteDao(sc).listNotes();
  }

  @GET
  @Path("{note_id}")
  @Produces({MediaType.APPLICATION_JSON})
  @Secured
  public Note getNotes(@Context SecurityContext sc, @PathParam("note_id") long noteId) {
    return new NoteDao(sc).getNoteById(noteId);
  }

  @DELETE
  @Path("{note_id}")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  public void deleteNote(@Context SecurityContext sc, @PathParam("note_id") long noteId) {
    new NoteDao(sc).deleteNote(noteId);
  }

  @PUT
  @Path("{note_id}")
  @Secured
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public void deleteNote(@Context SecurityContext sc, @PathParam("note_id") long noteId, Note note) {
    new NoteDao(sc).updateNote(note, noteId);
  }

  @POST
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  @Secured
  public void createNote(@Context SecurityContext sc, Note note) {
    new NoteDao(sc).createNote(note);
  }
}
