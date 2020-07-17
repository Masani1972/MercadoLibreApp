package com.mercadolibreapp.di.component;

import android.content.Context;

import com.mercadolibreapp.common.MyApplication;
import com.mercadolibreapp.data.network.ApiService;
import com.mercadolibreapp.di.module.RetrofitModule;
import com.mercadolibreapp.di.ApplicationContext;
import com.mercadolibreapp.di.scope.ApplicationScope;
import com.mercadolibreapp.di.module.ContextModule;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    ApiService getApiService();


    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication application);
}
