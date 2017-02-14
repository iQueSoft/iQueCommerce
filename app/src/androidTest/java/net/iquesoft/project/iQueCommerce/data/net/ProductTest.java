package net.iquesoft.project.iQueCommerce.data.net;

import com.shopify.buy.model.Image;
import com.shopify.buy.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductTest extends Product {

    private final String minimumPrice;
    private final String image;

    public ProductTest(long id, String title, String price, String bodyHtml, String notebookImage) {
        this.productId = id;
        this.title = title;
        this.minimumPrice = price;
        this.bodyHtml = bodyHtml;
        this.image = notebookImage;
    }

    @Override
    public List<Image> getImages() {
        List<Image> list = new ArrayList<>();
        list.add(this.getImage(image));
        return list;
    }

    private ImageTest getImage(String image) {
        return new ImageTest(image);
    }

    @Override
    public String getMinimumPrice() {
        return minimumPrice;
    }

    private class ImageTest extends Image {
        public ImageTest(String image) {
            this.src = image;
        }
    }
}

