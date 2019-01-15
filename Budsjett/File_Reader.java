import java.util.HashMap;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


class File_Reader{
  static HashMap values = new HashMap<String, Integer>();
  static HashMap discount = new HashMap<String, Integer>();

  /**
   * Creates a HashMap and stores the values of the given textfile, on the form
   * '<key>: <value>'
   * @param String filename: The filename to be read
   * @return HashMap returnMap: All values in the file given as input
  **/
  public static HashMap get_values(String filename) throws FileNotFoundException{
    File valueFile = new File(filename);
    Scanner fn = new Scanner(valueFile);
    HashMap returnMap = new HashMap<String, Integer>();

    while(fn.hasNext()){
      String line = fn.nextLine();
      if(line.equals("\n") || line.equals("") || line.charAt(0) == '#'){
        continue;
      }
      String[] keys = line.split(": ");
      returnMap.put(keys[0], Integer.parseInt(keys[1]));
    }

    if(filename.contains("Cash_Flow_Discount")){
      discount = returnMap;
    }else if(filename.contains("Cash_Flow")){
      values = returnMap;
    }
    return returnMap;
  }

  /**
   * Adds a new transaction in the Cash_Flow.txt.
   * If the key s already exist, it adds the value i to the current value.
   *
   * @param String s: The key / category name
   * @param int i: The value of this category
  **/
  public static void add_value(String s, int i){
    int val = 0;
    if (values.get(s) != null){
      val = (Integer)values.get(s);
    }
    values.put(s, (i+val));
    System.out.println("Successfully added " + i + " to " + s);
    System.out.println("New value: " + (i+val));
  }

  /**
   * Adds a new transaction in the Cash_Flow_Discount.txt.
   * If the key s already exist, it adds the value i to the current value.
   *
   * @param String s: The key / Store name
   * @param int i: The value of this category
  **/
  public static void add_value_discount(String s, int original){
    Scanner in = new Scanner(System.in);
    System.out.print("What did you pay? ");
    String input = in.nextLine();

    int disc = 0; //Registers money spent
    if (input.matches("\\d+")){
      disc = Integer.parseInt(input);
    }else{
      System.out.println("Unkown input");
      return;
    }

    int val = 0; //Logs money spent
    if (discount.get(s) != null){
      val = (Integer)discount.get(s);
    }
    discount.put(s, (disc+val));

    double taxes = 0; //Checks if you have to tax the discount
    if(disc < (original/2)){
      taxes = disc*0.38;
    }

    disc = original - disc; //Logs the money saved
    s += " Discount";
    val = 0;
    if (discount.get(s) != null){
      val = (Integer)discount.get(s);
    }
    discount.put(s, (disc+val));

    val = 0; //Logs the taxes that must be paid
    if (discount.get("Taxes") != null){
      val = (Integer)discount.get("Taxes");
    }
    discount.put("Taxes", (int)(taxes+val));
  }

  /**
   * Adds all monthly fees in Monthly_Fees.txt into Cash_Flow.txt
   * @param String filename: The Monthly_Fees.txt file
  **/
  public static void add_value_monthly(String filename) throws FileNotFoundException{
    HashMap monthlyFees = get_values(filename);
    for(Object val : monthlyFees.keySet()){
      add_value(val+"", (int) monthlyFees.get(val));
    }
  }

  /**
   * Displays all categories stored in Cash_Flow.txt / HashMap values
  **/
  public static void show_categories(){
    System.out.println("Current categories:\t" + values.keySet());
  }

  /**
   * Overwrites the current Cash_Flow.txt with the new values, if any.
  **/
  public static void read_to_file() throws IOException{
    String filename = "Cash_Flow_" + Date.get_month() + Date.get_year() + ".txt";
    FileWriter fileWriter = new FileWriter(filename);
    fileWriter.write("# This file contains all transactions made.\n"
                    +"# Changing this will cause the program to crash.\n"
                    +"# Make sure you follow this rule if you want to add "
                    +"more categories directly:\n"
                    +"# <name>: <value>\n"
                    +"# Including the space\n\n");
    for(Object val : values.keySet()){
      String out = val + ": " + values.get(val) + System.getProperty("line.separator");
      fileWriter.write(out);
    }
    fileWriter.close();

    filename = "Cash_Flow_Discount" + Date.get_year() + ".txt";
    FileWriter fileWriter2 = new FileWriter(filename);
    fileWriter2.write("# This file contains all employee discount transactions made.\n"
                     +"# Changing this will cause the program to crash.\n"
                     +"# Make sure you follow this rule if you want to add"
                     +"more categories directly:\n"
                     +"# <name>: <value>\n# Including the space\n#\n"
                     +"# Taxes is a special case and shows the total amount of"
                     +"taxes that needs to be paid.\n\n");

    for(Object val : discount.keySet()){
      String out = val + ": " + discount.get(val) + System.getProperty("line.separator");
      fileWriter2.write(out);
    }
    fileWriter2.close();
  }

  /**
   * Displays the budget made in Budget.txt for the current month
   * @param String filename: The budget file. Should be Budget<month>.txt
  **/
  public static void show_budget(String filename) throws FileNotFoundException{
    HashMap budget = get_values(filename);
    int total = 0;

    for(Object val : budget.keySet()){
      int value = (int) budget.get(val);
      String key = val + "";

      for(int i = key.length()+1; i < 12; i++){
        key = " " + key;
      }
      total += value;
      System.out.println(key + " - " + value + ",-");
    }

    int totalSpent = 0;
    for(Object val : values.keySet()){
      String key = val + "";
      if(key.contains(" Income")){
        continue;
      }
      totalSpent += (int) values.get(key);
    }

    System.out.println("\n===---Transactions for " + Date.get_month() + "---===\n");
    for(Object val : values.keySet()){
      String key = val + "";
      if(key.contains(" Income")){
        System.out.println("\u001B[32m" + val + ": " + values.get(val));
      }else{
        System.out.println("\u001B[31m" + val + ": " + values.get(val));
      }
    }
    System.out.print("\u001B[0m");
    System.out.println("\nTOTAL BUDGET: " + total + ",-");
    System.out.println("TOTAL SPENT: " + totalSpent + ",-");
    int diff = total - totalSpent;
    String color = (diff >= 0) ?"\u001B[32m" : "\u001B[31m";
    System.out.println(color + "DIFFERENCE: " + (total-totalSpent) + ",-\n");
    System.out.print("\u001B[0m");
  }


  public static void show_budget_discount(String filename) throws FileNotFoundException{
    HashMap budget = get_values(filename);
    String[] out = new String[(budget.size()-1)/2];

    System.out.println("STORE\tSPENT\tSAVED");
    int i = 0;
    for(Object val : budget.keySet()){
      String key = val+"";
      if(key.equals("Taxes")){
        continue;
      }
      if(!key.contains(" Discount")){
        out[i] = key+"\t"+budget.get(key)+"\t"+budget.get(key+" Discount");
        i++;
      }
    }

    for(i = 0; i < out.length; i++){
      System.out.println(out[i]);
    }

    System.out.println("\n\u001B[31mTOTAL TAXES:\t"+budget.get("Taxes"));
    System.out.print("\u001B[0m");
  }
}
