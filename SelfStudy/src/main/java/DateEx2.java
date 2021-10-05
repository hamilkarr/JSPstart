import java.util.*;

public class DateEx2 {
  public static void main(String[] args) {
    Calendar today = Calendar.getInstance();
    System.out.println(toDate(today));
    System.out.println(toTime(today));
    System.out.println(toYoil(today));

    int zoneOffset = today.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000);
    System.out.println(zoneOffset);

    System.out.println("날짜 설정===============");
    today.set(2020, 8, 28);
    System.out.println(toDate(today));
    System.out.println(toTime(today));
  }

  public static String toDate(Calendar date) {
    return date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.DATE);
  }

  public static String toTime(Calendar date) {
    String amPm = (date.get(Calendar.AM_PM) == 0) ? "오전" : "오후";
    return date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND) + "(" + amPm
        + ")";
  }

  public static String toYoil(Calendar date) {

    String[] yoils = { "", "일", "월", "화", "수", "목", "금", "토" };
    int yoil = date.get(Calendar.DAY_OF_WEEK);
    return yoils[yoil];
  }
}
