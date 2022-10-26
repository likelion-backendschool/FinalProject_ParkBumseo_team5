package com.ll.exam.final__2022_10_08.app.cart.repository;

import com.ll.exam.final__2022_10_08.app.cart.entity.CartItem;
import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByMemberAndIsOrdered(Member member, boolean isOrdered);

}
