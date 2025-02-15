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
import com.amazonservices.mws.products.model.ASINListType;
import com.amazonservices.mws.products.model.GetLowestOfferListingsForASINRequest;
import com.amazonservices.mws.products.model.GetLowestOfferListingsForASINResponse;
import com.amazonservices.mws.products.model.ResponseHeaderMetadata;
import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample call for GetLowestOfferListingsForASIN.
 */
public class GetLowestOfferListingsForASINSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     * @return The response.
     */
    public static GetLowestOfferListingsForASINResponse invokeGetLowestOfferListingsForASIN(
            MarketplaceWebServiceProducts client,
            GetLowestOfferListingsForASINRequest request) {
        try {
            // Call the service.
            GetLowestOfferListingsForASINResponse response = client.getLowestOfferListingsForASIN(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
          /*  System.out.println("Response:");
            System.out.println("RequestId: "+rhmd.getRequestId());
            System.out.println("Timestamp: "+rhmd.getTimestamp());
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
     */
    public static BigDecimal getLowestOfferListing(Product p) {

        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();

        // Create a request.
        GetLowestOfferListingsForASINRequest request = new GetLowestOfferListingsForASINRequest();
        String sellerId = "A1DKMVBC49AN5B";
        request.setSellerId(sellerId);
        String mwsAuthToken = "amzn.mws.1cac3970-d8c0-09a5-28d9-378147e30a61";
        request.setMWSAuthToken(mwsAuthToken);


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

        List<String> asins = new ArrayList<>();
        asins.add(p.getAsin());
        ASINListType asinList = new ASINListType();
        asinList.setASIN(asins);
        request.setASINList(asinList);
        String itemCondition = "New";
        request.setItemCondition(itemCondition);
        Boolean excludeMe = true;
        request.setExcludeMe(excludeMe);

        // Make the call.
        GetLowestOfferListingsForASINResponse response = GetLowestOfferListingsForASINSample.invokeGetLowestOfferListingsForASIN(client, request);

        try {
            if (response.getGetLowestOfferListingsForASINResult().size() != 0)
                if (response.getGetLowestOfferListingsForASINResult().get(0).getProduct().getLowestOfferListings().getLowestOfferListing().size() != 0)
                    if (response.getGetLowestOfferListingsForASINResult().get(0).getProduct().getLowestOfferListings().getLowestOfferListing().get(0).getPrice().getLandedPrice().getAmount() != null)
                        return response.getGetLowestOfferListingsForASINResult().get(0).getProduct().getLowestOfferListings().getLowestOfferListing().get(0).getPrice().getLandedPrice().getAmount();
        } catch (NullPointerException ignore) {
        }
        return BigDecimal.valueOf((0.00));

    }

}
