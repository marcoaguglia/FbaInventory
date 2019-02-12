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

import java.util.Arrays;

/**
 * Request Report  Samples
 */
public class RequestReportSample {

    /**
     * Just add a few required parameters, and try the service
     * Request Report functionality
     */
    public static void requestReport() {

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
        // Europe
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
         * sample for Request Report 
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all 
         * Marketplace Web Launcher calls.
         ***********************************************************************/
        final String merchantId = "A1DKMVBC49AN5B";
        final String sellerDevAuthToken = "amzn.mws.1cac3970-d8c0-09a5-28d9-378147e30a61";
        // marketplaces from which data should be included in the report; look at the
        // API reference document on the MWS website to see which marketplaces are
        // included if you do not specify the list yourself
        final IdList marketplaces = new IdList(Arrays.asList(
                "APJ6JRA9NG5V4"
        ));

        RequestReportRequest request = new RequestReportRequest()
                .withMerchant(merchantId)
                .withMarketplaceIdList(marketplaces)
                .withReportType("_GET_AFN_INVENTORY_DATA_BY_COUNTRY_")
                .withReportOptions("ShowSalesChannel=true");
        request = request.withMWSAuthToken(sellerDevAuthToken);

        // demonstrates how to set the date range
        /************************************************************************************
         DatatypeFactory df = null;
         try {
         df = DatatypeFactory.newInstance();
         } catch (DatatypeConfigurationException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
         }
         XMLGregorianCalendar startDate = df
         .newXMLGregorianCalendar(new GregorianCalendar(2019, 1, 18,8,0,0));
         ******************************************************************************************/

        // @TODO: set additional request parameters here


        invokeRequestReport(service, request);

    }


    /**
     * Request Report  request sample
     * requests the generation of a report
     *
     * @param service instance of MarketplaceWebService service
     * @param request Action to invoke
     */
    public static void invokeRequestReport(MarketplaceWebService service, RequestReportRequest request) {

        try {

            RequestReportResponse response = service.requestReport(request);


            System.out.println("RequestReport Action Response");
            System.out.println("=============================================================================");
            System.out.println();

            System.out.print("    RequestReportResponse");
            System.out.println();
            if (response.isSetRequestReportResult()) {
                System.out.print("        RequestReportResult");
                System.out.println();
                RequestReportResult requestReportResult = response.getRequestReportResult();
                if (requestReportResult.isSetReportRequestInfo()) {
                    System.out.print("            ReportRequestInfo");
                    System.out.println();
                    ReportRequestInfo reportRequestInfo = requestReportResult.getReportRequestInfo();
                    if (reportRequestInfo.isSetReportRequestId()) {
                        System.out.print("                ReportRequestId");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportRequestId());
                        System.out.println();

                    }
                    if (reportRequestInfo.isSetReportType()) {
                        System.out.print("                ReportType");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportType());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetStartDate()) {
                        System.out.print("                StartDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getStartDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetEndDate()) {
                        System.out.print("                EndDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getEndDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetSubmittedDate()) {
                        System.out.print("                SubmittedDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getSubmittedDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetReportProcessingStatus()) {
                        System.out.print("                ReportProcessingStatus");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportProcessingStatus());
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


        } catch (MarketplaceWebServiceException ex) {

            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }

    }

}
