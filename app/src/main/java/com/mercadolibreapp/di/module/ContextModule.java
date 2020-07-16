package com.mercadolibreapp.di.module;

import android.content.Context;

import com.mercadolibreapp.di.ApplicationContext;
import com.mercadolibreapp.di.ApplicationScope;


import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context providesContext(){
        return context;
    }

}
