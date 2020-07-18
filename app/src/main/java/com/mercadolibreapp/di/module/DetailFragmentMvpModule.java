package com.mercadolibreapp.di.module;

import com.mercadolibreapp.di.scope.ActivityScope;
import com.mercadolibreapp.ui.detail_products.DetailProductContract;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailFragmentMvpModule {

    private DetailProductContract.View mView;


    public DetailFragmentMvpModule(DetailProductContract.View mView){
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    DetailProductContract.View provideView(){
        return mView;
    }}