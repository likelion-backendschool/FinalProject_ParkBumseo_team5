package com.ll.exam.final__2022_10_08.app.cart.dto;

import com.ll.exam.final__2022_10_08.app.cart.entity.CartItem;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private Long id;
    private Long productId;

    private String subject;
    private String writer;
    private int price;

    public static CartItemDto fromEntity(CartItem cartItem){
        return CartItemDto.builder()
                .id(cartItem.getId())
                .productId(cartItem.getId())
                .subject(cartItem.getProduct().getSubject())
                .writer(cartItem.getMember().getNickname())
                .price(cartItem.getProduct().getPrice())
                .build();
    }
}
