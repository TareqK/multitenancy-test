/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.dao;

import java.util.List;
import lombok.AllArgsConstructor;
import me.kisoft.entity.Note;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

/**
 *
 * @author tareq
 */
@AllArgsConstructor
public class NoteDao extends NitriteDao {

  public Note getNoteById(long noteId) {
    return db.getRepository(Note.class).find(eq("noteId", noteId)).firstOrDefault();
  }

  public List<Note> listNotes() {
    return db.getRepository(Note.class).find().toList();
  }

  public void createNote(Note note) {
    db.getRepository(Note.class).insert(note);
  }

  public void updateNote(Note note, long noteId) {
    db.getRepository(Note.class).update(eq("noteId", noteId), note, true);
  }

  public void deleteNote(long noteId) {
    db.getRepository(Note.class).remove(eq("noteId", noteId));
  }

}
