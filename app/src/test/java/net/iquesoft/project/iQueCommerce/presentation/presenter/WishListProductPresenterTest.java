//package net.iquesoft.project.seedEcommerce.presentation.presenter;
//
//import android.content.Context;
//
//import net.iquesoft.project.seedEcommerce.domain.interactor.IsProductInWishListUseCase;
//import net.iquesoft.project.seedEcommerce.domain.interactor.RemoveProductFromWishListUseCase;
//import net.iquesoft.project.seedEcommerce.domain.interactor.SaveProductToWishListUseCase;
//import net.iquesoft.project.seedEcommerce.presentation.model.CartModel;
//import net.iquesoft.project.seedEcommerce.presentation.model.SelectedProductModel;
//import net.iquesoft.project.seedEcommerce.presentation.model.ShopModel;
//import net.iquesoft.project.seedEcommerce.presentation.view.fragment.WishListProductFragment;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class WishListProductPresenterTest {
//
//    private static final java.lang.String MONEY_FORMAT = "USD";
//    private static final java.lang.Integer ITEM_COUNT = 10;
//
//    @Mock
//    Context context;
//    @Mock
//    SelectedProductModel selectedProductModel;
//    @Mock
//    CartModel cartModel;
//    @Mock
//    ShopModel shopModel;
//    @Mock
//    SaveProductToWishListUseCase saveProductToWishListUseCase;
//    @Mock
//    IsProductInWishListUseCase isProductInWishListUseCase;
//    @Mock
//    RemoveProductFromWishListUseCase removeProductFromWishListUseCase;
//    @Mock
//    private WishListProductFragment fragmentView;
//    @Mock
//    private net.iquesoft.project.seedEcommerce.presentation.model.ProductModel productModel;
//    private WishListProductPresenter presenter;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        when(selectedProductModel.getSelectedProductModel()).thenReturn(productModel);
//        when(shopModel.getMoneyFormat()).thenReturn(MONEY_FORMAT);
//        when(cartModel.getItemCount()).thenReturn(ITEM_COUNT);
//        this.presenter = new WishListProductPresenter(saveProductToWishListUseCase, removeProductFromWishListUseCase, isProductInWishListUseCase, selectedProductModel, cartModel, shopModel);
//    }
//
//    @Test
//    public void setView() throws Exception {
//        this.presenter.setView(fragmentView);
//        verify(presenter).setView(fragmentView);
//    }
//
//    @Test
//    public void initialize() throws Exception {
//        this.presenter.initialize();
//        verify(fragmentView).loadContent(productModel, MONEY_FORMAT);
//    }
//
//    @Test
//    public void getCartItemCount() throws Exception {
//        this.presenter.getCartItemCount();
//        verify(presenter).getCartItemCount();
//    }
//
//
//}