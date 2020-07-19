package com.mercadolibreapp.ui.detail_products;

import com.mercadolibreapp.data.network.ApiService;
import com.mercadolibreapp.data.network.pojo.ProductInfo;
import com.mercadolibreapp.data.network.pojo.ProductModel;
import com.mercadolibreapp.data.network.pojo.SearchProductsResponse;
import com.mercadolibreapp.ui.search_products.SearchActivity;
import com.mercadolibreapp.ui.search_products.SearchPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.util.ReflectionHelpers;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class DetailPresenterImplTest {

    @Mock
    private ApiService apiService;

    @InjectMocks
    private DetailPresenterImpl detailPresenter;

    @Mock
    private DetailProductFragment view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        detailPresenter = new DetailPresenterImpl(apiService,view);
    }

    @Test
    public void searchProduct() {
        ProductInfo productInfo = new ProductInfo();
        ReflectionHelpers.setField(productInfo, "name", "Samsumg");

        when(apiService.getProductInfo(anyString())).thenReturn(Observable.just(productInfo));

        TestObserver<ProductInfo> testObserver = new TestObserver<>();
        apiService.getProductInfo(anyString()).subscribeWith(testObserver);
        testObserver.assertComplete();
        testObserver.assertResult(productInfo);
    }

    @Test
    public void searchProduct_data_Error() {
        Observable<SearchProductsResponse> obs = mockedObservableError();
        TestObserver<SearchProductsResponse> testObserver = TestObserver.create();
        obs.subscribe(testObserver);

        testObserver.assertError(Throwable.class);
    }


    private Observable<SearchProductsResponse> mockedObservableError() {
        return Observable.create(new ObservableOnSubscribe<SearchProductsResponse>() {
            @Override
            public void subscribe(ObservableEmitter<SearchProductsResponse> e) throws Exception {
                e.onNext( new SearchProductsResponse());
                e.onError(new Throwable("Generic exception"));
            }
        });
    }
}