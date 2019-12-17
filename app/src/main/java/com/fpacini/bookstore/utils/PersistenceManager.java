package com.fpacini.bookstore.utils;

import android.content.Context;
import android.util.Log;

import com.fpacini.bookstore.model.Book;
import com.fpacini.bookstore.model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class PersistenceManager
{
   private ArrayList<Book> catalogo;
   private ArrayList<User> users;
   private static PersistenceManager persistenceManager;

   private PersistenceManager(){

   }

   public static PersistenceManager getInstance() {
      if (persistenceManager == null)
         persistenceManager = new PersistenceManager();

      return persistenceManager;
   }

   public void init(){
      catalogo = new ArrayList<>();
      users = new ArrayList<>();

      //caricamento di due utenti hard-coded (per il LogIn usare password decriptata "pippo" per entrambe)
      users.add(new User(1,"Marco","Rossi","m.rossi@gmail.com","0c88028bf3aa6a6a143ed846f2be1ea4"));
      users.add(new User (2,"Sara","Bianchi", "s.bianchi@gmail.com", "0c88028bf3aa6a6a143ed846f2be1ea4"));

      //caricamento di due liste di libri hard-coded (rispettivamente associate ai due utenti tramite attributo 'userId')
      catalogo.add(new Book("Il codice Da Vinci", "Dan Brown",1 ,"456444555", 18.0f,"Feltrinelli" ));
      catalogo.add(new Book("Il gioco del suggeritore", "Donato Carrisi",1, "6585858484", 25.0f,"Feltrinelli"));
      catalogo.add(new Book("La ragazza scomparsa", "Angela Marsons",1, "4523431555", 10.0f,"Mondadori" ));

      catalogo.add(new Book("IT", "Stephen King" ,2,  "456466555", 20.0f,"Feltrinelli" ));
      catalogo.add(new Book("Promessi Sposi", "Alessandro Manzoni",2, "7568699488", 20.5f,"Mondadori"));
      catalogo.add(new Book("La tempesta del secolo", "Stephen King", 2,"55432244", 12.0f, "Mondadori"));
   }

   public void addUser(User user){
      users.add(user);
   }

   public ArrayList<Book> getCatalogo() {
      return catalogo;
   }

   public ArrayList<User> getUsers() {
      return users;
   }

   //metodo che restituisce un nuovo id ad ogni utente che si registra, in modo incrementale rispetto a quelli già inseriti hard-coded
  /*
  milosVersion
  public int newId(){
      int maxId = 0;
      int currentId=0;
      for (User u : users){
         currentId = u.getId();
         if (currentId >= maxId){
            maxId = currentId;
         }
      }
      maxId++;
      return maxId;
   }*/

   public int getNewId(){
      int maxId = users.get(0).getId();
      for(int i=1; i<users.size(); i++){
         int currentId = users.get(i).getId();
         if(maxId < currentId){
            maxId = currentId;
         }
      }
      maxId++;
      return  maxId;
   }

}

