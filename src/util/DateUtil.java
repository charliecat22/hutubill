package util;
 
import java.util.Calendar;
import java.util.Date;
 
public class DateUtil {
    static long millisecondsOfOneDay = 1000*60*60*24;   
 
    public static java.sql.Date util2sql(java.util.Date d){
        return  new java.sql.Date(d.getTime());
    }
     
    /**
     * 获取今天，并且把时，分，秒和毫秒都置0. 因为通过日期控件获取到的日期，就是没有时分秒和毫秒的。
     * @return
     */
    public static Date today(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        return c.getTime();
         
    }
 
    /**
     * 获取月初。使用Calendar获取本月第一天。 在统计消费一览信息的时候，查看本月的消费数据，其实就是从数据库中去把从本月第一天到最后一天的数据查出来，再进行简单统计，所以需要一个获取本月第一天的方法。
     * @return
     */
 
    public static Date monthBegin() {
    	Calendar c = Calendar.getInstance();
    	c.setTime(new Date());
    	c.set(Calendar.DATE, 1);
    	
    	c.set(Calendar.HOUR_OF_DAY, 0);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);
    	c.set(Calendar.MILLISECOND, 0);
    	return c.getTime();
    	
    }
     
    /**
     * 获取月末
     * @return
     */
    public static Date monthEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
         
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONDAY, 1);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }
 
    /**
     * 获取本月一共有多少天
     * @return
     */
    public static int thisMonthTotalDay(){
         
        long lastDayMilliSeconds = monthEnd().getTime();
        long firstDayMilliSeconds = monthBegin().getTime();
 
        return (int) ((lastDayMilliSeconds-firstDayMilliSeconds)/millisecondsOfOneDay) +1;
    }
     
    /**
     *获取本月还剩多少天 
     * @return
     */
     
    public static int thisMonthLeftDay(){
        long lastDayMilliSeconds = monthEnd().getTime();
        long toDayMilliSeconds = today().getTime();
        return (int) ((lastDayMilliSeconds-toDayMilliSeconds)/millisecondsOfOneDay) +1;
    }   
     
    public static void main(String[] args) {
        System.out.println(DateUtil.today());
        System.out.println(DateUtil.monthBegin());
        System.out.println(DateUtil.monthEnd());
        System.out.println(thisMonthLeftDay());
        System.out.println(thisMonthTotalDay());
    }
}