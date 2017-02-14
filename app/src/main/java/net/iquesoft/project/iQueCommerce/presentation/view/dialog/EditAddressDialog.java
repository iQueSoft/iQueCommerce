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


public class EditAddressDialog extends DialogFragment {

    private static final String ADDRESS = "ADDRESS";
    private static final String CITY = "CITY";
    private static final String POSTAL_CODE = "POSTAL_CODE";
    private OnEditAddressListener onEditAddressListener = (OnEditAddressListener) getTargetFragment();
    private TextInputLayout tilPostalCode;
    private TextInputLayout tilCity;
    private TextInputLayout tilAddress;
    private TextInputEditText etPostalCode;
    private TextInputEditText etCity;
    private TextInputEditText etAddress;

    public static EditAddressDialog newInstance(String address, String city, String postal_code) {
        EditAddressDialog frag = new EditAddressDialog();
        Bundle args = new Bundle();
        args.putString(ADDRESS, address);
        args.putString(CITY, city);
        args.putString(POSTAL_CODE, postal_code);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        try {
            onEditAddressListener = (OnEditAddressListener) childFragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(childFragment.toString()
                    + " must implement OnEditAddressListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getContext(), R.layout.dialog_edit_customer_address, null);

        this.initViews(view);
        this.setHints();
        String title = "Edit " + getTag() + " address";

        builder.setView(view)
                .setTitle(title)
                .setPositiveButton("Done", (dialogInterface, i) -> this.collectData())
                .setNegativeButton("Cancel", ((dialogInterface, i) -> dismiss()))
                .create();
        return builder.create();
    }


    private void initViews(View view) {
        etAddress = (TextInputEditText) view.findViewById(R.id.etAddress);
        etCity = (TextInputEditText) view.findViewById(R.id.et_city);
        etPostalCode = (TextInputEditText) view.findViewById(R.id.et_postal_code);

        tilAddress = (TextInputLayout) view.findViewById(R.id.tilAddress);
        tilCity = (TextInputLayout) view.findViewById(R.id.til_city);
        tilPostalCode = (TextInputLayout) view.findViewById(R.id.til_postal_code);
    }

    private void setHints() {
        if (getArguments().getString(ADDRESS) != null) {
            tilAddress.setHint(getArguments().getString(ADDRESS));
        } else {
            tilAddress.setHint("");
        }
        if (getArguments().getString(CITY) != null) {
            tilCity.setHint(getArguments().getString(CITY));
        } else {
            tilCity.setHint("");
        }
        if (getArguments().getString(POSTAL_CODE) != null) {
            tilPostalCode.setHint(getArguments().getString(POSTAL_CODE));
        } else {
            tilPostalCode.setHint("");
        }
    }

    private void collectData() {
        String address = "";
        String city = "";
        String postalCode = "";
        if (!etAddress.getText().toString().isEmpty()) {
            address = etAddress.getText().toString();
        }
        if (!etCity.getText().toString().isEmpty()) {
            city = etCity.getText().toString();
        }
        if (!etPostalCode.getText().toString().isEmpty()) {
            postalCode = etPostalCode.getText().toString();
        }

        this.onEditAddressListener.OnCustomerAddressChanged(getTag(), address, city, postalCode);
    }

    public interface OnEditAddressListener {
        void OnCustomerAddressChanged(String tag, String address, String city, String postalCode);
    }


}