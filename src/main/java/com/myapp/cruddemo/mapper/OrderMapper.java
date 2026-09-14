
package com.myapp.cruddemo.mapper;

import com.myapp.cruddemo.dto.OrderResponseDto;
import com.myapp.cruddemo.dto.OrderItemResponseDto;
import com.myapp.cruddemo.entity.Order;
import com.myapp.cruddemo.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderResponseDto entityToResponseDto(Order order);

    @Mapping(source = "product.id", target = "productId")
    OrderItemResponseDto orderItemToResponseDto(OrderItem orderItem);
}