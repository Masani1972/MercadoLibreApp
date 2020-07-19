package com.mercadolibreapp.common;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import com.mercadolibreapp.di.component.ApplicationComponent;
import com.mercadolibreapp.di.component.DaggerApplicationComponent;
import com.mercadolibreapp.di.module.ContextModule;


public class MyApplication extends Application {
    ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public static MyApplication get(Fragment fragment){
        return (MyApplication) fragment.getActivity().getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


}
