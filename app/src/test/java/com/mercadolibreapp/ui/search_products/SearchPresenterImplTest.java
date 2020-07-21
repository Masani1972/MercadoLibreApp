package com.mercadolibreapp.ui.search_products;


import com.mercadolibreapp.data.network.ApiService;
import com.mercadolibreapp.data.network.pojo.ProductModel;
import com.mercadolibreapp.data.network.pojo.SearchProductsResponse;
import com.mercadolibreapp.utils.TypeAlert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.util.ReflectionHelpers;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.TestObserver;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterImplTest {
    @Mock
    private ApiService apiService;

    @InjectMocks
    private SearchPresenterImpl searchPresenter;

    @Mock
    private SearchActivity view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        searchPresenter = new SearchPresenterImpl(apiService, view);

    }

    @Test
    public void searchProduct_valueEmpty() {
        searchPresenter.searchProduct("");
        verify(view).showError(TypeAlert.TYPE_INFO, "");
    }

    @Test
    public void searchProduct_valuenull() {
        searchPresenter.searchProduct(null);
        verify(view).showError(TypeAlert.TYPE_INFO, "");
    }

    @Test
    public void searchProduct_data() {
        ProductModel productModel = new ProductModel();
        ReflectionHelpers.setField(productModel, "id", "12345");
        List<ProductModel> productModelsList = new ArrayList<>();
        productModelsList.add(productModel);
        SearchProductsResponse expectedResults = new SearchProductsResponse();
        ReflectionHelpers.setField(expectedResults, "products", productModelsList);

        when(apiService.getProductForSearch(anyString())).thenReturn(Observable.just(expectedResults));

        TestObserver<SearchProductsResponse> testObserver = new TestObserver<>();
        apiService.getProductForSearch(anyString()).subscribeWith(testObserver);
        testObserver.assertComplete();
        testObserver.assertResult(expectedResults);
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
                e.onNext(new SearchProductsResponse());
                e.onError(new Throwable("Generic exception"));
            }
        });
    }

}