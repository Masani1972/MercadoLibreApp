package com.mercadolibreapp.di.module;

import com.mercadolibreapp.di.ActivityScope;
import com.mercadolibreapp.ui.searchproducts.adapter.RecyclerViewAdapter;
import com.mercadolibreapp.ui.searchproducts.SearchActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {SearchActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getProductList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(SearchActivity searchActivity) {
        return searchActivity;
    }
}