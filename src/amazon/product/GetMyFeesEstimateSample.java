/*******************************************************************************
 * Copyright 2009-2017 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Products
 * API Version: 2011-10-01
 * Library Version: 2017-03-22
 * Generated: Wed Mar 22 23:24:32 UTC 2017
 */
package amazon.product;


import buisness.XmlParser;
import com.amazonservices.mws.products.MarketplaceWebServiceProducts;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsException;
import com.amazonservices.mws.products.model.*;
import model.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import utility.MarketId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample call for GetMyFeesEstimate.
 */
public class GetMyFeesEstimateSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     * @return The response.
     */
    public static GetMyFeesEstimateResponse invokeGetMyFeesEstimate(
            MarketplaceWebServiceProducts client,
            GetMyFeesEstimateRequest request) {
        try {
            // Call the service.
            GetMyFeesEstimateResponse response = client.getMyFeesEstimate(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: " + rhmd.getRequestId());
            System.out.println("Timestamp: " + rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            return response;
        } catch (MarketplaceWebServiceProductsException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if (rhmd != null) {
                System.out.println("RequestId: " + rhmd.getRequestId());
                System.out.println("Timestamp: " + rhmd.getTimestamp());
            }
            System.out.println("Message: " + ex.getMessage());
            System.out.println("StatusCode: " + ex.getStatusCode());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("ErrorType: " + ex.getErrorType());
            throw ex;
        }
    }

    /**
     * Command line entry point.
     *
     * @param p
     */
    public static void getMyFeesEstimate(Product p) {

        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();

        // Create a request.
        GetMyFeesEstimateRequest request = new GetMyFeesEstimateRequest();
        String sellerId = "A1DKMVBC49AN5B";
        request.setSellerId(sellerId);
        String mwsAuthToken = "amzn.mws.1cac3970-d8c0-09a5-28d9-378147e30a61";
        request.setMWSAuthToken(mwsAuthToken);
        FeesEstimateRequest feesEstimateRequests = new FeesEstimateRequest();
        FeesEstimateRequestList feesEstimateRequestList = new FeesEstimateRequestList();
        List<FeesEstimateRequest> feesList = new ArrayList<>();
        feesEstimateRequests.setMarketplaceId(MarketId.valueOf(p.getCountry()).getText());
        feesEstimateRequests.setIdentifier("FBA_InventoryRequestFees");
        feesEstimateRequests.setIdType("ASIN");
        feesEstimateRequests.setIsAmazonFulfilled(true);
        PriceToEstimateFees priceToEstimateFees = new PriceToEstimateFees();
        MoneyType moneyType = new MoneyType();
        moneyType.setAmount(p.getPrice());
        priceToEstimateFees.setListingPrice(moneyType);
        moneyType.setAmount(BigDecimal.valueOf(0.00));
        priceToEstimateFees.setShipping(moneyType);
        feesEstimateRequests.setPriceToEstimateFees(priceToEstimateFees);
        Points points = new Points();
        points.setPointsNumber(0);
        priceToEstimateFees.setPoints(points);
        feesEstimateRequests.setIdValue(p.getAsin());

        feesList.add(feesEstimateRequests);
        feesEstimateRequestList.setFeesEstimateRequest(feesList);
        request.setFeesEstimateRequestList(feesEstimateRequestList);

        // Make the call.
        GetMyFeesEstimateResponse response = GetMyFeesEstimateSample.invokeGetMyFeesEstimate(client, request);

        try {

            Document doc = XmlParser.xmlFromString(response.getGetMyFeesEstimateResult().getFeesEstimateResultList().getFeesEstimateResult().get(0).toXML());
            doc.normalize();
            Element getMyFeesEstimateResponse = doc.getDocumentElement();
            Element getMyFeesEstimateResult = (Element) getMyFeesEstimateResponse.getElementsByTagName("GetMyFeesEstimateResult").item(0);
            Element feesEstimateResultList = (Element) getMyFeesEstimateResult.getElementsByTagName("FeesEstimateResultList").item(0);
            Element feesEstimateResult = (Element) feesEstimateResultList.getElementsByTagName("FeesEstimateResult").item(0);
            Element feesEstimate = (Element) feesEstimateResult.getElementsByTagName("FeesEstimate").item(0);
            Element totalEstimate = (Element) feesEstimate.getElementsByTagName("TotalFeesEstimate").item(0);
            Element totalAmount = (Element) totalEstimate.getElementsByTagName("Amount").item(0);
            String amount = totalAmount.getTextContent();
            Element feeDetail = (Element) feesEstimate.getElementsByTagName("FeeDetailList").item(0);
            String fba_amount2 = "";
            for (int i = 0; i < feeDetail.getElementsByTagName("FeeDetail").getLength(); i++) {
                if (feeDetail.getElementsByTagName("FeeType").item(0).getTextContent().equals("FBAFees")) {
                    Element feeAmount = (Element) feeDetail.getElementsByTagName("FeeAmount").item(0);
                    Element fba_Amount = (Element) feeAmount.getElementsByTagName("Amount").item(0);
                    fba_amount2 = fba_Amount.getTextContent();
                }

            }
            p.setTotal_Fee(new BigDecimal(amount));
            p.setFba_Fee(new BigDecimal(fba_amount2));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
