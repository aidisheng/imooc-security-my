package com.hommin.web.mock;/**
 * Created by Hommin on 2018/3/11.
 */

import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * @author Hommin
 * @ClassName: MockServer
 * @Description: WireMock服务
 * @data 2018年03月11日 下午10:19
 */
public class MockServer {
    public static void main(String[] args) {
        WireMock.configureFor(8062);
        WireMock.removeAllMappings();

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/order1")).willReturn(WireMock.aResponse().withBody("{\"id\":1}").withStatus(200)));
    }
}
