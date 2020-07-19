package com.mercadolibreapp.data.network;

import com.mercadolibreapp.data.network.pojo.ProductInfo;
import com.mercadolibreapp.data.network.pojo.SearchProductsResponse;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

public class ApiServiceTest {
    private MockWebServer mMockWebServer;
    private Retrofit retrofit;



    @Before
    public void setUp() throws Exception {
        mMockWebServer = new MockWebServer();
        mMockWebServer.start();
        retrofit = new Retrofit.Builder()
                .baseUrl(mMockWebServer.url("/"))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @After
    public void tearDown() throws Exception {
        mMockWebServer.shutdown();

    }

    @Test
    public void getProductForSearch() throws InterruptedException {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(200);
        mockResponse.setBody(getResponse("searchProduct"));
        mMockWebServer.enqueue(mockResponse);

        ApiService ourApi = retrofit.create(ApiService.class);
        assertNotNull(ourApi);

        TestObserver<SearchProductsResponse> observable = ourApi.getProductForSearch(anyString()).test();
        assertThat(observable.values(), Matchers.<SearchProductsResponse>hasSize(1));
        SearchProductsResponse searchProductsResponse = observable.values().get(0);
        assertNotNull(searchProductsResponse);
        assertEquals(searchProductsResponse.products.get(0).name, "Moto G6 Plus 64 Gb Índigo Oscuro 4 Gb Ram");

    }


    @Test
    public void getProductForSearchError() throws InterruptedException {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(404);
        mockResponse.setBody(getResponse("searchProduct"));
        mMockWebServer.enqueue(mockResponse);

        ApiService ourApi = retrofit.create(ApiService.class);
        assertNotNull(ourApi);

        TestObserver<SearchProductsResponse> observable = ourApi.getProductForSearch(anyString()).test();
        assertThat(observable.values(), Matchers.<SearchProductsResponse>hasSize(0));

    }

    @Test
    public void getProductInfo() throws InterruptedException {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(200);
        mockResponse.setBody(getResponse("productInfo"));
        mMockWebServer.enqueue(mockResponse);

        ApiService ourApi = retrofit.create(ApiService.class);
        assertNotNull(ourApi);

        TestObserver<ProductInfo> observable = ourApi.getProductInfo(anyString()).test();
        assertThat(observable.values(), Matchers.<ProductInfo>hasSize(1));
        ProductInfo productInfo = observable.values().get(0);
        assertNotNull(productInfo);
        assertEquals(productInfo.name, "Moto G6 Plus 64 Gb Índigo Oscuro 4 Gb Ram");
    }

    @Test
    public void getProductInfoError() throws InterruptedException {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(404);
        mockResponse.setBody(getResponse("productInfo"));
        mMockWebServer.enqueue(mockResponse);

        ApiService ourApi = retrofit.create(ApiService.class);
        assertNotNull(ourApi);

        TestObserver<ProductInfo> observable = ourApi.getProductInfo(anyString()).test();
        assertThat(observable.values(), Matchers.<ProductInfo>hasSize(0));
    }


    private String getResponse(String api){
        switch (api){
            case "searchProduct":
                return "{\n" +
                        "  \"results\": [\n" +
                        "    {\n" +
                        "      \"id\": \"MLA861732979\",\n" +
                        "      \"title\": \"Moto G6 Plus 64 Gb Índigo Oscuro 4 Gb Ram\",\n" +
                        "      \"price\": 49999,\n" +
                        "      \"thumbnail\": \"http://mla-s2-p.mlstatic.com/909111-MLA31239994076_062019-I.jpg\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";
            case "productInfo":
                return "{\n" +
                        "  \"id\": \"MLA861732979\",\n" +
                        "  \"title\": \"Moto G6 Plus 64 Gb Índigo Oscuro 4 Gb Ram\",\n" +
                        "  \"pictures\": []\n" +
                      // "  \"descriptions\": [],\n" +
                        "}\n";
            default:
                return "";
        }
    }
}