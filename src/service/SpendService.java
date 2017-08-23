package service;
 
import java.util.List;
 
import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;
 
public class SpendService {
 
    public SpendPage getSpendPage() {
        RecordDAO dao = new RecordDAO();
        // 本月数据
        List<Record> thisMonthRecords = dao.listThisMonth();
        // 今日数据
        List<Record> toDayRecords = dao.listToday();
        // 本月总天数
        int thisMonthTotalDay = DateUtil.thisMonthTotalDay();
 
        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;
 
        // 预算
        int monthBudget = new ConfigService().getIntBudget();
 
        // 统计本月消费
        for (Record record : thisMonthRecords) {
            monthSpend += record.getSpend();
        }
 
        // 统计今日消费
        for (Record record : toDayRecords) {
            todaySpend += record.getSpend();
        }
        // 计算日均消费
        avgSpendPerDay = monthSpend / thisMonthTotalDay;
        // 计算本月剩余
        monthAvailable = monthBudget - monthSpend;
 
        // 距离月末
        monthLeftDay = DateUtil.thisMonthLeftDay();
 
        // 计算日均可用
        dayAvgAvailable = monthAvailable / monthLeftDay;
 
        // 计算使用比例
        usagePercentage = monthSpend * 100 / monthBudget;
 
        // 根据这些信息，生成SpendPage对象
 
        return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthAvailable, dayAvgAvailable, monthLeftDay,
                usagePercentage);
    }
}