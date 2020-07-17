package com.mercadolibreapp.di.component;

import android.content.Context;

import com.mercadolibreapp.di.ActivityContext;
import com.mercadolibreapp.di.scope.ActivityScope;
import com.mercadolibreapp.di.module.DetailProductFragmentModule;
import com.mercadolibreapp.di.module.ResultProductsFragmentModule;
import com.mercadolibreapp.di.module.SearchActivityMvpModule;
import com.mercadolibreapp.ui.searchproducts.SearchActivity;

import dagger.Component;


@ActivityScope
@Component(modules = {SearchActivityMvpModule.class, DetailProductFragmentModule.class, ResultProductsFragmentModule.class}, dependencies = ApplicationComponent.class)
public interface SearchActivityComponent  {

    @ActivityContext
    Context getContext();

    void injectSearchActivity(SearchActivity searchActivity);
}
