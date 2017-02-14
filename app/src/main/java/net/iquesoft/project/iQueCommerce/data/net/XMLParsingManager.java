package net.iquesoft.project.iQueCommerce.data.net;

import android.util.Xml;

import net.iquesoft.project.iQueCommerce.data.entity.CategoriesEntity;
import net.iquesoft.project.iQueCommerce.data.entity.ProductsEntity;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.utils.Constants;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class XMLParsingManager {

    private final String ns = "";

    @Inject
    public XMLParsingManager() {
    }


    public List<CategoriesEntity> getListOfCategories() throws IOException, XmlPullParserException {
        return parse(downloadUrl(Constants.CATEGORIES_URL));
    }


    private List<CategoriesEntity> parse(InputStream inputStream) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            inputStream.close();
        }
    }


    private List<CategoriesEntity> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<CategoriesEntity> entries = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "yml_catalog");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("shop")) {
                entries = readShop(parser);
            } else {
                skip(parser);
            }
        }
        return entries;
    }


    private List<CategoriesEntity> readShop(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<CategoriesEntity> entries = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "shop");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("categories")) {
                entries = readCategories(parser);
            } else {
                skip(parser);
            }
        }
        return entries;
    }


    private List<CategoriesEntity> readCategories(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<CategoriesEntity> entries = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "categories");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("category")) {
                entries.add(readCategory(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }


    private CategoriesEntity readCategory(XmlPullParser parser) throws IOException, XmlPullParserException {
        CategoriesEntity categoriesEntity = new CategoriesEntity();

        parser.require(XmlPullParser.START_TAG, ns, "category");
        categoriesEntity.setCategoryId(parser.getAttributeValue(0));
        categoriesEntity.setCategoryTitle(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, "category");
        return categoriesEntity;
    }


    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }


    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }

    public List<ProductsEntity> getListOfProducts(int categoryId) throws IOException, XmlPullParserException {
        return parseProductsList(downloadUrl(Constants.CATEGORIES_URL), categoryId);

    }

    private List<ProductsEntity> parseProductsList(InputStream inputStream, int categoryId) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            return readProductsFeed(parser, categoryId);
        } finally {
            inputStream.close();
        }
    }

    private List<ProductsEntity> readProductsFeed(XmlPullParser parser, int categoryId) throws XmlPullParserException, IOException {
        List<ProductsEntity> entries = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "yml_catalog");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("shop")) {
                entries = readProductsShop(parser, categoryId);
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private List<ProductsEntity> readProductsShop(XmlPullParser parser, int categoryId) throws IOException, XmlPullParserException {
        List<ProductsEntity> entries = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "shop");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("offers")) {
                entries = readOffers(parser, categoryId);
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private List<ProductsEntity> readOffers(XmlPullParser parser, int categoryId) throws IOException, XmlPullParserException {
        List<ProductsEntity> entries = new ArrayList<>();
        ProductsEntity productsEntity = new ProductsEntity();
        parser.require(XmlPullParser.START_TAG, ns, "offers");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("offer")) {
                productsEntity = readOffer(parser);
                if (productsEntity != null && productsEntity.getCategoryID() == categoryId) {
                    entries.add(productsEntity);
                }
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private ProductsEntity readOffer(XmlPullParser parser) throws IOException, XmlPullParserException {
        ProductsEntity productsEntity = new ProductsEntity();
        parser.require(XmlPullParser.START_TAG, ns, "offer");
        productsEntity.setItemId(Integer.parseInt(parser.getAttributeValue(0)));
        productsEntity.setAvailable(Boolean.parseBoolean(parser.getAttributeValue(1)));
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "name":
                    productsEntity.setTitle(readName(parser));
                    break;
                case "price":
                    productsEntity.setPrice(readPrice(parser));
                    break;
                case "currencyId":
                    productsEntity.setCurrencyID(readCurrencyID(parser));
                    break;
                case "categoryId":
                    productsEntity.setCategoryID(readCategoryId(parser));
                    break;
                case "url":
                    productsEntity.setShopUrl(readShopUrl(parser));
                    break;
                case "picture":
                    productsEntity.setPictureUrl(readPictureUrl(parser));
                    break;
                case "description":
                    productsEntity.setDescription(readDescription(parser));
                    break;
                default:
                    skip(parser);
                    break;
            }
        }
        return productsEntity;
    }

    private String readDescription(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "description");
        String s = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "description");
        return s;
    }

    private String readPictureUrl(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "picture");
        String s = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "picture");
        return s;
    }

    private int readCategoryId(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "categoryId");
        int i = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, "categoryId");
        return i;
    }

    private String readShopUrl(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "url");
        String s = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "url");
        return s;
    }

    private String readCurrencyID(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "currencyId");
        String s = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "currencyId");
        return s;
    }

    private float readPrice(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "price");
        float v = Float.parseFloat(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, "price");
        return v;
    }

    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String s = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return s;
    }
}