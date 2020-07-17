package com.mercadolibreapp.ui.searchproducts;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mercadolibreapp.R;
import com.mercadolibreapp.R2;
import com.mercadolibreapp.common.MyApplication;
import com.mercadolibreapp.data.network.pojo.ProductModel;
import com.mercadolibreapp.di.ActivityContext;
import com.mercadolibreapp.di.ApplicationContext;
import com.mercadolibreapp.di.component.ApplicationComponent;
import com.mercadolibreapp.di.component.DaggerSearchActivityComponent;
import com.mercadolibreapp.di.component.SearchActivityComponent;
import com.mercadolibreapp.di.module.DetailProductFragmentModule;
import com.mercadolibreapp.di.module.ResultProductsFragmentModule;
import com.mercadolibreapp.di.module.SearchActivityContextModule;
import com.mercadolibreapp.di.module.SearchActivityMvpModule;
import com.mercadolibreapp.ui.searchproducts.fragments.DetailProductFragment;
import com.mercadolibreapp.ui.searchproducts.fragments.ResultProductsFragment;
import com.mercadolibreapp.utils.TypeError;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;


public class SearchActivity extends FragmentActivity implements SearchActivityContract.View{
    @BindView(R2.id.edtxtProductSearch)
    EditText productSearch;

    @BindView(R2.id.progressBar)
    ProgressBar progressBar;

    SearchActivityComponent searchActivityComponent;

    @Inject
    DetailProductFragment detailProductFragment;

    @Inject
    ResultProductsFragment resultProductsFragment;

    @Inject
    @ApplicationContext
    public Context context;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    SearchPresenterImpl searchPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        searchActivityComponent = DaggerSearchActivityComponent.builder()
                .searchActivityContextModule(new SearchActivityContextModule(this))
                .searchActivityMvpModule(new SearchActivityMvpModule(this))
                .detailProductFragmentModule(new DetailProductFragmentModule(new DetailProductFragment()))
                .resultProductsFragmentModule(new ResultProductsFragmentModule(new ResultProductsFragment()))
                .applicationComponent(applicationComponent)
                .build();

        searchActivityComponent.injectSearchActivity(this);
    }

   @OnEditorAction(R.id.edtxtProductSearch)
    protected boolean searchProduct(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            searchPresenter.searchProduct(productSearch.getText().toString());
           // searchPresenter.searchProduct("Samsung Galaxy S8");
            //searchPresenter.searchProduct("Motorola G6");
            return true;
        }
        return false;
    }

    @Override
    public void showData(List<ProductModel> data) {
        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable("list_products", (Serializable) data);

        FragmentTransaction t = getSupportFragmentManager()
                .beginTransaction();
        resultProductsFragment.setArguments(dataBundle);
        t.replace(R.id.frProducts, resultProductsFragment);
        t.commit();
    }

    @Override
    public void showError(TypeError typeError,String statusMessage) {
        String message = typeError.name().equals(TypeError.ERROR_DATA_SERVICE)?getResources().getString(R.string.errorProductSearch): getResources().getString(R.string.errorDataEmpty);
        Toast.makeText(context,  message + statusMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showComplete() {

    }


}