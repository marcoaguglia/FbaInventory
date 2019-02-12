/******************************************************************************* 
 *  Copyright 2009 Amazon Services.
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 *  You may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 *  This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 *  CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 *  specific language governing permissions and limitations under the License.
 * ***************************************************************************** 
 *
 *  Marketplace Web Launcher Java Library
 *  API Version: 2009-01-01
 *  Generated: Wed Feb 18 13:28:48 PST 2009 
 *
 */


package amazon.Report;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.*;
import com.amazonservices.mws.client.MwsUtl;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Get Report List  Samples
 */
public class GetReportListSample {

    /**
     * Just add a few required parameters, and try the service
     * Get Report List functionality
     */
    public static GetReportListResponse getReportList() {

        /************************************************************************
         * Access Key ID and Secret Access Key ID, obtained from:
         * http://aws.amazon.com
         ***********************************************************************/
        final String accessKeyId = "AKIAIS6BIWMZZ527WQTQ";
        final String secretAccessKey = "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";

        final String appName = "BuyBoxMonitor";
        final String appVersion = "1.0";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();

        /************************************************************************
         * Uncomment to set the appropriate MWS endpoint.
         ************************************************************************/
        // US
        // config.setServiceURL("https://mws.amazonservices.com/");
        // UK
        // config.setServiceURL("https://mws.amazonservices.co.uk/");
        // Germany
        // config.setServiceURL("https://mws.amazonservices.de/");
        // France
        // config.setServiceURL("https://mws.amazonservices.fr/");
        // Italy
        config.setServiceURL("https://mws-eu.amazonservices.com");
        // Japan
        // config.setServiceURL("https://mws.amazonservices.jp/");
        // China
        // config.setServiceURL("https://mws.amazonservices.com.cn/");
        // Canada
        // config.setServiceURL("https://mws.amazonservices.ca/");
        // India
        // config.setServiceURL("https://mws.amazonservices.in/");

        /************************************************************************
         * You can also try advanced configuration options. Available options are:
         *
         *  - Signature Version
         *  - Proxy Host and Proxy Port
         *  - User Agent String to be sent to Marketplace Web Launcher
         *
         ***********************************************************************/

        /************************************************************************
         * Instantiate Http Client Implementation of Marketplace Web Launcher
         ***********************************************************************/

        MarketplaceWebService service = new MarketplaceWebServiceClient(
                accessKeyId, secretAccessKey, appName, appVersion, config);

        /************************************************************************
         * Uncomment to try out Mock Launcher that simulates Marketplace Web Launcher
         * responses without calling Marketplace Web Launcher  service.
         *
         * Responses are loaded from local XML files. You can tweak XML files to
         * experiment with various outputs during development
         *
         * XML files available under com/amazonaws/mws/mock tree
         *
         ***********************************************************************/
        // MarketplaceWebService service = new MarketplaceWebServiceMock();

        /************************************************************************
         * Setup request parameters and uncomment invoke to try out 
         * sample for Get Report List 
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all 
         * Marketplace Web Launcher calls.
         ***********************************************************************/
        final String merchantId = "A1DKMVBC49AN5B";
        final String sellerDevAuthToken = "amzn.mws.1cac3970-d8c0-09a5-28d9-378147e30a61"; //"amzn.mws.32b08431-1b04-af39-9f2a-19a372446c14";

        GetReportListRequest request = new GetReportListRequest();
        request.setMerchant(merchantId);
        request.setMWSAuthToken(sellerDevAuthToken);

        /****************************************************************************************************************/
        //intervallo di date tra una spedizione ed un altra
        Calendar today = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        simpleDateFormat.applyPattern("yyyy");
        int yearToday = Integer.parseInt(simpleDateFormat.format(today.getTime()));
        simpleDateFormat.applyPattern("MM");
        int monthToday = Integer.parseInt(simpleDateFormat.format(today.getTime()));
        simpleDateFormat.applyPattern("dd");
        int dayToday = Integer.parseInt(simpleDateFormat.format(today.getTime()));
        simpleDateFormat.applyPattern("HH");
        int hourToday = Integer.parseInt(simpleDateFormat.format(today.getTime()));
        simpleDateFormat.applyPattern("mm");
        int minuteToday = Integer.parseInt(simpleDateFormat.format(today.getTime()));
        simpleDateFormat.applyPattern("ss");
        int secondToday = Integer.parseInt(simpleDateFormat.format(today.getTime()));

        XMLGregorianCalendar lastUpdatedBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
        lastUpdatedBefore.setYear(yearToday);
        lastUpdatedBefore.setMonth(monthToday);
        lastUpdatedBefore.setDay(dayToday);
        lastUpdatedBefore.setTime(hourToday, minuteToday, secondToday);
        request.setAvailableToDate(lastUpdatedBefore);
        // sottraggo 1 ora data attuale

        today.add(Calendar.MINUTE, -2);

        simpleDateFormat.applyPattern("yyyy");
        int year_One_Hour_Before = Integer.parseInt(simpleDateFormat.format(today.getTime()));
        simpleDateFormat.applyPattern("MM");
        int month_One_Hour_Before = Integer.parseInt(simpleDateFormat.format(today.getTime()));
        simpleDateFormat.applyPattern("dd");
        int day_One_Hour_Before = Integer.parseInt(simpleDateFormat.format(today.getTime()));
        simpleDateFormat.applyPattern("HH");
        int hour_One_Hour_Before = Integer.parseInt(simpleDateFormat.format(today.getTime()));
        simpleDateFormat.applyPattern("mm");
        int minute_One_Hour_Before = Integer.parseInt(simpleDateFormat.format(today.getTime()));

        XMLGregorianCalendar lastUpdatedAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
        lastUpdatedAfter.setYear(year_One_Hour_Before);
        lastUpdatedAfter.setMonth(month_One_Hour_Before);
        lastUpdatedAfter.setDay(day_One_Hour_Before);
        lastUpdatedAfter.setTime(hour_One_Hour_Before, minute_One_Hour_Before, 0);
        request.setAvailableFromDate(lastUpdatedAfter);


        /****************************************************************************************************************/


        // @TODO: set request parameters here

        return invokeGetReportList(service, request);

    }


    /**
     * Get Report List  request sample
     * returns a list of reports; by default the most recent ten reports,
     * regardless of their acknowledgement status
     *
     * @param service instance of MarketplaceWebService service
     * @param request Action to invoke
     */
    public static GetReportListResponse invokeGetReportList(MarketplaceWebService service, GetReportListRequest request) {
        String reportId = "";
        try {

            GetReportListResponse response = service.getReportList(request);


            System.out.println("GetReportList Action Response");
            System.out.println("=============================================================================");
            System.out.println();

            System.out.print("    GetReportListResponse");
            System.out.println();
            if (response.isSetGetReportListResult()) {
                System.out.print("        GetReportListResult");
                System.out.println();
                GetReportListResult getReportListResult = response.getGetReportListResult();
                if (getReportListResult.isSetNextToken()) {
                    System.out.print("            NextToken");
                    System.out.println();
                    System.out.print("                " + getReportListResult.getNextToken());
                    System.out.println();
                }
                if (getReportListResult.isSetHasNext()) {
                    System.out.print("            HasNext");
                    System.out.println();
                    System.out.print("                " + getReportListResult.isHasNext());
                    System.out.println();
                }
                List<ReportInfo> reportInfoListList = getReportListResult.getReportInfoList();
                for (ReportInfo reportInfoList : reportInfoListList) {
                    System.out.print("            ReportInfoList");
                    System.out.println();
                    if (reportInfoList.isSetReportId()) {
                        System.out.print("                ReportId");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.getReportId());
                        System.out.println();
                        reportId = reportInfoList.getReportRequestId();
                    }
                    if (reportInfoList.isSetReportType()) {
                        System.out.print("                ReportType");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.getReportType());
                        System.out.println();
                    }
                    if (reportInfoList.isSetReportRequestId()) {
                        System.out.print("                ReportRequestId");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.getReportRequestId());
                        System.out.println();
                    }
                    if (reportInfoList.isSetAvailableDate()) {
                        System.out.print("                AvailableDate");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.getAvailableDate());
                        System.out.println();
                    }
                    if (reportInfoList.isSetAcknowledged()) {
                        System.out.print("                Acknowledged");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.isAcknowledged());
                        System.out.println();
                    }
                    if (reportInfoList.isSetAcknowledgedDate()) {
                        System.out.print("                AcknowledgedDate");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.getAcknowledgedDate());
                        System.out.println();
                    }
                }
            }
            if (response.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata responseMetadata = response.getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + responseMetadata.getRequestId());
                    System.out.println();
                }
            }
            System.out.println();
            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();

            return response;
        } catch (MarketplaceWebServiceException ex) {

            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
        return null;
    }

}
