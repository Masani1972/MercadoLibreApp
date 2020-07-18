package com.mercadolibreapp.di.component;


import com.mercadolibreapp.di.module.DetailFragmentMvpModule;
import com.mercadolibreapp.di.scope.ActivityScope;
import com.mercadolibreapp.ui.detail_products.DetailProductFragment;

import dagger.Component;

@ActivityScope
@Component(modules = {DetailFragmentMvpModule.class}, dependencies = ApplicationComponent.class)
public interface DetailFragmentComponent  {

    void injectDetailFragment(DetailProductFragment detailProductFragment);
}