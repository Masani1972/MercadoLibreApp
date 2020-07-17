package com.mercadolibreapp.di.module;

import com.mercadolibreapp.di.scope.ActivityScope;
import com.mercadolibreapp.ui.searchproducts.fragments.DetailProductFragment;

import dagger.Module;
import dagger.Provides;

@Module(includes = {SearchActivityContextModule.class})
public class DetailProductFragmentModule {

    private DetailProductFragment detailProductFragment;

    public DetailProductFragmentModule(DetailProductFragment detailProductFragment){
        this.detailProductFragment = detailProductFragment;
    }

    @Provides
    @ActivityScope
    public DetailProductFragment provideFragment(){
        return detailProductFragment;
    }


}
