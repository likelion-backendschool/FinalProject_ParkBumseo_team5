package com.ll.exam.final__2022_10_08.app.cart.service;

import com.ll.exam.final__2022_10_08.app.cart.dto.CartItemDto;
import com.ll.exam.final__2022_10_08.app.cart.entity.CartItem;
import com.ll.exam.final__2022_10_08.app.cart.repository.CartRepository;
import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import com.ll.exam.final__2022_10_08.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Member member = memberService.findByUsername(username).orElseThrow(); // Todo : 예외처리 추후에 해야됨

        List<CartItem> lists = cartRepository.findAllByMemberAndIsOrdered(member, false);
        List<CartItemDto> cartItemDto = new ArrayList<>();

        /**
         * Entity to Dto
         */
        for (CartItem cartItem : lists)
            cartItemDto.add(CartItemDto.fromEntity(cartItem));

        return cartItemDto;

    }
}
