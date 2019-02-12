package buisness;

import amazon.Report.GetReportListByNextTokenSample;
import amazon.Report.GetReportListSample;
import amazon.Report.GetReportSample;
import amazon.Report.RequestReportSample;
import com.amazonaws.mws.model.*;
import model.Inventory;
import utility.Sleep;

import java.io.File;
import java.util.List;


/*******************************************
 * ReportManagerClass
 * Load setInventory function.
 * Load FBA multi country inventory.
 ******************************************/

public class ReportManager {

    /*******************************************
     * SetInventory method
     * -First load in FBA_temp database of:
     * -sku, asin, country, quantity and date.
     ******************************************/

    public static void setInventory() {
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
        Sleep.sleepSecond(23);

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

                    //TODO change directoy

                    //Delete file lista.txt
                    //File file = new File("/home/gate/software_g14/fba_report_list.txt");
                    File file = new File("C:\\Users\\Marco Aguglia\\Desktop\\lista.txt");
                    if (file.delete()) {
                        //Check file deleting and print that is ok.
                        System.out.println("caricamento in Database temporaneo terminato.\nFile cancellato dalla memoria." +
                                "\n\n\n Avvio confronto dati tabella principale");
                    }
                }
            }

            //close while loop for every nextToken
        } while (nextToken != null);


    }
}


