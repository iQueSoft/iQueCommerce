package net.iquesoft.project.iQueCommerce.presentation;

import net.iquesoft.project.iQueCommerce.presentation.di.components.ApplicationTestComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.DaggerApplicationTestComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ApplicationModuleTest;

public class AndroidApplicationTest extends AndroidApplication {

    @Override
    protected ApplicationTestComponent builtComponent() {
        return DaggerApplicationTestComponent.builder()
                .applicationModuleTest(new ApplicationModuleTest(this))
                .build();
    }
}