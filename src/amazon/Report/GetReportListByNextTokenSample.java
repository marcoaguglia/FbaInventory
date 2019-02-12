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

import java.util.List;

/**
 * Get Report List By Next Token  Samples
 */
public class GetReportListByNextTokenSample {
    String nextToken;

    public GetReportListByNextTokenSample(String nextToken) {
        nextToken = nextToken;
    }

    /**
     * Just add a few required parameters, and try the service
     * Get Report List By Next Token functionality
     */
    public static GetReportListByNextTokenResponse getReportListByNextToken(String nextToken) {


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
         * sample for Get Report List By Next Token 
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all 
         * Marketplace Web Launcher calls.
         ***********************************************************************/

        final String merchantId = "A1DKMVBC49AN5B";
        final String sellerDevAuthToken = "amzn.mws.1cac3970-d8c0-09a5-28d9-378147e30a61";

        GetReportListByNextTokenRequest request = new GetReportListByNextTokenRequest();
        request.setMerchant(merchantId);
        request.setMWSAuthToken(sellerDevAuthToken);
        request.setNextToken(nextToken);

        // @TODO: set request parameters here

        return invokeGetReportListByNextToken(service, request);

    }


    /**
     * Get Report List By Next Token  request sample
     * retrieve the next batch of list items and if there are more items to retrieve
     *
     * @param service instance of MarketplaceWebService service
     * @param request Action to invoke
     */
    public static GetReportListByNextTokenResponse invokeGetReportListByNextToken(MarketplaceWebService service, GetReportListByNextTokenRequest request) {
        try {

            GetReportListByNextTokenResponse response = service.getReportListByNextToken(request);


            System.out.println("GetReportListByNextToken Action Response");
            System.out.println("=============================================================================");
            System.out.println();

            System.out.print("    GetReportListByNextTokenResponse");
            System.out.println();
            if (response.isSetGetReportListByNextTokenResult()) {
                System.out.print("        GetReportListByNextTokenResult");
                System.out.println();
                GetReportListByNextTokenResult getReportListByNextTokenResult = response.getGetReportListByNextTokenResult();
                if (getReportListByNextTokenResult.isSetNextToken()) {
                    System.out.print("            NextToken");
                    System.out.println();
                    System.out.print("                " + getReportListByNextTokenResult.getNextToken());
                    System.out.println();
                }
                if (getReportListByNextTokenResult.isSetHasNext()) {
                    System.out.print("            HasNext");
                    System.out.println();
                    System.out.print("                " + getReportListByNextTokenResult.isHasNext());
                    System.out.println();
                }
                List<ReportInfo> reportInfoList = getReportListByNextTokenResult.getReportInfoList();
                for (ReportInfo reportInfo : reportInfoList) {
                    System.out.print("            ReportInfo");
                    System.out.println();
                    if (reportInfo.isSetReportId()) {
                        System.out.print("                ReportId");
                        System.out.println();
                        System.out.print("                    " + reportInfo.getReportId());
                        System.out.println();
                    }
                    if (reportInfo.isSetReportType()) {
                        System.out.print("                ReportType");
                        System.out.println();
                        System.out.print("                    " + reportInfo.getReportType());
                        System.out.println();
                    }
                    if (reportInfo.isSetReportRequestId()) {
                        System.out.print("                ReportRequestId");
                        System.out.println();
                        System.out.print("                    " + reportInfo.getReportRequestId());
                        System.out.println();
                    }
                    if (reportInfo.isSetAvailableDate()) {
                        System.out.print("                AvailableDate");
                        System.out.println();
                        System.out.print("                    " + reportInfo.getAvailableDate());
                        System.out.println();
                    }
                    if (reportInfo.isSetAcknowledged()) {
                        System.out.print("                Acknowledged");
                        System.out.println();
                        System.out.print("                    " + reportInfo.isAcknowledged());
                        System.out.println();
                    }
                    if (reportInfo.isSetAcknowledgedDate()) {
                        System.out.print("                AcknowledgedDate");
                        System.out.println();
                        System.out.print("                    " + reportInfo.getAcknowledgedDate());
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
