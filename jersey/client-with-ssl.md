```java
    private Client simpleSSLClient() {
        ClientConfig config = new ClientConfig();
        return ClientBuilder.newBuilder()
                            .withConfig(config)
                            .hostnameVerifier(SSLClient.createHostNameVerifier())
                            .sslContext(SSLClient.createSSLContext())
                            .build();
    }
```

* SSL Context
```java
public class SSLClient {
    private static final Logger log = LoggerFactory.getLogger(SSLClient.class);

    private SSLClient(){};

    public static SSLContext createSSLContext() {
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLSv1.2");
            ctx.init(null,
                    new X509TrustManager[] { new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain,String authType) throws CertificateException {}
                        @Override
                        public void checkServerTrusted(X509Certificate[] chain,String authType) throws CertificateException {}
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
                    } }, new SecureRandom());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            log.error("Create SSLContext - " + e.getMessage());
            throw new RuntimeException("Create SSLContext");
        }

        return ctx;
    }

    public static HostnameVerifier createHostNameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {return true;}
        };
    }
}
```
