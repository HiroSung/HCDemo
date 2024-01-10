package com.example.hcdemo;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientTest {
    
    public void testHttpClient() {

        String url = "http://localhost:8080/test";
        String resultContent = null;
        HttpGet httpGet = new HttpGet(url);

        try ( CloseableHttpClient httpclient = HttpClients.createDefault()){

            try ( CloseableHttpResponse httpResponse = httpclient.execute(httpGet)){
                
                log.error("{}", httpResponse.getVersion());
                log.error("{}", httpResponse.getCode());
                log.error("{}", httpResponse.getReasonPhrase());
                
                HttpEntity httpEntity = httpResponse.getEntity();
                resultContent = EntityUtils.toString(httpEntity);

            } catch ( IOException | ParseExcep | Exception e) {
                e.printStackTrace();
            }

            log.error("{}", resultContent);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
    
    
    @Test
    public void testHttpClientFluent() {
        String url = "http://httpbin.org/get";

        String result = null;
        try {
            Response response = Request.get(url).execute();
            result = response.returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.error("{}",  result);
    }

    public static InetSocketAddress resolveRemoteAddress(InetSocketAddress address) {

        Map<String, String> addressMap = new HashMap<>();

        addressMap.put("httpbin.org", "52.206.94.89");

        String hostname = address.getHostName();
        int port = address.getPort();

        log.error("hostname: {}, port {}", hostname, port);
       
        return new InetSocketAddress(Optional.ofNullable(addressMap.get(hostname)).orElse(hostname), port);
    }
}
