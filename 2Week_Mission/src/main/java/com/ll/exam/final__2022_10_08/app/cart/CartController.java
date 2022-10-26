package com.ll.exam.final__2022_10_08.app.cart;

import com.ll.exam.final__2022_10_08.app.cart.dto.CartItemDto;
import com.ll.exam.final__2022_10_08.app.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart/list")
    public String showCartList(Principal principal, Model model) {
        List<CartItemDto> cartItems = cartService.findAllByUsername(principal.getName());
        model.addAttribute("cartItems", cartItems);

        return "cart/list";
    }
}
