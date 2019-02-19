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
import com.amazonservices.mws.products.model.GetMyPriceForSKURequest;
import com.amazonservices.mws.products.model.GetMyPriceForSKUResponse;
import com.amazonservices.mws.products.model.ResponseHeaderMetadata;
import com.amazonservices.mws.products.model.SellerSKUListType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Sample call for GetMyPriceForSKU.
 */
public class GetMyPriceForSKUSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     * @return The response.
     */
    public static BigDecimal invokeGetMyPriceForSKU(
            MarketplaceWebServiceProducts client,
            GetMyPriceForSKURequest request) {
        try {
            // Call the service.
            GetMyPriceForSKUResponse response = client.getMyPriceForSKU(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.

            try {
                if (response.getGetMyPriceForSKUResult().size() != 0 && response.getGetMyPriceForSKUResult().get(0).getProduct().getOffers().getOffer().size() != 0)
                    return response.getGetMyPriceForSKUResult().get(0).getProduct().getOffers().getOffer().get(0).getBuyingPrice().getLandedPrice().getAmount();
            } catch (NullPointerException e) {
                System.out.println(e.getCause() + "\n\n" + e.getMessage());

            }

            return null;

        } catch (MarketplaceWebServiceProductsException ex) {
            // Exception properties are important for diagnostics.
          /*  System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if (rhmd != null) {
                System.out.println("RequestId: " + rhmd.getRequestId());
                System.out.println("Timestamp: " + rhmd.getTimestamp());
            }
            System.out.println("Message: " + ex.getMessage());
            System.out.println("StatusCode: " + ex.getStatusCode());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("ErrorType: " + ex.getErrorType());*/
            throw ex;
        }
    }

    /**
     * Command line entry point.
     */
    public static BigDecimal getMyPriceForSKUSample(String sku, String marketplace) {

        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();

        // Create a request.
        GetMyPriceForSKURequest request = new GetMyPriceForSKURequest();
        String sellerId = "A1DKMVBC49AN5B";
        request.setSellerId(sellerId);
        String mwsAuthToken = "amzn.mws.1cac3970-d8c0-09a5-28d9-378147e30a61";
        request.setMWSAuthToken(mwsAuthToken);
        String marketplaceId = "";

        switch (marketplace) {
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
        List<String> skuList = new ArrayList<>();
        skuList.add(sku);
        SellerSKUListType sellerSKUList = new SellerSKUListType();
        sellerSKUList.setSellerSKU(skuList);
        request.setSellerSKUList(sellerSKUList);

        // Make the call.
        return GetMyPriceForSKUSample.invokeGetMyPriceForSKU(client, request);

    }

}
