package com.mercadolibreapp.di.module;

 import com.mercadolibreapp.di.scope.ActivityScope;
import com.mercadolibreapp.ui.search_products.SearchActivityContract;

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
