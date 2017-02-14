package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;

import javax.inject.Inject;

import rx.Observable;


public class SaveCustomerDetailsUseCase extends UseCase {
    private final RepositoryProvider repositoryProvider;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String billingAddress;
    private String billingCity;
    private String billingPostalCode;
    private String shippingAddress;
    private String shippingCity;
    private String shippingPostalCode;
    private Long userId;
    private String billingCountry;
    private String billingProvince;
    private String shippingCountry;
    private String shippingProvince;

    @Inject
    public SaveCustomerDetailsUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        super(threadExecutor, postExecutionThread);
        this.repositoryProvider = repositoryProvider;
    }

    public void setArguments(Long userId,
                             String firstName,
                             String lastName,
                             String email,
                             String phoneNumber,
                             String billingAddress,
                             String billingCity,
                             String billingPostalCode,
                             String shippingAddress,
                             String shippingCity,
                             String shippingPostalCode,
                             String billingCountry,
                             String billingProvince,
                             String shippingCountry,
                             String shippingProvince) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingPostalCode = billingPostalCode;
        this.shippingAddress = shippingAddress;
        this.shippingCity = shippingCity;
        this.shippingPostalCode = shippingPostalCode;
        this.billingCountry = billingCountry;
        this.billingProvince = billingProvince;
        this.shippingCountry = shippingCountry;
        this.shippingProvince = shippingProvince;
    }

    public void setArguments(Long userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setArguments(Long userId, String phoneNumber) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.checkForExistInformation();
    }

    private Observable checkForExistInformation() {
        UserModel userModel = this.repositoryProvider.getCustomerUserModel(userId);

        return userModel == null ? this.repositoryProvider.saveCustomerDetails(userId, firstName, lastName,
                email, phoneNumber, billingAddress, billingCity, billingPostalCode,
                shippingAddress, shippingCity, shippingPostalCode, billingCountry,
                billingProvince, shippingCountry, shippingProvince)
                :
                this.repositoryProvider.saveCustomerDetails(userId,
                this.getData(firstName, userModel.getFirstName()),
                this.getData(lastName, userModel.getLastName()),
                this.getData(email, userModel.getUserEmail()),
                this.getData(phoneNumber, userModel.getPhoneNumber()),
                this.getData(billingAddress, userModel.getBillingAddress()),
                this.getData(billingCity, userModel.getBillingCity()),
                this.getData(billingPostalCode, userModel.getBillingPostal()),
                this.getData(shippingAddress, userModel.getShippingAddress()),
                this.getData(shippingCity, userModel.getShippingCity()),
                this.getData(shippingPostalCode, userModel.getShippingPostal()),
                this.getData(billingCountry, userModel.getBillingCountry()),
                this.getData(billingProvince, userModel.getBillingProvince()),
                this.getData(shippingCountry, userModel.getShippingCountry()),
                this.getData(shippingProvince, userModel.getShippingProvince()));
    }

    private String getData(String currentData, String savedData) {
        String s = savedData;
        if (currentData != null && !currentData.equals("")) {
            s = currentData;
        }
        return s;
    }
}
