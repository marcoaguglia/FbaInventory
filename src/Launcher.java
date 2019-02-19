//import buisness.SqsManager;

import buisness.PriceManager;
import buisness.ReportManager;
import model.Inventory;

/*******************************************
 * MainClass
 * Load setTempInventory function.
 * Load FBA multi country inventory.
 ******************************************/

public class Launcher {
    public static void main(String[] args) {
        Inventory.truncateTempTable();
        ReportManager.setTempInventory();
        PriceManager.setPrice();

    }
}
