package com.example.gtics_lab6_20180805.controller;


import com.example.gtics_lab6_20180805.entity.Site;
import com.example.gtics_lab6_20180805.repository.LocationRepository;
import com.example.gtics_lab6_20180805.repository.SiteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/site")
public class SiteController {

    final LocationRepository locationRepository;
    private final SiteRepository siteRepository;

    public SiteController(SiteRepository siteRepository, LocationRepository locationRepository) {
        this.siteRepository = siteRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping(value = {"", "/"})
    public String listaSitios(Model model) {
        model.addAttribute("listaSitios", siteRepository.findAll());
        return "sites/list";
    }

    @GetMapping("/new")
    public String nuevoSitioFrm(Model model, @ModelAttribute("product") Site site) {
        model.addAttribute("listaLocaciones", locationRepository.findAll());
        return "site/newFrm";
    }

    @PostMapping("/save")
    public String guardarSitio(RedirectAttributes attr,
                                  Model model,
                                  @ModelAttribute("sitio") @Valid Site site,
                                  BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            model.addAttribute("listaLocaciones", locationRepository.findAll());
            return "site/newFrm";
        }else{
            if (site.getId() == 0) {
                List<Site> productList = siteRepository.findBySitename(site.getSitename());
                boolean existe = false;
                for (Site p : productList) {
                    if (p.getSitename().equals(site.getSitename())) {
                        existe = true;
                        break;
                    }
                }
                if (existe) {
                    System.out.println("El producto existe");
                    model.addAttribute("listaLocaciones", locationRepository.findAll());
                    return "site/newFrm";
                } else {
                    attr.addFlashAttribute("msg", "Sitio creado exitosamente");
                    siteRepository.save(site);
                    return "redirect:/site";
                }
            } else {
                attr.addFlashAttribute("msg", "Sitio actualizado exitosamente");
                siteRepository.save(site);
                return "redirect:/site";
            }
        }
    }

    @GetMapping("/edit")
    public String editarTransportista(Model model, @RequestParam("id") int id) {

        Optional<Product> optProduct = productRepository.findById(id);

        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            model.addAttribute("product", product);
            model.addAttribute("listaCategorias", categoryRepository.findAll());
            model.addAttribute("listaProveedores", supplierRepository.findAll());
            return "product/newFrm";
        } else {
            return "redirect:/product";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Product> optProduct = productRepository.findById(id);

        if (optProduct.isPresent()) {
            productRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
        }
        return "redirect:/product";

    }

}
