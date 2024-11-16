package com.example.facturacliente.controllers;

import com.example.facturacliente.models.entity.Client;
import com.example.facturacliente.models.entity.Factura;
import com.example.facturacliente.models.entity.ItemFactura;
import com.example.facturacliente.models.entity.Product;
import com.example.facturacliente.service.ClienteService;
import com.example.facturacliente.service.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private IClientService clientService;

    private final Logger log = Logger.getLogger(getClass().getName());
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/form/{clientId}")
    public String crear(@PathVariable(value = "clientId") Long clientId, Model model, RedirectAttributes flash) {

        Client client = clientService.findById(clientId);
        if (client == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la bd");
            return "redirect:/factura";
        }

        Factura factura = new Factura();
        factura.setClient(client);

        model.addAttribute("factura", factura);
        model.addAttribute("title", "Crear Factura");

        return "factura/form";
    }

    @GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
    public @ResponseBody List<Product> cargarProductos(@PathVariable(value = "term") String term) {
        return clientService.findByName(term);
    }

    @PostMapping("/form")
    public String save(@Valid Factura factura,
                       BindingResult result,
                       Model model,
                       @RequestParam(name="item_id[]", required = false) Long[] itemId,
                       @RequestParam(name="cantidad[]", required = false) Integer[] cantidad,
                       RedirectAttributes flash,
                       SessionStatus status) {



        for(int i = 0; i<itemId.length; i++) {
            Product p = clientService.findProductById(itemId[i]);
            ItemFactura linea = new ItemFactura();
            linea.setCantidad(cantidad[i]);
            linea.setProduct(p);
            factura.addItem(linea);

            log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i]);
        }

        clienteService.saveFactura(factura);

        status.setComplete();

        flash.addFlashAttribute("success", "Factura guardada correctamente");
        return "redirect:/ver/" + factura.getClient().getId();

    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {

        Factura factura = clienteService.findFacturaById(id);

        if(factura == null) {
            flash.addFlashAttribute("error", "El la factura no existe en la bd");
            return "redirect:/list";
        }
        model.addAttribute("factura", factura);
        model.addAttribute("title", "Factura: ".concat(factura.getDescription()));

        return "factura/ver";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {

        Factura factura = clienteService.findFacturaById(id);

        if(factura != null) {
            clientService.deleteFactura(id);
            return "redirect:/ver/" + factura.getClient().getId();
        }

        return "redirect:/list";
    }
}
