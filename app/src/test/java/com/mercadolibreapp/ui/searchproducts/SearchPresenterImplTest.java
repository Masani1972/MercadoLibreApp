package com.mercadolibreapp.ui.searchproducts;


import com.mercadolibreapp.data.network.ApiService;
import com.mercadolibreapp.utils.TypeError;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterImplTest {
    @Mock
    private ApiService apiService;

    @Mock
    SearchActivityContract.View mView;

    private MockWebServer mMockWebServer;

    @InjectMocks
    private SearchPresenterImpl searchPresenter;

    @Mock
    private SearchActivity view;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        searchPresenter = new SearchPresenterImpl(apiService,view);
        mMockWebServer = new MockWebServer();
        mMockWebServer.start();
    }

    @After
    public void tearDown() throws Exception {
        mMockWebServer.shutdown();
    }

    @Test
    public void searchProduct_valueEmpty() {
       /* MockResponse response = new MockResponse();
        response.setResponseCode(404);*/

        searchPresenter.searchProduct("");
        verify(view).showError(TypeError.ERROR_VALIDATION_DATA,"");
    }

    @Test
    public void searchProduct_valuenull() {
       /* MockResponse response = new MockResponse();
        response.setResponseCode(404);*/

        searchPresenter.searchProduct(null);
        verify(view).showError(TypeError.ERROR_VALIDATION_DATA,"");
    }
}