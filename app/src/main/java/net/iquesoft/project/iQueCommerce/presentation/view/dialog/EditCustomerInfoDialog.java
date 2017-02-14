package net.iquesoft.project.iQueCommerce.presentation.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import net.iquesoft.project.iQueCommerce.R;


public class EditCustomerInfoDialog extends DialogFragment {

    private static final String DIALOG_TITLE = "Edit customer information";
    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String LAST_NAME = "LAST_NAME";
    private static final String EMAIL = "EMAIL";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";
    TextInputEditText etCustomerFirstName;
    TextInputEditText etCustomerLastName;
    TextInputEditText etEmail;
    TextInputEditText etPhoneNumber;
    TextInputLayout tilCustomerFirstName;
    TextInputLayout tilCustomerLastName;
    TextInputLayout tilEmail;
    TextInputLayout tilPhoneNumber;
    private OnEditCustomerInfoListener onEditCustomerInfoListener = (OnEditCustomerInfoListener) getTargetFragment();

    public static EditCustomerInfoDialog newInstance(String firstName, String lastName, String email, String phoneNumber) {
        EditCustomerInfoDialog frag = new EditCustomerInfoDialog();
        Bundle args = new Bundle();
        args.putString(FIRST_NAME, firstName);
        args.putString(LAST_NAME, lastName);
        args.putString(EMAIL, email);
        args.putString(PHONE_NUMBER, phoneNumber);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        try {
            onEditCustomerInfoListener = (OnEditCustomerInfoListener) childFragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(childFragment.toString()
                    + " must implement OnEditAddressListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getContext(), R.layout.dialog_edit_customer_info, null);

        initViews(view);
        setHints();

        builder.setView(view)
                .setTitle(DIALOG_TITLE)
                .setPositiveButton("Done", (dialogInterface, i) -> this.collectData())
                .setNegativeButton("Cancel", ((dialogInterface, i) -> dismiss()))
                .create();
        return builder.create();
    }

    private void initViews(View view) {
        etCustomerFirstName = (TextInputEditText) view.findViewById(R.id.etCustomerFirstName);
        etCustomerLastName = (TextInputEditText) view.findViewById(R.id.etCustomerLastName);
        etEmail = (TextInputEditText) view.findViewById(R.id.etEmail);
        etPhoneNumber = (TextInputEditText) view.findViewById(R.id.etPhoneNumber);

        tilCustomerFirstName = (TextInputLayout) view.findViewById(R.id.tilCustomerFirstName);
        tilCustomerLastName = (TextInputLayout) view.findViewById(R.id.tilCustomerLastName);
        tilEmail = (TextInputLayout) view.findViewById(R.id.tilEmail);
        tilPhoneNumber = (TextInputLayout) view.findViewById(R.id.tilPhoneNumber);
    }

    private void setHints() {
        if (getArguments().getString(FIRST_NAME) != null) {
            tilCustomerFirstName.setHint(getArguments().getString(FIRST_NAME));
        } else {
            tilCustomerFirstName.setHint("");
        }
        if (getArguments().getString(LAST_NAME) != null) {
            tilCustomerLastName.setHint(getArguments().getString(LAST_NAME));
        } else {
            tilCustomerLastName.setHint("");
        }
        if (getArguments().getString(EMAIL) != null) {
            tilEmail.setHint(getArguments().getString(EMAIL));
        } else {
            tilEmail.setHint("");
        }
        if (getArguments().getString(PHONE_NUMBER) != null) {
            tilPhoneNumber.setHint(getArguments().getString(PHONE_NUMBER));
        } else {
            tilPhoneNumber.setHint("");
        }
    }

    private void collectData() {

        String firstName;
        String lastName;
        String email;
        String phoneNumber;
        if (!etCustomerFirstName.getText().toString().isEmpty()) {
            firstName = etCustomerFirstName.getText().toString();
        } else {
            firstName = tilCustomerFirstName.getHint().toString();
        }
        if (!etCustomerLastName.getText().toString().isEmpty()) {
            lastName = etCustomerLastName.getText().toString();
        } else {
            lastName = tilCustomerLastName.getHint().toString();
        }
        if (!etEmail.getText().toString().isEmpty() && etEmail.getText().toString().contains("@")) {
            email = etEmail.getText().toString();
        } else {
            email = tilEmail.getHint().toString();
        }
        if (!etPhoneNumber.getText().toString().isEmpty()) {
            phoneNumber = etPhoneNumber.getText().toString();
        } else {
            phoneNumber = tilPhoneNumber.getHint().toString();
        }

        this.onEditCustomerInfoListener.OnCustomerPhoneNumberChanged(phoneNumber);
    }

    public interface OnEditCustomerInfoListener {
        void OnCustomerPhoneNumberChanged(String phoneNumber);
    }


}