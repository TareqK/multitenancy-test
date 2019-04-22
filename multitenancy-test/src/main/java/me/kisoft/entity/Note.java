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
import org.dizitart.no2.objects.Id;

/**
 *
 * @author tareq
 */
@Getter
@Setter
public class Note implements Serializable {

  @Id
  private long noteId;

  private String content;

  private String category;

  private ArrayList<Long> relatedNotes;
}
