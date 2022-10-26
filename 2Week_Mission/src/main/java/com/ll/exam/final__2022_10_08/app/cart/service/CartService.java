package com.ll.exam.final__2022_10_08.app.cart.service;

import com.ll.exam.final__2022_10_08.app.cart.dto.CartItemDto;
import com.ll.exam.final__2022_10_08.app.cart.entity.CartItem;
import com.ll.exam.final__2022_10_08.app.cart.repository.CartRepository;
import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import com.ll.exam.final__2022_10_08.app.member.service.MemberService;
import com.ll.exam.final__2022_10_08.app.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final MemberService memberService;
    private final CartRepository cartRepository;

    @Transactional(readOnly = true)
    public List<CartItemDto> findAllByUsername(String username) {
        Member member = memberService.findByUsername(username);

        List<CartItem> lists = cartRepository.findAllByMemberAndIsOrdered(member, false);
        List<CartItemDto> cartItemDto = new ArrayList<>();

        /**
         * Entity to Dto
         */
        for (CartItem cartItem : lists)
            cartItemDto.add(CartItemDto.fromEntity(cartItem));

        return cartItemDto;

    }

    @Transactional
    public void addProduct(Member member, Product product) {
        LocalDateTime now = LocalDateTime.now();
        cartRepository.save(CartItem.builder()
                .member(member)
                .product(product)
                .build());

    }
    @Transactional
    public void deleteProduct(long id) {
        cartRepository.deleteById(id);
    }

}
