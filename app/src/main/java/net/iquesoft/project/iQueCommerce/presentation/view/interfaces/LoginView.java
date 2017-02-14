package net.iquesoft.project.iQueCommerce.presentation.view.interfaces;

public interface LoginView extends LoadDataView {

    void clearTextField();

    void showEmailError(String errorMessage);

    void showPasswordError(String errorMessage);

}
