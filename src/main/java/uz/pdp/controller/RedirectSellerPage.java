package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.service.order.OrderService;
import uz.pdp.service.product.ProductService;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RedirectSellerPage {
    private final ProductService service;
    private final OrderService orderService;

    @GetMapping("/crud-page")
    public String crud(Model model, HttpSession session) {
        UUID id = (UUID) session.getAttribute("id");
        model.addAttribute("id", id);
        model.addAttribute("all", service.getAll());
        return "crudWindow";
    }

    @GetMapping("/order-page")
    public String orders(Model model, HttpSession session) {
        UUID id = (UUID) session.getAttribute("id");
        model.addAttribute("orders", orderService.getOrdersForMyProducts(id));
        return "sellerOrders";
    }
}
