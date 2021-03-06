package com.mercadolibreapp.ui.search_products;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.mercadolibreapp.R;
import com.mercadolibreapp.R2;
import com.mercadolibreapp.common.MyApplication;
import com.mercadolibreapp.data.network.pojo.ProductModel;
import com.mercadolibreapp.di.ActivityContext;
import com.mercadolibreapp.di.ApplicationContext;
import com.mercadolibreapp.di.component.ApplicationComponent;
import com.mercadolibreapp.di.component.DaggerSearchActivityComponent;
import com.mercadolibreapp.di.component.SearchActivityComponent;
import com.mercadolibreapp.di.module.ResultProductsFragmentModule;
import com.mercadolibreapp.di.module.SearchActivityContextModule;
import com.mercadolibreapp.di.module.SearchActivityMvpModule;
import com.mercadolibreapp.ui.result_products.ResultProductsFragment;
import com.mercadolibreapp.utils.TypeAlert;
import com.mercadolibreapp.utils.UtilAlertClassBuilder;

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

    @BindView(R2.id.frProducts)
    FrameLayout frameLayout;

    SearchActivityComponent searchActivityComponent;

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
                .resultProductsFragmentModule(new ResultProductsFragmentModule(new ResultProductsFragment()))
                .applicationComponent(applicationComponent)
                .build();

        searchActivityComponent.injectSearchActivity(this);
    }

   @OnEditorAction(R.id.edtxtProductSearch)
    protected boolean searchProduct(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            searchPresenter.searchProduct(productSearch.getText().toString());
            return true;
        }
        return false;
    }

    @Override
    public void showData(List<ProductModel> data) {
        frameLayout.setAlpha(1f);
        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable("list_products", (Serializable) data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frProducts);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        resultProductsFragment.setArguments(dataBundle);
        if (getSupportFragmentManager().getFragments().isEmpty())
            fragmentTransaction.replace(R.id.frProducts, resultProductsFragment);
        else {
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void showError(TypeAlert typeAlert, int stringResource,String message) {
        String messageAlert = getResources().getString(stringResource) + " " +  message;
        AlertDialog alertDialog = new UtilAlertClassBuilder(this)
                .setTitle(getResources().getString(R.string.tittleAlert))
                .setMessage(messageAlert)
                .setType(typeAlert).build();

        alertDialog.show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }



}