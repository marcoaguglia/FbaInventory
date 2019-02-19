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


import com.amazonservices.mws.products.MarketplaceWebServiceProducts;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsException;
import com.amazonservices.mws.products.model.*;
import model.Product;
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
          /*  System.out.println("Response:");
            System.out.println("RequestId: " + rhmd.getRequestId());
            System.out.println("Timestamp: " + rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);*/
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
        if (p.getCountry().equals("GB"))
            moneyType.setCurrencyCode("GBP");
        else
            moneyType.setCurrencyCode("EUR");
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
            FeesEstimate estimate = response.getGetMyFeesEstimateResult().getFeesEstimateResultList().getFeesEstimateResult().get(0).getFeesEstimate();
            BigDecimal total_amount = estimate.getTotalFeesEstimate().getAmount();
            BigDecimal fba_amount = null;
            List<FeeDetail> detail_list = estimate.getFeeDetailList().getFeeDetail();
            for (int i = 0; i < detail_list.size(); i++) {
                if (detail_list.get(i).getFeeType().equals("FBAFees")) {
                    fba_amount = detail_list.get(i).getFinalFee().getAmount();
                }
            }

            p.setTotal_Fee(total_amount);
            p.setFba_Fee(fba_amount);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
