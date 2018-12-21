import java.util.Calendar;

class Date{
  public static String get_month(){
    Calendar cal = Calendar.getInstance();
    int m = cal.get(Calendar.MONTH);
    String[] monthNames = {"January", "February", "March", "April", "May",
                            "June", "July", "August", "September", "October",
                            "November", "December"};
    return monthNames[m];
  }
}
