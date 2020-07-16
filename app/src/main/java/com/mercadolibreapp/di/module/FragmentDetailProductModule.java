package com.mercadolibreapp.di.module;

import com.mercadolibreapp.di.ActivityScope;
import com.mercadolibreapp.ui.detailProduct.DetailProductFragment;
import com.mercadolibreapp.ui.searchproducts.SearchActivityContract;

import dagger.Module;
import dagger.Provides;

@Module(includes = {SearchActivityContextModule.class})
public class FragmentDetailProductModule {

    private DetailProductFragment detailProductFragment;

    public FragmentDetailProductModule(DetailProductFragment detailProductFragment){
        this.detailProductFragment = detailProductFragment;
    }

    @Provides
    @ActivityScope
    public DetailProductFragment provideFragment(){
        return detailProductFragment;
    }


}
