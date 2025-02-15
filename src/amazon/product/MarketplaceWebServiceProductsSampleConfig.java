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

import com.amazonservices.mws.products.MWSEndpoint;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsAsyncClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsConfig;

/**
 * Configuration for MarketplaceWebServiceProducts samples.
 */
public class MarketplaceWebServiceProductsSampleConfig {

    /**
     * Developer AWS access key.
     */
    private static final String accessKey = "AKIAIS6BIWMZZ527WQTQ";

    /**
     * Developer AWS secret key.
     */
    private static final String secretKey = "nABZxjpL4iSrDxrg3hXeKBNq179XWcnSKZuh5DAe";

    /**
     * The client application name.
     */
    private static final String appName = "FBA_inventory";

    /**
     * The client application version.
     */
    private static final String appVersion = "1.1";


    /**
     * The endpoint for region service and version.
     * ex: serviceURL = MWSEndpoint.NA_PROD.toString();
     */
    private static final String serviceURL = MWSEndpoint.IT_PROD.toString();

    /**
     * The client, lazy initialized. Async client is also a sync client.
     */
    private static MarketplaceWebServiceProductsAsyncClient client = null;

    /**
     * Get a client connection ready to use.
     *
     * @return A ready to use client connection.
     */
    public static MarketplaceWebServiceProductsClient getClient() {
        return getAsyncClient();
    }

    /**
     * Get an async client connection ready to use.
     *
     * @return A ready to use client connection.
     */
    public static synchronized MarketplaceWebServiceProductsAsyncClient getAsyncClient() {
        if (client == null) {
            MarketplaceWebServiceProductsConfig config = new MarketplaceWebServiceProductsConfig();
            config.setServiceURL(serviceURL);
            // Set other client connection configurations here.
            client = new MarketplaceWebServiceProductsAsyncClient(accessKey, secretKey,
                    appName, appVersion, config, null);
        }
        return client;
    }

}
