package com.mercadolibreapp.di.module;

import com.mercadolibreapp.di.ActivityScope;
import com.mercadolibreapp.ui.searchproducts.fragments.DetailProductFragment;
import com.mercadolibreapp.ui.searchproducts.fragments.ResultProductsFragment;

import dagger.Module;
import dagger.Provides;

@Module(includes = {SearchActivityContextModule.class})
public class ResultProductsFragmentModule {

    private ResultProductsFragment resultProductsFragment;

    public ResultProductsFragmentModule(ResultProductsFragment resultProductsFragment){
        this.resultProductsFragment = resultProductsFragment;
    }

    @Provides
    @ActivityScope
    public ResultProductsFragment provideFragment(){
        return resultProductsFragment;
    }


}
