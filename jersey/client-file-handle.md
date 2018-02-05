### upload file
```java
    public void uploadFileTest() {
        String testFile = getClass().getResource("filepath").getPath();
        Client client = ClientBuilder
                        .newBuilder()
                        .register(MultiPartFeature.class)
                        .build();

        FileDataBodyPart fileData = new FileDataBodyPart(
                "file",
                new File(testFile),
                MediaType.APPLICATION_OCTET_STREAM_TYPE);

        MultiPart multiPart = new MultiPart();
        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        multiPart.bodyPart(fileData);

        Response resp = client
                .target("http://<upload api>")
                .request()
                .post(Entity.entity(multiPart, multiPart.getMediaType()));

        assertEquals("status:204", 204, resp.getStatus());
    }
```
### download file
```java
    @Test
    public void downloadFileTest() {
        Response resp = target("http://<download api>")
                .request(MediaType.APPLICATION_OCTET_STREAM)
                .get();

        assertEquals("status:200", 200, resp.getStatus());

        String disposition = resp.getHeaderString("Content-Disposition");
        String fileName = disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1");

        byte[] fileBytes = resp.readEntity(byte[].class);
        try {
            FileUtils.writeByteArrayToFile(new File("<local path for saving file>" + fileName), fileBytes);
        } catch (IOException e) {
            fail("save file");
        }
    }
```
