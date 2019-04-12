//import buisness.SqsManager;

import buisness.PriceManager;
import buisness.ReportManager;
import model.Inventory;

import java.io.IOException;

/*******************************************
 * MainClass
 * Load setTempInventory function.
 * Load FBA multi country inventory.
 ******************************************/

public class Launcher {
    private final static String os = System.getProperty("os.name");

    public static void main(String[] args) {
        Inventory.truncateTempTable();
        ReportManager.setTempInventory();
        PriceManager.setPrice();


        //Clean console
        /**********************************************************************************/
        if (!os.contains("Windows")) {
            try {
                Runtime.getRuntime().exec("clear");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**********************************************************************************/

    }
}
