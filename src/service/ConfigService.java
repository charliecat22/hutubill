package service;
 
import javax.naming.InitialContext;

import dao.ConfigDAO;
import entity.Config;
 
public class ConfigService {
        public static final String budget ="budget";
        public static final String mysqlPath = "mysqlPath";
        public static final String default_budget = "500";
        
        static ConfigDAO dao = new ConfigDAO();
        static{
        	init();
        }
        
        public static void init(){
        	init(budget,default_budget);
        	init(mysqlPath,"");
        }
        
        public static void init(String key,String value) {
			Config config = dao.getByKey(key);
			if (config == null) {
				Config c = new Config();
				c.setKey(key);
				c.setValue(value);
				dao.add(c);
			}
			
		}
        
        public String get(String key){
        	Config config = dao.getByKey(key);
        	return config.getValue();
        }
        
        public void update(String key,String value){
        	Config config = dao.getByKey(key);
        	config.setValue(value);
        	dao.update(config);
        }
        
        public int getIntBudget() {
			return Integer.parseInt(get(budget));
		}
        
}