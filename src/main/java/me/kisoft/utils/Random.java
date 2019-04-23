/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.utils;

/**
 *
 * @author tareq
 */
public class Random {

  public static String randomToken(int length) {
    String subset = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder sb = new StringBuilder();
    java.util.Random r = new java.util.Random();
    for (int i = 0; i < length; i++) {
      int index = r.nextInt(subset.length());
      char c = subset.charAt(index);
      sb.append(c);
    }
    return sb.toString();
  }
}
