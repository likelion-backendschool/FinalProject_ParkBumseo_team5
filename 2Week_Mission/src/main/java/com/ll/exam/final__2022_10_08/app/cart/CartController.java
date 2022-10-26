package com.ll.exam.final__2022_10_08.app.cart;

import com.ll.exam.final__2022_10_08.app.cart.dto.CartItemDto;
import com.ll.exam.final__2022_10_08.app.cart.service.CartService;
import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import com.ll.exam.final__2022_10_08.app.member.service.MemberService;
import com.ll.exam.final__2022_10_08.app.product.entity.Product;
import com.ll.exam.final__2022_10_08.app.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;
    private final ProductService productService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart/list")
    public String showCartList(Principal principal, Model model) {
        List<CartItemDto> cartItems = cartService.findAllByUsername(principal.getName());
        model.addAttribute("cartItems", cartItems);

        return "cart/list";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("add/{productId}")
    public String add(
            @PathVariable("productId") long productId, Principal principal
    ) {

        Member member = memberService.findByUsername(principal.getName());
        Product product = productService.findById(productId);

        cartService.addProduct(member, product);

        return "redirect:/cart/list";
    }

}
