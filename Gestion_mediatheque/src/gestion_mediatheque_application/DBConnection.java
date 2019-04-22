/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_mediatheque_application;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class DBConnection {
    
    
    public static String url = "jdbc:derby:mediatheque; create=true";
    public static Connection connexion =null;
    public static Statement statement =null;

    public static void AdherentTable() throws Exception{
          Connection connexion = null;
        
try {
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
       connexion = DriverManager.getConnection(url);
    Statement statement = connexion.createStatement();
      
      String sql = "CREATE TABLE IF NOT EXISTS ADHERENT(" +
                   "id_adherent int , " +
                   " nom VARCHAR(255), " + 
                   " prenom VARCHAR(255), " + 
                   " adresse VARCHAR(255), " + 
                   " PRIMARY KEY ( nom,prenom))";
 

      statement.executeUpdate(sql);
      System.out.println("Created table Adherent in database mediatheque");
   }catch(SQLException se){
      //Handle errors for JDBC
      
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(statement!=null)
            connexion.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(connexion!=null)
            connexion.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
    }
    
    
    public static void OeuvreTable(){
                  Connection connexion = null;
        
try {
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
       connexion = DriverManager.getConnection(url);

    Statement statement = connexion.createStatement();
      
      String sql = "CREATE TABLE OEUVRE(" +
                   "id_oeuvre int , " +
                   " titre VARCHAR(255), " + 
                   " auteur VARCHAR(255), " + 
                   " exemplaire int , " + 
                   " PRIMARY KEY ( titre,auteur ))";
 

      statement.executeUpdate(sql);
      System.out.println("Created table Oeuvre in database mediatheque");
   }catch(SQLException se){
      //Handle errors for JDBC
      
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(statement!=null)
            connexion.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(connexion!=null)
            connexion.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
    }
     
    
    
     public static void PretTable(){
                  Connection connexion = null;
        
try {
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
       connexion = DriverManager.getConnection(url);

    Statement statement = connexion.createStatement();
      
      String sql = "CREATE TABLE PRET " +
                   "(id_pret int NOT NULL, " +
                   " nom_adherent varchar(255), " + 
                   " oeuvre varchar(255), " + 
                   " date_de_pret DATE, " + 
                    " date_de_remise_attendue DATE, " + 
                   " PRIMARY KEY ( nom_adherent,oeuvre,date_de_pret ))";
 

      statement.executeUpdate(sql);
      System.out.println("Created table Pret database mediatheque");
   }catch(SQLException se){
      //Handle errors for JDBC
      
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(statement!=null)
            connexion.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(connexion!=null)
            connexion.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
     }
    
    
    
    
    
    
    public static void CreateDatabase() throws Exception {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
       connexion = DriverManager.getConnection(url);
        try (Statement st = connexion.createStatement()) {
            int Result = st.executeUpdate("CREATE DATABASE mediatheque\n");
           System.out.println("La base de donnée de la mediatheque a été crée");
        } catch (Exception e){
            System.err.println();
            
        }
    }

    public static Connection getConnection() throws Exception{
        try{
       Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
       connexion = DriverManager.getConnection(url);
    
    System.out.println("Connexion établie avec la base de donnée");
       return connexion;
        }
        catch (ClassNotFoundException | SQLException ex){
            System.err.println();
        }
        
        
        return null;
    }
    public static void addDateRemise(){
        
          Connection connexion = null;
        
try {
 Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
       connexion = DriverManager.getConnection(url);
    

    Statement statement = connexion.createStatement();
      
      String sql = "ALTER TABLE pret ADD date_de_remise_reelle DATE";
 

      statement.executeUpdate(sql);
      System.out.println("Add Date de remise reelle in database mediatheque");
   }catch(SQLException se){
      //Handle errors for JDBC
      
   }catch(ClassNotFoundException e){
      //Handle errors for Class.forName
     
   }finally{
      //finally block used to close resources
      try{
         if(statement!=null)
            connexion.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(connexion!=null)
            connexion.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
    }
        
    }

