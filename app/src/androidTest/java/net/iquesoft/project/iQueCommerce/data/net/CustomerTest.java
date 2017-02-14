package net.iquesoft.project.iQueCommerce.data.net;

import com.shopify.buy.model.Address;
import com.shopify.buy.model.Customer;

import net.iquesoft.project.iQueCommerce.tools.ConstantsTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class CustomerTest extends Customer {

    @Override
    public Long getId() {
        return ConstantsTest.CUSTOMER_ID;
    }

    @Override
    public String getEmail() {
        return ConstantsTest.EMAIL;
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public boolean acceptsMarketing() {
        return super.acceptsMarketing();
    }

    @Override
    public Date getCreatedAtDate() {
        return super.getCreatedAtDate();
    }

    @Override
    public Date getUpdatedAtDate() {
        return super.getUpdatedAtDate();
    }

    @Override
    public String getFirstName() {
        return ConstantsTest.FIRST_NAME;
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return ConstantsTest.LAST_NAME;
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public long getOrdersCount() {
        return ConstantsTest.CUSTOMER_ORDERS_COUNT;
    }

    @Override
    public String getState() {
        return super.getState();
    }

    @Override
    public String getTotalSpent() {
        return ConstantsTest.CUSTOMER_TOTAL_SPENT;
    }

    @Override
    public String getNote() {
        return super.getNote();
    }

    @Override
    public void setNote(String note) {
        super.setNote(note);
    }

    @Override
    public boolean isVerifiedEmail() {
        return super.isVerifiedEmail();
    }

    @Override
    public String getMultipassIdenfier() {
        return super.getMultipassIdenfier();
    }

    @Override
    public boolean isTaxExempt() {
        return super.isTaxExempt();
    }

    @Override
    public void setTaxExempt(boolean taxExempt) {
        super.setTaxExempt(taxExempt);
    }

    @Override
    public Set<String> getTags() {
        return super.getTags();
    }

    @Override
    public void setTags(Set<String> tags) {
        super.setTags(tags);
    }

    @Override
    public Long getLastOrderId() {
        return super.getLastOrderId();
    }

    @Override
    public String getLastOrderName() {
        return super.getLastOrderName();
    }

    @Override
    public List<Address> getAddresses() {
        return Collections.emptyList();
    }

    @Override
    public void setAddresses(List<Address> addresses) {
        super.setAddresses(addresses);
    }

    @Override
    public Address getDefaultAddress() {
        return super.getDefaultAddress();
    }

    @Override
    public void setAcceptsMarketing(boolean acceptsMarketing) {
        super.setAcceptsMarketing(acceptsMarketing);
    }

    @Override
    public void setMultipassIdentifier(String multipassIdentifier) {
        super.setMultipassIdentifier(multipassIdentifier);
    }
}
