package net.iquesoft.project.iQueCommerce.data.net;

import com.shopify.buy.model.Shop;

import net.iquesoft.project.iQueCommerce.tools.ConstantsTest;


public class ShopTest extends Shop {

    public ShopTest() {
        this.name = ConstantsTest.SHOP_NAME;
        this.moneyFormat = "$";
    }

}
