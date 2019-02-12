package DAO;

import Db_connection.Db_connection;

import java.util.ArrayList;

public class Inventory_DAO {

    public static Inventory_DAO instance;

    public static synchronized Inventory_DAO getInstance() {
        if (instance == null)
            instance = new Inventory_DAO();
        return instance;
    }

    public void loadInventoryTemp() {
        //Db_connection.getInstance().eseguiAggiornamento(" LOAD DATA LOCAL INFILE '/home/gate/software_g14/fba_report_list.txt'\n" +
        Db_connection.getInstance().eseguiAggiornamento(" LOAD DATA LOCAL INFILE 'C:/Users/Marco/Desktop/lista.txt'\n" +
                "        INTO TABLE FBA_temp\n" +
                "        FIELDS TERMINATED by '\\t'\n" +
                "        LINES TERMINATED BY '\\n'\n" +
                "        IGNORE 1 LINES\n" +
                "        (sku,fulfillmenChannelsku,asin,conditionType,country,quantityLocalFulfillment)" +
                "SET data = NOW();");
    }

    public int findSizeInventoryTemp() {
        ArrayList<String[]> result = Db_connection.getInstance().eseguiQuery("SELECT count(*) FROM FBA_temp");
        String[] x = result.get(0);
        return Integer.parseInt(x[0]);
    }

    public int findSizeInventoryStatus() {
        ArrayList<String[]> result = Db_connection.getInstance().eseguiQuery("SELECT count(*) FROM FBA_status");
        String[] x = result.get(0);
        return Integer.parseInt(x[0]);
    }

    public void truncateFba_Temp() {
        Db_connection.getInstance().eseguiAggiornamento("truncate table FBA_temp");
    }

}
