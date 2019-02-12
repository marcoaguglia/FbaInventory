package model;

import DAO.Inventory_DAO;

public class Inventory {

    public static void loadInventoryTemp() {
        Inventory_DAO.getInstance().loadInventoryTemp();
    }

    public static int findSizeInventoryTemp() {
        return Inventory_DAO.getInstance().findSizeInventoryTemp();
    }


    public static void truncateTempTable() {
        Inventory_DAO.getInstance().truncateFba_Temp();
    }
}

