package net.iquesoft.project.iQueCommerce.presentation.model;

import io.realm.RealmObject;

public class WishProduct extends RealmObject {

    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
