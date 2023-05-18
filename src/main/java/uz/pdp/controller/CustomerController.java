package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.domain.dto.order.OrderCreateDto;
import uz.pdp.domain.dto.product.ProductReadDto;
import uz.pdp.domain.entity.order.OrderStatus;
import uz.pdp.service.order.OrderService;
import uz.pdp.service.product.ProductService;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final ProductService productService;
    private final OrderService orderService;

    @PostMapping("/category")
    public String showByCategoryName(@RequestParam("category") String category, Model model) {
        model.addAttribute("products", productService.getByCategoryName(category));
        return "customerWindow";
    }

    String part1 = null;
    @PostMapping("/search")
    public String searchByPartOfNameProduct(@RequestParam("part") String part, Model model) {
        part1 = part;
        model.addAttribute("products", productService.searchByPartOfNameProduct(part));
        return "customerWindow";
    }

    @GetMapping("/add/{id}")
    public String order(@PathVariable("id") UUID id, Model model, HttpSession session) {
        UUID userId = (UUID) session.getAttribute("id");

        ProductReadDto byId = productService.getById(id);
        OrderCreateDto dto = new OrderCreateDto()
                .setProductId(id)
                .setUserId(userId)
                .setStatus(OrderStatus.SHIPPING)
                .setAmount(1)
                .setProductName(byId.getName())
                .setTotalPrice(byId.getPrice());

        orderService.create(dto);
        model.addAttribute("products", productService.searchByPartOfNameProduct(part1));
        model.addAttribute("products", productService.getByCategoryName(String.valueOf(byId.getCategory())));
        return "customerWindow";
    }



}
