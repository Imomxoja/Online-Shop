package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import uz.pdp.domain.dto.history.HistoryCreateDto;
import uz.pdp.domain.dto.order.OrderReadDto;
import uz.pdp.service.history.HistoryService;
import uz.pdp.service.order.OrderService;
import uz.pdp.service.product.ProductService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService service;
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/addOrder/{id}")
    public String addHistory(
            @PathVariable("id") UUID orderId, Model model, HttpSession session
    ) {
        UUID userId = (UUID) session.getAttribute("id");
        HistoryCreateDto dto = new HistoryCreateDto();
        OrderReadDto byId = orderService.getById(orderId);
        dto.setAmount(byId.getAmount());
        dto.setProductName(byId.getProductName());
        dto.setTotalPrice(byId.getTotalPrice());
        dto.setUserId(userId);
        dto.setProductId(productService.findByName(byId.getProductName()).getId());

        service.create(dto);
        orderService.delete(orderId);
        return "CustomerOrders";
    }
}
