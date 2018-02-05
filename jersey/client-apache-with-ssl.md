  ```java
    public Client apacheSSLClient() {
        ClientConfig config = getApacheClientConfig();

        return ClientBuilder.newBuilder()
                            .withConfig(config)
                            .build();

    }

    private ClientConfig getApacheClientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.property(ClientProperties.READ_TIMEOUT, 2000);
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 500);


        SSLConnectionSocketFactory sslConnectionSocketFactory = 
                new SSLConnectionSocketFactory(SSLClient.createSSLContext(), 
                        NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslConnectionSocketFactory)
                .build();

        PoolingHttpClientConnectionManager  connectionManager = 
                new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);

        clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager);
        clientConfig.property(ClientProperties.REQUEST_ENTITY_PROCESSING, RequestEntityProcessing.BUFFERED);
        clientConfig.connectorProvider(new ApacheConnectorProvider());

        return clientConfig;
    }
```
