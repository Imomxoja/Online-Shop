package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.service.history.HistoryService;
import uz.pdp.service.order.OrderService;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RedirectCustomerPage {
    private final OrderService service;
    private final HistoryService historyService;

    @GetMapping("/showProductsByCategory")
    public String customerWindow() {
        return "customerWindow";
    }
    @GetMapping("/showOrders")
    public String showOrders(HttpSession session, Model model){
        UUID id = (UUID) session.getAttribute("id");
        model.addAttribute("customerOrders", service.getCustomerOrders(id));
        return "CustomerOrders";
    }
    @GetMapping("/histories")
    public String histories(Model model, HttpSession session) {
        UUID id = (UUID) session.getAttribute("id");
        model.addAttribute("userHistories", historyService.getUserHistories(id));
        return "history";
    }
}
