/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.run;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import me.kisoft.entity.multitenancy.NitriteContext;
import me.kisoft.utils.EntityManagerFactoryWrapper;

/**
 *
 * @author tareq
 */
@WebListener
public class Init implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    EntityManagerFactoryWrapper.create("MT_TEST");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    NitriteContext.closeAll();
    EntityManagerFactoryWrapper.destroy();
  }

}
