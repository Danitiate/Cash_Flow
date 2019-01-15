import java.io.FileNotFoundException;
import java.util.Scanner;

class User_Interface{
  public static void menu(int c){
    Scanner in = new Scanner(System.in);
    switch(c){
      case 1: System.out.println("WELCOME TO THE BUDGET MAIN MENU!\n");
              System.out.println("1. Displays this menu");
              System.out.println("2. Display monthly budget");
              System.out.println("3. Display yearly employee discount");
              System.out.println("4. Set a budget");
              System.out.println("5. Log a transaction");
              System.out.println("6. Log income");
              System.out.println("7. Log employee discount");
              System.out.println("9. Save & Quit");
              System.out.println("0. Quit without saving");
              break;

      case 2: System.out.println("\n===---Budget for " + Date.get_month() + "---===\n");
              try{
                File_Reader.show_budget("Budget.txt");
              }catch (FileNotFoundException e){
                System.out.println(e);
              }
              break;

      case 3: System.out.println("\n===---Employee discount for " + Date.get_year() + "---===\n");
              try{
                File_Reader.show_budget_discount("Cash_Flow_Discount" + Date.get_year() + ".txt");
              }catch (FileNotFoundException e){
                System.out.println(e);
              }
              break;


      case 5:
      case 6: File_Reader.show_categories();
              System.out.print("Choose a category: ");
      case 7: if(c == 7) System.out.print("What store? ");
              String cat = in.nextLine();
              if(c == 6) cat += " Income";
              if(cat.equals("")){
                cat = "Uncategorized";
              }else if(cat.equals("-m") || cat.equals("-M") && c == 5){
                try{
                  File_Reader.add_value_monthly("Monthly_Fees.txt");
                }catch (FileNotFoundException e){
                  System.out.println(e);
                }
                menu(1);
                break;
              }
              if (c == 5 || c == 6) System.out.print("Choose a value: ");
              if (c == 7) System.out.print("Original price: ");
              String val = in.nextLine();
              if (val.matches("\\d+")){
                if (c == 5 || c == 6) File_Reader.add_value(cat, Integer.parseInt(val));
                if (c == 7) File_Reader.add_value_discount(cat, Integer.parseInt(val));
              }else{
                System.out.println("Unkown input");
              }
              menu(1);
              break;

      case 9:
      case 0: System.out.println("\nQuitting...\n");
              break;
      default: System.out.println("Unkown command\n");
    }
  }
}
