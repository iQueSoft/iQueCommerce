package net.iquesoft.project.iQueCommerce.presentation.di.tools;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import net.iquesoft.project.iQueCommerce.presentation.AndroidApplicationTest;

public class MockTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(
            ClassLoader cl, String className, Context context)
            throws InstantiationException,
            IllegalAccessException,
            ClassNotFoundException {
        return super.newApplication(
                cl, AndroidApplicationTest.class.getName(), context);
    }
}