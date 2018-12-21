import java.util.HashMap;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


class File_Reader{
  final static File valueFile = new File("Cash_Flow.txt");
  final static File budgetFile = new File("Budget.txt");
  final static HashMap values = new HashMap<String, Integer>();

  public static HashMap get_values() throws FileNotFoundException{
    Scanner fn = new Scanner(valueFile);

    while(fn.hasNext()){
      String line = fn.nextLine();
      if(line.equals("\n")){
        break;
      }
      String[] keys = line.split(": ");
      values.put(keys[0], Integer.parseInt(keys[1]));
    }
    return values;
  }

  public static void add_value(String s, int i){
    int val = 0;
    if (values.get(s) != null){
      val = (Integer)values.get(s);
    }
    values.put(s, (i+val));
    System.out.println("Successfully added " + i + " to " + s);
    System.out.println("New value: " + (i+val));
  }

  public static void show_categories(){
    System.out.println("Current categories:\t" + values.keySet());
  }


  public static void read_to_file() throws IOException{
    FileWriter fileWriter = new FileWriter("Cash_Flow.txt");
    for(Object val : values.keySet()){
      String out = val + ": " + values.get(val) + System.getProperty("line.separator");
      fileWriter.write(out);
    }
    fileWriter.close();
  }


  public static void show_budget() throws FileNotFoundException{
    Scanner fn = new Scanner(budgetFile);
    int total = 0;

    while(fn.hasNext()){
      String line = fn.nextLine();
      if(line.equals("\n")){
        break;
      }
      String[] keys = line.split(": ");
      String key = keys[0];
      int val = Integer.parseInt(keys[1]);
      for(int i = key.length()+1; i < 12; i++){
        key = " " + key;
      }
      total += val;
      System.out.println(key + " - " + val + ",-");
    }

    System.out.println("\nTOTAL BUDGET: " + total + ",-");
    System.out.println("TOTAL SPENT: " + total + ",-");
    System.out.println("DIFFERENCE: " + total + ",-\n");
  }
}
