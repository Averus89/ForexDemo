package pl.dexbytes.forexdemo;

import pl.dexbytes.forexdemo.constants.ServerConfig;
import pl.dexbytes.forexdemo.net.BaseUrlChangingInterceptor;
import pl.dexbytes.forexdemo.util.AssetReaderUtil;
import pl.dexbytes.forexdemo.vars.StaticVariables;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

public class RestStub {
    public static void stubQuotesEndpointWithError(int error){
        BaseUrlChangingInterceptor.get().setInterceptor(ServerConfig.LOCAL_HOST + StaticVariables.Urls.QUOTES);
        stubFor(get(urlPathMatching(StaticVariables.Urls.QUOTES)).willReturn(aResponse().withStatus(error)));
    }

    public static void stubQuotesWithBody(){
        BaseUrlChangingInterceptor.get().setInterceptor(ServerConfig.LOCAL_HOST + StaticVariables.Urls.QUOTES);
        String jsonBody = AssetReaderUtil.asset("getQuotes.json");
        stubFor(get(urlPathMatching(StaticVariables.Urls.QUOTES)).willReturn(aResponse().withStatus(200).withBody(jsonBody)));
    }
}
