package com.user.ecommerce_project.business.mapping;

import com.user.ecommerce_project.business.dtos.requests.cartRequests.AddCartItemRequest;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.CreateCartRequest;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.UpdateCartItemRequest;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.UpdateCartRequest;
import com.user.ecommerce_project.business.dtos.requests.categoryRequests.CreateCategoryRequest;
import com.user.ecommerce_project.business.dtos.requests.categoryRequests.UpdateCategoryRequest;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.CreateOrderItemRequest;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.CreateOrderRequest;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.UpdateOrderItemRequest;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.UpdateOrderRequest;
import com.user.ecommerce_project.business.dtos.requests.productRequests.CreateProductRequest;
import com.user.ecommerce_project.business.dtos.requests.productRequests.UpdateProductRequest;
import com.user.ecommerce_project.business.dtos.requests.userRequests.CreateUserRequest;
import com.user.ecommerce_project.business.dtos.requests.userRequests.UpdateUserRequest;
import com.user.ecommerce_project.business.dtos.responses.cartResponses.CartItemResponse;
import com.user.ecommerce_project.business.dtos.responses.cartResponses.CartResponse;
import com.user.ecommerce_project.business.dtos.responses.categoryResponses.CategoryResponse;
import com.user.ecommerce_project.business.dtos.responses.categoryResponses.CategoryWithProductsResponse;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderDetailResponse;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderItemResponse;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderResponse;
import com.user.ecommerce_project.business.dtos.responses.productResponses.ProductDetailResponse;
import com.user.ecommerce_project.business.dtos.responses.productResponses.ProductResponse;
import com.user.ecommerce_project.business.dtos.responses.userResponses.UserResponse;
import com.user.ecommerce_project.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ECommerceMapper {

    User mapCreateUserRequestToUser(CreateUserRequest createUserRequest);
    
    void updateUserFromRequest(UpdateUserRequest updateUserRequest, @MappingTarget User user);
    
    UserResponse mapUserToUserResponse(User user);
    List<UserResponse> mapUserListToUserResponseList(List<User> users);

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "cartItems", ignore = true)
    Product mapCreateProductRequestToProduct(CreateProductRequest createProductRequest);
    
    @Mapping(target = "category.id", source = "categoryId")
    void updateProductFromRequest(UpdateProductRequest updateProductRequest, @MappingTarget Product product);
    
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    ProductResponse mapProductToProductResponse(Product product);
    
    @Mapping(target = "inStock", expression = "java(product.getStockQuantity() > 0)")
    @Mapping(target = "category", source = "category")
    ProductDetailResponse mapProductToProductDetailResponse(Product product);
    
    List<ProductResponse> mapProductListToProductResponseList(List<Product> products);

    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);
    
    void updateCategoryFromRequest(UpdateCategoryRequest updateCategoryRequest, @MappingTarget Category category);
    
    CategoryResponse mapCategoryToCategoryResponse(Category category);
    
    @Mapping(target = "products", source = "products")
    CategoryWithProductsResponse mapCategoryToCategoryWithProductsResponse(Category category);
    
    List<CategoryResponse> mapCategoryListToCategoryResponseList(List<Category> categories);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "cartItems", ignore = true)
    Cart mapCreateCartRequestToCart(CreateCartRequest createCartRequest);
    
    void updateCartFromRequest(UpdateCartRequest updateCartRequest, @MappingTarget Cart cart);
    
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "cartItems", source = "cartItems")
    @Mapping(target = "itemCount", expression = "java(cart.getCartItems() != null ? cart.getCartItems().size() : 0)")
    CartResponse mapCartToCartResponse(Cart cart);

    @Mapping(target = "cart.id", source = "cartId")
    @Mapping(target = "product.id", source = "productId")
    CartItem mapAddCartItemRequestToCartItem(AddCartItemRequest addCartItemRequest);
    
    void updateCartItemFromRequest(UpdateCartItemRequest updateCartItemRequest, @MappingTarget CartItem cartItem);
    
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productDescription", source = "product.description")
    @Mapping(target = "unitPrice", source = "unitPrice")
    @Mapping(target = "subtotal", expression = "java(cartItem.getQuantity() != null && cartItem.getUnitPrice() != null ? cartItem.getUnitPrice().multiply(new java.math.BigDecimal(cartItem.getQuantity())) : null)")
    CartItemResponse mapCartItemToCartItemResponse(CartItem cartItem);
    
    List<CartItemResponse> mapCartItemListToCartItemResponseList(List<CartItem> cartItems);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "orderItems", ignore = true)
    Order mapCreateOrderRequestToOrder(CreateOrderRequest createOrderRequest);
    
    void updateOrderFromRequest(UpdateOrderRequest updateOrderRequest, @MappingTarget Order order);
    
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userFullName", expression = "java(order.getUser().getFirstName() + ' ' + order.getUser().getLastName())")
    OrderResponse mapOrderToOrderResponse(Order order);
    
    @Mapping(target = "user", source = "user")
    @Mapping(target = "orderItems", source = "orderItems")
    OrderDetailResponse mapOrderToOrderDetailResponse(Order order);
    
    List<OrderResponse> mapOrderListToOrderResponseList(List<Order> orders);

    @Mapping(target = "order.id", source = "orderId")
    @Mapping(target = "product.id", source = "productId")
    OrderItem mapCreateOrderItemRequestToOrderItem(CreateOrderItemRequest createOrderItemRequest);
    
    void updateOrderItemFromRequest(UpdateOrderItemRequest updateOrderItemRequest, @MappingTarget OrderItem orderItem);
    
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "subtotal", expression = "java(orderItem.getQuantity() != null && orderItem.getUnitPrice() != null ? orderItem.getUnitPrice().multiply(new java.math.BigDecimal(orderItem.getQuantity())) : null)")
    OrderItemResponse mapOrderItemToOrderItemResponse(OrderItem orderItem);
    
    List<OrderItemResponse> mapOrderItemListToOrderItemResponseList(List<OrderItem> orderItems);
}