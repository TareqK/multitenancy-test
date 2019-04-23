/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.dao.NoteDao;
import me.kisoft.dao.Storeable;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author tareq
 */
@Getter
@Setter
public class Note implements Serializable, Storeable<NoteDao> {

  @Id
  private long noteId;

  private String content;

  private String category;

  private ArrayList<Long> relatedNotes;

  @Override
  public NoteDao getDao() {
    return new NoteDao();
  }
}