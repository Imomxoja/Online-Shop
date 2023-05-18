package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.service.order.OrderService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OderController {
    private final OrderService service;

    @PostMapping("/setStatus/{id}")
    public String set(@PathVariable("id") UUID id,
                      @RequestParam("status") String status,
                      Model model, HttpSession session) {
          service.setStatus(id, status);
        UUID id1 = (UUID) session.getAttribute("id");
        model.addAttribute("orders", service.getOrdersForMyProducts(id1));
        return "sellerOrders";
    }

    @PostMapping("/setAmount/{id}")
    public String setAmount(
            @PathVariable("id") UUID id,
            @RequestParam("amount") Integer amount,
            Model model, HttpSession session) {
        UUID id1 = (UUID) session.getAttribute("id");
        service.setOrderAmount(id, amount);
        model.addAttribute("customerOrders", service.getCustomerOrders(id1));
        return "CustomerOrders";
    }

}
