import java.util.Calendar;

//Used to get the current month and year. Abstracted from the Calendar class.
class Date{
  public static String get_month(){
    Calendar cal = Calendar.getInstance();
    int m = cal.get(Calendar.MONTH);
    String[] monthNames = {"January", "February", "March", "April", "May",
                            "June", "July", "August", "September", "October",
                            "November", "December"};
    return monthNames[m];
  }


  public static int get_year(){
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    return year;
  }
}
