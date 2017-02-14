package net.iquesoft.project.iQueCommerce.presentation.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.model.ShopModel;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;

import javax.inject.Inject;


public class BaseActivityPresenter {

    private final ShopModel shopModel;
    private final UserModel userModel;

    @Inject
    public BaseActivityPresenter(ShopModel shopModel, UserModel userModel) {
        this.shopModel = shopModel;
        this.userModel = userModel;
    }

    public void showAboutDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle(this.shopModel.getName())
                .setView(this.getAboutMessage(context))
                .setPositiveButton(R.string.OK, (dialogInterface, i) -> dialogInterface.dismiss());
        builder.show();

    }

    private TextView getAboutMessage(Context context) {
        TextView message = new TextView(context);
        String s = "Contact email: andrewkasilov@gmail.com" + "\n"
                + this.shopModel.getUrl() + "\n"
                + this.shopModel.getDescription() + "\n"
                + "Shop address: " + this.shopModel.getCity() + ", "
                + this.shopModel.getCountry() + "\n";
        final SpannableString spannableString = new SpannableString(s);
        Linkify.addLinks(spannableString, Linkify.ALL);
        message.setText(spannableString);
        message.setMovementMethod(LinkMovementMethod.getInstance());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(8, 8, 8, 8);
        message.setLayoutParams(params);
        return message;
    }

    public void shareApp(Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getShareMessage());
        sendIntent.setType("text/uri");
        context.startActivity(sendIntent);
        // TODO: 29-Sep-16 check if there exist an app which can open intent 
    }

    private SpannableString getShareMessage() {
        String s = "I would like to share with you this wonderful online-shop! Check it out here " + this.shopModel.getUrl() + "."
                + " Try android application as well https://drive.google.com/folderview?id=0Bys_kMpV2ZV3UEVnZ05DOFB1TXc&usp=sharing";
        final SpannableString spannableString = new SpannableString(s);
        Linkify.addLinks(spannableString, Linkify.ALL);
        return spannableString;
    }

    public UserModel getCurrentUser() {
        return this.userModel;
    }
}
