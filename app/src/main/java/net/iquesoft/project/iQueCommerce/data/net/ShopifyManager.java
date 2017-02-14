package net.iquesoft.project.iQueCommerce.data.net;

import com.shopify.buy.dataprovider.BuyClient;
import com.shopify.buy.dataprovider.BuyClientBuilder;
import com.shopify.buy.dataprovider.BuyClientError;
import com.shopify.buy.dataprovider.Callback;
import com.shopify.buy.model.AccountCredentials;
import com.shopify.buy.model.Address;
import com.shopify.buy.model.Cart;
import com.shopify.buy.model.Checkout;
import com.shopify.buy.model.Collection;
import com.shopify.buy.model.CreditCard;
import com.shopify.buy.model.Customer;
import com.shopify.buy.model.CustomerToken;
import com.shopify.buy.model.GiftCard;
import com.shopify.buy.model.PaymentToken;
import com.shopify.buy.model.Product;
import com.shopify.buy.model.ShippingRate;
import com.shopify.buy.model.Shop;

import net.iquesoft.project.iQueCommerce.data.exception.NoCartException;
import net.iquesoft.project.iQueCommerce.data.executor.JobExecutor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import rx.Observable;
import rx.schedulers.Schedulers;


public class ShopifyManager {

    private static final String BUY_CLIENT_SHOP = "seedecommerce.myshopify.com";
    private static final String BUY_CLIENT_API_KEY = "1189ffdce3f32502bc4ced07f8772de7";
    private static final String BUY_CLIENT_APP_ID = "8";
    private static final String BUY_CLIENT_APP_NAME = "net.iquesoft.project.seedEcommerce";
    private static final int PRODUCT_PAGE_SIZE = 20;
    private static final int RETRY_MAX_COUNT = 3;
    private static final long RETRY_DELAY_MS = TimeUnit.MILLISECONDS.toMillis(200);
    private static final float RETRY_BACKOFF_MULTIPLIER = 1.5f;
    private static final long CONNECTION_TIMEOUT_MS = 30;
    private static final long READ_WRITE_TIMEOUT_MS = 60;
    private final JobExecutor jobExecutor;
    private CustomerToken customerToken = null;
    private BuyClient buyClient;
    private Cart cart;
    private Checkout checkout;
    private PaymentToken paymentToken;

    public ShopifyManager(JobExecutor jobExecutor) {
        this.jobExecutor = jobExecutor;
        initialize();
    }

    public void initialize() {
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        this.buyClient = new BuyClientBuilder()
                .shopDomain(BUY_CLIENT_SHOP)
                .apiKey(BUY_CLIENT_API_KEY)
                .appId(BUY_CLIENT_APP_ID)
                .applicationName(BUY_CLIENT_APP_NAME)
                .callbackScheduler(Schedulers.from(jobExecutor))
                .interceptors(logging)
                .productPageSize(PRODUCT_PAGE_SIZE)
                .build();
        this.cart = new Cart();

    }

    public Observable<Shop> getShop() {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.getShop(new Callback<Shop>() {
                @Override
                public void success(Shop response) {

                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public Observable<List<com.shopify.buy.model.Collection>> getCollections(int pageNumber) {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.getCollections(pageNumber, new Callback<List<com.shopify.buy.model.Collection>>() {
                @Override
                public void success(List<com.shopify.buy.model.Collection> response) {
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                    error.printStackTrace();
                }
            });
        });
    }


    public Observable<Collection> getCollection(String handle) {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.getCollectionByHandle(handle, new Callback<Collection>() {
                @Override
                public void success(Collection response) {
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public Observable<List<Product>> getProducts(int pageNumber) {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.getProducts(pageNumber, new Callback<List<Product>>() {
                @Override
                public void success(List<Product> response) {
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public Observable<List<Product>> getProducts(int pageNumber, long collectionId) {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.getProducts(pageNumber, collectionId, null, null, new Callback<List<Product>>() {
                @Override
                public void success(List<Product> response) {
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public Observable<List<Product>> getProducts(List<Long> ids) {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.getProducts(ids, new Callback<List<Product>>() {
                @Override
                public void success(List<Product> response) {
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public Observable<Product> getProduct(String handle) {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.getProductByHandle(handle, new Callback<Product>() {
                @Override
                public void success(Product response) {
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public void addProductToCard(Product product, int variant) {
        this.cart.addVariant(product.getVariants().get(variant));
    }


    public Observable createCheckout() {
        return Observable.create(subscriber -> {
            if (ShopifyManager.this.cart != null) {
                ShopifyManager.this.checkout = new Checkout(ShopifyManager.this.cart);
            } else {
                subscriber.onError(new NoCartException());
            }
            ShopifyManager.this.buyClient.createCheckout(ShopifyManager.this.checkout, new Callback<Checkout>() {
                @Override
                public void success(Checkout response) {
                    ShopifyManager.this.checkout = response;
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public Observable<Customer> logIn(String email, String password) {
        return Observable.create(subscriber -> {
            AccountCredentials accountCredentials = new AccountCredentials(email, password);
            ShopifyManager.this.buyClient.loginCustomer(accountCredentials, new Callback<Customer>() {
                @Override
                public void success(Customer response) {
                    ShopifyManager.this.customerToken = ShopifyManager.this.buyClient.getCustomerToken();
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error.getCause());
                }
            });
        });
    }

    public Observable<Customer> signUp(String email, String password, String firstName, String lastName) {
        return Observable.create(subscriber -> {
            AccountCredentials accountCredentials = new AccountCredentials(email, password, firstName, lastName);
            ShopifyManager.this.buyClient.createCustomer(accountCredentials, new Callback<Customer>() {
                @Override
                public void success(Customer response) {
                    ShopifyManager.this.customerToken = ShopifyManager.this.buyClient.getCustomerToken();
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public Observable<CustomerToken> getCustomerToken() {
        return Observable.create(subscriber -> {
            subscriber.onNext(ShopifyManager.this.customerToken);
        });
    }

    public Observable<CustomerToken> renewCustomer() {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.renewCustomer(new Callback<CustomerToken>() {
                @Override
                public void success(CustomerToken response) {
                    ShopifyManager.this.customerToken = response;
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error.getCause());
                }
            });
        });
    }

    public Observable<Void> logOut() {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.logoutCustomer(new Callback<Void>() {
                @Override
                public void success(Void response) {
                    ShopifyManager.this.customerToken = null;
                    subscriber.onNext(response);
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }


    public Observable updateCheckoutWithData(String email, Address billingAddress, Address shippingAddress, ShippingRate shippingRate, String discountCode, GiftCard giftCard) {
        this.checkout.setEmail(email);
        this.checkout.setBillingAddress(billingAddress);
        this.checkout.setShippingAddress(shippingAddress);
        this.checkout.setShippingRate(shippingRate);
        this.checkout.setDiscountCode(discountCode);
        this.checkout.addGiftCard(giftCard);
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.updateCheckout(checkout, new Callback<Checkout>() {
                @Override
                public void success(Checkout checkout) {
                    ShopifyManager.this.checkout = checkout;
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public Observable storeCreditCard(CreditCard creditCard) {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.storeCreditCard(creditCard, ShopifyManager.this.checkout, new Callback<PaymentToken>() {
                @Override
                public void success(PaymentToken response) {
                    ShopifyManager.this.paymentToken = response;
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public Observable completeCheckout() {
        return Observable.create(subscriber -> {
            ShopifyManager.this.buyClient.completeCheckout(ShopifyManager.this.paymentToken, ShopifyManager.this.checkout.getToken(), new Callback<Checkout>() {
                @Override
                public void success(Checkout response) {
                    ShopifyManager.this.checkout = response;
                    subscriber.onCompleted();
                }

                @Override
                public void failure(BuyClientError error) {
                    subscriber.onError(error);
                }
            });
        });
    }

    public Observable setProductQuantity(Product product, int productVariant, Integer quantity) {
        return Observable.create(subscriber -> {
            this.cart.setVariantQuantity(product.getVariants().get(productVariant), quantity);
            subscriber.onNext(this.cart.getTotalQuantity());
        });
    }

}
