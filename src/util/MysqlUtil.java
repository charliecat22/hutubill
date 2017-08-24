package util;
 
import java.io.BufferedReader;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
 
public class MysqlUtil {
 
    public static void backup(String mysqlPath, String backupfile) throws IOException {
        String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s   -hlocalhost   -P%d %s -r \"%s\"";
 
        String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password, DBUtil.port,
                DBUtil.database, backupfile);
        Runtime.getRuntime().exec(command);
    }
 
    public static void recover(String mysqlPath, String recoverfile) {
        try {
            String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s   %s ";
            String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password,
                    DBUtil.database);
 
            Process p = Runtime.getRuntime().exec(command);
            OutputStream out = p.getOutputStream();
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverfile), "utf8"));
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
 
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
            writer.flush();
            out.close();
            br.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    public static void main(String[] args) throws IOException {
        String mysqlPath = "D:/tools/MYSQL/mysql-5.1.57-win32";
        String file = "C:/Documents and Settings/Administrator/My Documents/hutubill.sql";
 
        // backup(mysqlPath, file);
        // restore();
        // recover(mysqlPath, file);
        // recover(file);
        recover(mysqlPath, file);
 
    }
 
}