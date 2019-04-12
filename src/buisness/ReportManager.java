package buisness;

import amazon.Report.GetReportListByNextTokenSample;
import amazon.Report.GetReportListSample;
import amazon.Report.GetReportSample;
import amazon.Report.RequestReportSample;
import com.amazonaws.mws.model.*;
import model.Inventory;
import model.Product;
import utility.Sleep;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*******************************************
 * ReportManagerClass
 * Load setTempInventory function.
 * Load FBA multi country inventory.
 ******************************************/

public class ReportManager {
    private final static String os = System.getProperty("os.name");

    /*******************************************
     * SetInventory method
     * -First load in FBA_temp database of:
     * -sku, asin, country, quantity and date.
     ******************************************/

    public static void setTempInventory() {
        GetReportListResponse response;
        GetReportListByNextTokenResponse responseToken;
        String nextToken = null;
        List<ReportInfo> reportInfoListList;


        // Check FBA_temp is empty
        if (Inventory.findSizeInventoryTemp() != 0) {
            Inventory.truncateTempTable();
        }
        // Request new Report
        RequestReportSample.requestReport();
        // Wait that new Report has been generated.
        Sleep.sleepSecond(30);

        // Do while loop for execute GetReportList for all nextToken value
        do {
            // GetReportList without nextToken
            if (nextToken == null || nextToken.equals("")) {
                response = GetReportListSample.getReportList();
                GetReportListResult getReportListResult = response.getGetReportListResult();
                nextToken = getReportListResult.getNextToken();
                reportInfoListList = getReportListResult.getReportInfoList();

            } else { //GetReportList with nextToken
                responseToken = GetReportListByNextTokenSample.getReportListByNextToken(nextToken);
                GetReportListByNextTokenResult getReportListResult = responseToken.getGetReportListByNextTokenResult();
                nextToken = getReportListResult.getNextToken();
                reportInfoListList = getReportListResult.getReportInfoList();
            }

            //foreach reportInfo variable in report InfoListList SEARCH last _GET_AFN_INVENTORY_DATA_BY_COUNTRY_
            for (ReportInfo reportInfo : reportInfoListList) {
                //Get reportId and ReportType
                String reportId = reportInfo.getReportId();
                String reportType = reportInfo.getReportType();

                // check _GET_AFN_INVENTORY_DATA_BY_COUNTRY_

                if (reportType.equals("_GET_AFN_INVENTORY_DATA_BY_COUNTRY_")) {

                    //Download report of reportId finded, download info in lista.txt temp file
                    GetReportSample.getReport(reportId);
                    //Upload on Database lista.txt temp file.
                    Inventory.loadInventoryTemp();

                    //Delete file lista.txt
                    File file = new File("/home/gate/software_g14/fba_report_list.txt");
                    //    File file = new File("C:\\Users\\Marco Aguglia\\Desktop\\lista.txt");
                    if (file.delete()) {
                        //Check file deleting and print that is ok.
                        System.out.println("caricamento FBA_temp terminato.\nFile cancellato dalla memoria.");
                    }
                    break;
                }

            }
            //close while loop for every nextToken
        } while (nextToken != null);
        setInventory();
    }

    public static void setInventory() {
        int fba_Temp_Size = Inventory.findSizeInventoryTemp();
        //InsertProduct,PriceError counter
        int product_inserted = 0;


        if (fba_Temp_Size != 0) {
            ArrayList<String[]> result = Product.findProduct();
            System.out.println("Controllo giacenze a zero...");
            for (int j = 0; j < result.size(); j++) {

                Product p = Product.findTempProduct_for_Sku((result.get(j))[0], (result.get(j))[3]);
                if (p == null) {
                    p = new Product();
                    p.setSku((result.get(j))[0]);
                    p.setCountry((result.get(j))[3]);
                    p.setAsin((result.get(j))[2]);
                    p.setGiacenza("0");
                    p.setFullfillmentChannelSku((result.get(j))[1]);

                    Product productStatus = Product.findInventoryProduct(p.getSku(), p.getCountry(), p.getGiacenza());
                    if (productStatus == null || !(productStatus.getGiacenza().equals(p.getGiacenza()))) {
                        //Insert in FBA_status
                        if (p.getFullfillmentChannelSku() != null)
                            Product.insertProductInventory(p);

                    }
                }


            }
        }
        //Scan every item of Fba_temp
        for (int i = 0; i < fba_Temp_Size; i++) {
            //Print Status of process
            /***************************************************************************/
            System.out.println("Caricamento: " + (i * 100) / fba_Temp_Size + "%" +
                    "\tElemento: " + i + " : " + fba_Temp_Size +
                    "\t" + product_inserted + " Aggiunti");
            /*******************************************************************/
            //Get i-item from FBA_temp
            Product productTemp = Product.findTempProduct(i);
            //Search and get if available a clone productTemp
            Product productStatus = Product.findInventoryProduct(productTemp.getSku(), productTemp.getCountry(), productTemp.getGiacenza());

            // if clone exist in product status, jump insert
            if (productStatus == null || !(productStatus.getGiacenza().equals(productTemp.getGiacenza()))) {

                //Insert in FBA_status
                Product.insertSkus(productTemp.getSku(), productTemp.getAsin());
                Product.insertProductInventory(productTemp);

                //incremet counter
                product_inserted++;
            }


            //Clean console
            /**********************************************************************************/
            if (os.contains("Windows")) {
                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Runtime.getRuntime().exec("clear");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /**********************************************************************************/
        }
        //Truncate FBA_temp
        Inventory.truncateTempTable();

    }

}


