package com.mercadolibreapp.data.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

public class ApiServiceTest {
    private MockWebServer mMockWebServer;


    @Before
    public void setUp() throws Exception {
        mMockWebServer = new MockWebServer();
        mMockWebServer.start();

    }

    @After
    public void tearDown() throws Exception {
        mMockWebServer.shutdown();

    }

    @Test
    public void getProductForSearch() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mMockWebServer.url("/"))
                .build();


    }
}