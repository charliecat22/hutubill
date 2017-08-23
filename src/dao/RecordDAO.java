package dao;
 
import java.sql.Connection;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import entity.Record;
import util.DBUtil;
import util.DateUtil;
  
public class RecordDAO {
  
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from record";
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
  
            System.out.println("total:" + total);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
    }
  
    public void add(Record record) {
  
        String sql = "insert into record values(null,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, record.spend);
            ps.setInt(2, record.cid);
            ps.setString(3, record.comment);
            ps.setDate(4, DateUtil.util2sql(record.date));
  
            ps.execute();
  
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                record.id = id;
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public void update(Record record) {
  
        String sql = "update record set spend= ?, cid= ?, comment =?, date = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
              ps.setInt(1, record.spend);
              ps.setInt(2, record.cid);
              ps.setString(3, record.comment);
              ps.setDate(4, DateUtil.util2sql(record.date));
              ps.setInt(5, record.id);
  
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
    }
  
    public void delete(int id) {
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from record where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public Record get(int id) {
        Record record = null;
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from record where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                record = new Record();
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                 
                record.spend=spend;
                record.cid=cid;
                record.comment=comment;
                record.date=date;
                record.id = id;
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return record;
    }
  
    public List<Record> list() {
        return list(0, Short.MAX_VALUE);
    }
     
    public List<Record> list(int start, int count) {
        List<Record> records = new ArrayList<Record>();
  
        String sql = "select * from record order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                 
                 String comment = rs.getString("comment");
                 Date date = rs.getDate("date");
                  
                 record.spend=spend;
                 record.cid=cid;
                 record.comment=comment;
                 record.date=date;
                 record.id = id;
                records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }
     
    public List<Record> list(int cid) {
        List<Record> records = new ArrayList<Record>();
  
        String sql = "select * from record where cid = ?";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, cid);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                 
                 String comment = rs.getString("comment");
                 Date date = rs.getDate("date");
                  
                 record.spend=spend;
                 record.cid=cid;
                 record.comment=comment;
                 record.date=date;
                 record.id = id;
                records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }    
     
    public List<Record> listToday(){
        return list(DateUtil.today());
    }
     
    public List<Record> list(Date day) {
        List<Record> records = new ArrayList<Record>();
        String sql = "select * from record where date =?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(day));
 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                int cid = rs.getInt("cid");
                int spend = rs.getInt("spend");
                 
                 String comment = rs.getString("comment");
                 Date date = rs.getDate("date");
                  
                 record.spend=spend;
                 record.cid=cid;
                 record.comment=comment;
                 record.date=date;
                 record.id = id;
                 records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }            
     
    public List<Record> listThisMonth(){
        return list(DateUtil.monthBegin(),DateUtil.monthEnd());
    }
     
    public List<Record> list(Date start, Date end) {
        List<Record> records = new ArrayList<Record>();
        String sql = "select * from record where date >=? and date <= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(start));
            ps.setDate(2, DateUtil.util2sql(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                int cid = rs.getInt("cid");
                int spend = rs.getInt("spend");
                 
                 String comment = rs.getString("comment");
                 Date date = rs.getDate("date");
                  
                 record.spend=spend;
                 record.cid=cid;
                 record.comment=comment;
                 record.date=date;
                 record.id = id;
                 records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }        
  
}