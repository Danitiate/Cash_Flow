import java.util.HashMap;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

class Budget{
  public static void main(String[] args) {
    //Sets values
    HashMap values = new HashMap<String, Integer>();
    HashMap discount = new HashMap<String, Integer>();
    try{
      values = File_Reader.get_values("Cash_Flow_" + Date.get_month() + Date.get_year() + ".txt");
      discount = File_Reader.get_values("Cash_Flow_Discount" + Date.get_year() + ".txt");
    }catch (FileNotFoundException e){
      System.out.println("No file found!\nCreating new files...\n");
      try{
        File_Reader.read_to_file();
        System.out.println("File creation complete!\nRestarting program...\n");
        main(null);
      }catch(IOException f){
        System.out.println(f);
      }
      System.exit(0);
    }

    //User input loop
    Scanner in = new Scanner(System.in);
    User_Interface.menu(1);
    String c = "";
    while(!c.equals("9") && !c.equals("0")){
      c = in.next();
      if (c.matches("\\d+")){
        User_Interface.menu(Integer.parseInt(c));
      }else{
        User_Interface.menu(99);
      }
    }

    if(c.equals("9")){ //Save & Quit
      try{
        File_Reader.read_to_file();
      }catch(IOException e){
        System.out.println(e);
      }
    }
  }
}
