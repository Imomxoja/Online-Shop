package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.domain.dto.product.ProductCreateDto;
import uz.pdp.service.product.ProductService;
import java.util.UUID;

@Controller(value = "/crud")
@RequiredArgsConstructor
public class CrudWindowController {
    private final ProductService service;

    @GetMapping("/create")
    public String createPage() {
        return "crudWindow";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute ProductCreateDto dto,
            Model model
    ) {
        if (service.findByName(dto.getName()) == null) {
            service.create(dto);
            model.addAttribute("all", service.getAll());
            return "crudWindow";
        }
        model.addAttribute("message", "This product already saved");
        return "crudWindow";
    }

    UUID productId = null;

    @GetMapping("/update/{id}")
    public String updatePage(@PathVariable("id") UUID id) {
        productId = id;
        return "update";
    }

    @PostMapping("/set")
    public String update(@ModelAttribute ProductCreateDto dto,
                         Model model) {
        service.update(dto, productId);
        model.addAttribute("all", service.getAll());
        return "crudWindow";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id, Model model) {
        service.delete(id);

        model.addAttribute("all", service.getAll());
        return "crudWindow";
    }
}
