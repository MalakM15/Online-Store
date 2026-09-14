package com.myapp.cruddemo.mapper;

import com.myapp.cruddemo.entity.Cart;
import com.myapp.cruddemo.entity.CartItem;
import com.myapp.cruddemo.dto.CartResponseDto;
import com.myapp.cruddemo.dto.CartItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "user.id", target = "userId")
    CartResponseDto entityToResponseDto(Cart cart);

    @Mapping(source = "product.id", target = "productId")
    CartItemResponseDto cartItemToResponseDto(CartItem cartItem);
}