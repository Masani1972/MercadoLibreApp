package com.mercadolibreapp.di.module;

 import com.mercadolibreapp.di.ActivityScope;
import com.mercadolibreapp.ui.searchproducts.SearchActivityContract;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityMvpModule {

    private SearchActivityContract.View mView;

    public SearchActivityMvpModule(SearchActivityContract.View mView){
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    SearchActivityContract.View provideView(){
        return mView;
    }


}
