package com.mercadolibreapp.di.component;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.mercadolibreapp.di.ActivityContext;
import com.mercadolibreapp.di.ActivityScope;
import com.mercadolibreapp.di.module.AdapterModule;
import com.mercadolibreapp.di.module.FragmentDetailProductModule;
import com.mercadolibreapp.di.module.SearchActivityMvpModule;
import com.mercadolibreapp.ui.detailProduct.DetailProductFragment;
import com.mercadolibreapp.ui.searchproducts.SearchActivity;

import dagger.Component;
import dagger.android.AndroidInjectionModule;


@ActivityScope
@Component(modules = {AdapterModule.class, SearchActivityMvpModule.class,FragmentDetailProductModule.class}, dependencies = ApplicationComponent.class)
public interface SearchActivityComponent  {

    @ActivityContext
    Context getContext();

    void injectSearchActivity(SearchActivity searchActivity);
}
