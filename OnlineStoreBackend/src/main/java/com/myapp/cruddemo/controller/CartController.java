package com.myapp.cruddemo.controller;
import com.myapp.cruddemo.dto.CartResponseDto;
import com.myapp.cruddemo.mapper.CartMapper;
import com.myapp.cruddemo.service.CartService;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;
    public CartController(CartService cartService, CartMapper cartMapper ) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    @GetMapping
    public CartResponseDto getCart(Authentication authentication){ // The cart must be the user's cart
        return cartMapper.entityToResponseDto(cartService.getCartByUserId(authentication));
    }

    @PutMapping("/items/{productId}/{quantity}")
    public CartResponseDto addItems(@PathVariable int productId , @PathVariable int quantity, Authentication authentication){
 
        return cartMapper.entityToResponseDto(cartService.addToCart(authentication,productId , quantity));
    }

    @DeleteMapping("/items/{productId}/{quantity}")
    public CartResponseDto removeItem (@PathVariable int productId , @PathVariable int quantity,Authentication authentication){

        return cartMapper.entityToResponseDto(cartService.removeFromCart(authentication, productId, quantity));
    }

}
