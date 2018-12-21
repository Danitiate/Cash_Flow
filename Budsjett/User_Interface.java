import java.io.FileNotFoundException;
import java.util.Scanner;

class User_Interface{
  public static void menu(int c){
    switch(c){
      case 1: System.out.println("WELCOME TO THE BUDGET MAIN MENU!\n");
              System.out.println("1. Displays this menu");
              System.out.println("2. Display monthly budget");
              System.out.println("4. Set a budget");
              System.out.println("5. Log a transaction");
              System.out.println("6. Log income");
              System.out.println("9. Save & Quit");
              System.out.println("0. Quit without saving");
              break;

      case 2: System.out.println("\n===---Budget for " + Date.get_month() + "---===\n");
              try{
                File_Reader.show_budget();
              }catch (FileNotFoundException e){
                System.out.println(e);
              }
              break;

      case 5: File_Reader.show_categories();
              System.out.print("Choose a category: ");
              Scanner in = new Scanner(System.in);
              String cat = in.nextLine();
              if(cat.equals("")){
                cat = "Uncategorized";
              }
              System.out.print("Choose a value: ");
              String val = in.nextLine();
              if (val.matches("\\d+")){
                File_Reader.add_value(cat, Integer.parseInt(val));
              }else{
                System.out.println("Unkown input");
                menu(1);
              }
              break;
      case 9:
      case 0: System.out.println("\nQuitting...\n");
              break;
      default: System.out.println("Unkown command\n");
    }
  }
}
