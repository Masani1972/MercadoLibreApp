package com.mercadolibreapp.di.component;

import android.content.Context;

import com.mercadolibreapp.common.MyApplication;
import com.mercadolibreapp.data.network.ApiService;
import com.mercadolibreapp.data.network.RetrofitModule;
import com.mercadolibreapp.di.ApplicationContext;
import com.mercadolibreapp.di.ApplicationScope;
import com.mercadolibreapp.di.module.ContextModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    ApiService getApiService();


    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication application);
}
