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


import buisness.PriceManager;
import com.amazonservices.mws.products.MarketplaceWebServiceProducts;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsException;
import com.amazonservices.mws.products.model.GetLowestPricedOffersForSKURequest;
import com.amazonservices.mws.products.model.GetLowestPricedOffersForSKUResponse;
import com.amazonservices.mws.products.model.GetLowestPricedOffersForSKUResult;
import com.amazonservices.mws.products.model.ResponseHeaderMetadata;
import model.Product;
import utility.Is_winner;

import java.math.BigDecimal;


/**
 * Sample call for GetLowestPricedOffersForSKU.
 */
public class GetLowestPricedOffersForSKUSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     * @return The response.
     */
    public static GetLowestPricedOffersForSKUResult invokeGetLowestPricedOffersForSKU(
            MarketplaceWebServiceProducts client,
            GetLowestPricedOffersForSKURequest request) {
        try {
            // Call the service.
            GetLowestPricedOffersForSKUResponse response = client.getLowestPricedOffersForSKU(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
         /*   System.out.println("Response:");
            System.out.println("RequestId: " + rhmd.getRequestId());
            System.out.println("Timestamp: " + rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);*/

            if (response.getResponseHeaderMetadata().getQuotaRemaining() < 1) {
                PriceManager.setQuota_over(false);
            } else PriceManager.setQuota_over(true);
            return response.getGetLowestPricedOffersForSKUResult();


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
            //  throw ex;
        }
        return null;
    }

    /**
     * Command line entry point.
     */
    public static void getLowestPricedOffersForSKUSample(Product p) {

        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();

        // Create a request.
        GetLowestPricedOffersForSKURequest request = new GetLowestPricedOffersForSKURequest();
        String sellerId = "A1DKMVBC49AN5B";
        request.setSellerId(sellerId);
        String mwsAuthToken = "amzn.mws.1cac3970-d8c0-09a5-28d9-378147e30a61";
        request.setMWSAuthToken(mwsAuthToken);
        request.setSellerSKU(p.getSku());
        request.setItemCondition("New");

        String marketplaceId = "";

        switch (p.getCountry()) {
            case "IT":
                marketplaceId = "APJ6JRA9NG5V4";
                break;
            case "ES":
                marketplaceId = "A1RKKUPIHCS9HS";
                break;
            case "DE":
                marketplaceId = "A1PA6795UKMFR9";
                break;
            case "GB":
                marketplaceId = "A1F83G8C2ARO7P";
                break;
            case "FR":
                marketplaceId = "A13V1IB3VIYZZH";
                break;
        }

        request.setMarketplaceId(marketplaceId);

        // Make the call.
        GetLowestPricedOffersForSKUResult response = GetLowestPricedOffersForSKUSample.invokeGetLowestPricedOffersForSKU(client, request);

        try {
            if (response.getSummary().getBuyBoxPrices().getBuyBoxPrice().get(0).getLandedPrice().getAmount() != null && p.getBuyBox_Price().equals(BigDecimal.valueOf(0.00))) {
                p.setBuyBox_Price(response.getSummary().getBuyBoxPrices().getBuyBoxPrice().get(0).getLandedPrice().getAmount());
            }

            for (int i = 0; i < response.getOffers().getOffer().size(); i++) {
                if (response.getOffers().getOffer().get(i).getMyOffer() && response.getOffers().getOffer().get(i).getIsBuyBoxWinner() != null) {
                    if (response.getOffers().getOffer().get(i).getIsBuyBoxWinner()) {
                        p.setBuybox_winner(Is_winner.TRUE);
                    } else p.setBuybox_winner(Is_winner.FALSE);
                }
            }
        } catch (NullPointerException ignored) {
        }


    }
}


