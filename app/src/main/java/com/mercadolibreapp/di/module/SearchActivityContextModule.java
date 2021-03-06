package com.mercadolibreapp.di.module;

import android.content.Context;

import com.mercadolibreapp.di.ActivityContext;
import com.mercadolibreapp.di.scope.ActivityScope;
import com.mercadolibreapp.ui.search_products.SearchActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityContextModule {
    private SearchActivity searchActivity;
    public Context context;

    public SearchActivityContextModule(SearchActivity searchActivity){
        this.searchActivity = searchActivity;
        context = searchActivity;
    }

    @Provides
    @ActivityScope
    public SearchActivity providesSearchActivity(){
        return searchActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context providesContext(){
        return context;
    }
}
