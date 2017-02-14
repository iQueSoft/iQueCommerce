//package net.iquesoft.project.seedEcommerce.presentation.presenter;
//
//import com.shopify.buy.model.Product;
//
//import net.iquesoft.project.seedEcommerce.domain.interactor.DefaultSubscriber;
//import net.iquesoft.project.seedEcommerce.domain.interactor.GetProductsIdFromWishListUseCase;
//import net.iquesoft.project.seedEcommerce.domain.interactor.GetProductsListByIdUseCase;
//import net.iquesoft.project.seedEcommerce.presentation.mapper.ModelDataMapper;
//import net.iquesoft.project.seedEcommerce.presentation.model.CartModel;
//import net.iquesoft.project.seedEcommerce.presentation.model.ProductModel;
//import net.iquesoft.project.seedEcommerce.presentation.model.SelectedProductModel;
//import net.iquesoft.project.seedEcommerce.presentation.model.ShopModel;
//import net.iquesoft.project.seedEcommerce.presentation.view.fragment.WishListFragment;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class WishListPresenterTest {
//
//    @Mock
//    CartModel cartModel;
//    @Mock
//    ShopModel shopModel;
//    @Mock
//    ModelDataMapper modelDataMapper;
//    @Mock
//    SelectedProductModel selectedProductModel;
//    @Mock
//    GetProductsListByIdUseCase getProductsListByIdUseCase;
//    @Mock
//    GetProductsIdFromWishListUseCase getProductsIdFromWishListUseCase;
//    @Mock
//    WishListFragment fragmentView;
//    @Mock
//    List<ProductModel> collection = Collections.EMPTY_LIST;
//
//
//    @Captor
//    ArgumentCaptor<DefaultSubscriber<List<Long>>> productIdsCapture;
//    @Captor
//    ArgumentCaptor<DefaultSubscriber<List<Product>>> productCapture;
//
//    private WishListPresenter presenter;
//    private List<Long> ids;
//    private List<Product> products;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        when(shopModel.getMoneyFormat()).thenReturn("USD");
//        when(modelDataMapper.transformProductCollection(products)).thenReturn(collection);
//        this.presenter = new WishListPresenter(cartModel, shopModel, modelDataMapper, selectedProductModel, getProductsListByIdUseCase, getProductsIdFromWishListUseCase);
//        this.presenter.setView(fragmentView);
//    }
//
//    @Test
//    public void initialize() throws Exception {
//        this.presenter.initialize();
//        verify(fragmentView).showLoading();
//        verify(getProductsIdFromWishListUseCase).executeRealm(productIdsCapture.capture());
//
//        this.productIdsCapture.getValue().onNext(ids);
//        verify(fragmentView).showLoading();
//        verify(getProductsListByIdUseCase).setArguments(ids);
//        verify(getProductsListByIdUseCase).execute(productCapture.capture());
//
//        productCapture.getValue().onNext(products);
//        verify(fragmentView).loadContent(collection, shopModel.getMoneyFormat());
//    }
//
//}