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
import com.amazonservices.mws.products.model.GetMatchingProductForIdRequest;
import com.amazonservices.mws.products.model.GetMatchingProductForIdResponse;
import com.amazonservices.mws.products.model.IdListType;
import com.amazonservices.mws.products.model.ResponseHeaderMetadata;
import model.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import utility.MarketId;

import java.util.ArrayList;
import java.util.List;


/**
 * Sample call for GetMatchingProductForId.
 */
public class GetMatchingProductForIdSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     * @return The response.
     */
    public static GetMatchingProductForIdResponse invokeGetMatchingProductForId(
            MarketplaceWebServiceProducts client,
            GetMatchingProductForIdRequest request) {
        try {
            // Call the service.
            GetMatchingProductForIdResponse response = client.getMatchingProductForId(request);
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
        /*    System.out.println("Service Exception:");
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
     *
     * @param productTemp
     */
    public static void getMatchingProductForId(Product productTemp) {

        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();

        // Create a request.
        GetMatchingProductForIdRequest request = new GetMatchingProductForIdRequest();
        String sellerId = "A1DKMVBC49AN5B";
        request.setSellerId(sellerId);
        String mwsAuthToken = "amzn.mws.1cac3970-d8c0-09a5-28d9-378147e30a61";
        request.setMWSAuthToken(mwsAuthToken);
        String marketplaceId = MarketId.valueOf(productTemp.getCountry()).getText();
        request.setMarketplaceId(marketplaceId);
        String idType = "ASIN";
        request.setIdType(idType);
        IdListType idList = new IdListType();
        List<String> asin = new ArrayList<>();
        asin.add(productTemp.getAsin());
        idList.setId(asin);
        request.setIdList(idList);

        // Make the call.
        GetMatchingProductForIdResponse response = GetMatchingProductForIdSample.invokeGetMatchingProductForId(client, request);
        try {

            Document doc = XmlParser.xmlFromString(response.getGetMatchingProductForIdResult().get(0).toXML());
            doc.normalize();
            Element getMatchingProductForIdResponse = doc.getDocumentElement();
            Element products = (Element) getMatchingProductForIdResponse.getElementsByTagName("Products").item(0);
            if (products != null && products.getElementsByTagName("Product") != null && products.getElementsByTagName("Product").item(0) != null) {
                Element product = (Element) products.getElementsByTagName("Product").item(0);
                Element getAttributeSets = (Element) product.getElementsByTagName("AttributeSets").item(0);
                Element itemAttributes = (Element) getAttributeSets.getElementsByTagName("ns2:ItemAttributes").item(0);
                Element smallImage = (Element) itemAttributes.getElementsByTagName("ns2:SmallImage").item(0);
                String img_url = smallImage.getElementsByTagName("ns2:URL").item(0).getTextContent();
                img_url = img_url.replace("._SL75_", "");
                productTemp.setImg_url(img_url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

