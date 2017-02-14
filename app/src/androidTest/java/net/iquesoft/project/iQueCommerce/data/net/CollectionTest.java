package net.iquesoft.project.iQueCommerce.data.net;

import com.shopify.buy.model.Collection;


class CollectionTest extends Collection {


    private final String imageUrl;

    CollectionTest(long id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    @Override
    public String getImageUrl() {
        return this.imageUrl;
    }

}
