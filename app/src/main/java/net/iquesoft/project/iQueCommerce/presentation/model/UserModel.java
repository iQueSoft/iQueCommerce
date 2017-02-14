package net.iquesoft.project.iQueCommerce.presentation.model;

import android.net.Uri;

import com.shopify.buy.model.Address;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Class which stores all variables, such as user ID, name, etc. Here are only setters and getters.
 */


public class UserModel extends RealmObject {
    @Ignore
    private Uri userPhotoUrl;
    @PrimaryKey
    private Long userId;
    private String userEmail = "";
    private String lastName = "";
    private String firstName = "";
    @Ignore
    private List<Address> userAddresses;
    @Ignore
    private Address userDefaultAddress;
    private long userOrdersCount;
    private String userTotalSpent;
    private String phoneNumber = "";
    private String billingAddress = "";
    private String billingCity = "";
    private String billingPostal = "";
    private String shippingAddress = "";
    private String shippingCity = "";
    private String shippingPostal = "";
    private String billingCountry = "";
    private String shippingCountry = "";
    private String billingProvince = "";
    private String shippingProvince = "";


    public UserModel() {
    }

    @Override
    public String toString() {
        return "Name: " + firstName + lastName + "\n" +
                "email: " + userEmail + "\n" +
                "ID: " + userId + "\n" +
                "phone number: " + phoneNumber + "\n" +
                "shipping address" + shippingAddress + " " + shippingCity + " " + shippingProvince + " " + shippingPostal + "\n" +
                "billing address" + billingAddress + " " + billingCity + " " + billingProvince + " " + billingPostal + "\n";
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Uri getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(Uri userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<Address> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public Address getUserDefaultAddress() {
        return userDefaultAddress;
    }

    public void setUserDefaultAddress(Address userDefaultAddress) {
        this.userDefaultAddress = userDefaultAddress;
    }

    public long getUserOrdersCount() {
        return userOrdersCount;
    }

    public void setUserOrdersCount(long userOrdersCount) {
        this.userOrdersCount = userOrdersCount;
    }

    public String getUserTotalSpent() {
        return userTotalSpent;
    }

    public void setUserTotalSpent(String userTotalSpent) {
        this.userTotalSpent = userTotalSpent;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPostal() {
        return billingPostal;
    }

    public void setBillingPostal(String billingPostal) {
        this.billingPostal = billingPostal;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostal() {
        return shippingPostal;
    }

    public void setShippingPostal(String shippingPostal) {
        this.shippingPostal = shippingPostal;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getBillingProvince() {
        return billingProvince;
    }

    public void setBillingProvince(String billingProvince) {
        this.billingProvince = billingProvince;
    }

    public String getShippingProvince() {
        return shippingProvince;
    }

    public void setShippingProvince(String shippingProvince) {
        this.shippingProvince = shippingProvince;
    }
}
