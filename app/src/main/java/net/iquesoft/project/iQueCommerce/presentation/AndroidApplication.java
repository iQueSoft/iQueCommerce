/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.iquesoft.project.iQueCommerce.presentation;

import android.support.multidex.MultiDexApplication;

import net.iquesoft.project.iQueCommerce.presentation.di.components.ApplicationComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.DaggerApplicationComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ApplicationModule;

/**
 * Android Main Application
 */
public class AndroidApplication extends MultiDexApplication {

    private static ApplicationComponent appComponent;

    public static ApplicationComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = this.builtComponent();
    }

    protected ApplicationComponent builtComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
