package net.iquesoft.project.iQueCommerce.presentation.view.interfaces;

import java.util.List;

public interface PaymentView extends LoadDataView {

    void showCountries(List<String> countries);

    void showBillingProvinces(List<String> provinces);

    void showShippingProvinces(List<String> provinces);


}
