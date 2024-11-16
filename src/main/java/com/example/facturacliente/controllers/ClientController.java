package com.example.facturacliente.controllers;

import com.example.facturacliente.models.entity.Client;
import com.example.facturacliente.service.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("title", "Client List");
        model.addAttribute("clients", clientService.findAll());
        return "list";
    }

    @GetMapping("/form")
    public String create(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        model.addAttribute("title", "Client Form");
        return "form";
    }

    @GetMapping("/form/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model) {

        Client client = null;

        if(id>0){
            client = clientService.findById(id);
        }else{
            return "redirect:/list";
        }
        model.addAttribute("client", client);
        model.addAttribute("title", "Edit Client Form");
        return "form";

    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Model model) {

        Client client = clientService.findById(id);
        if(client==null){
            model.addAttribute("error", "El cliente no existe");
            return "redirect:/list";
        }

        model.addAttribute("client", client);
        model.addAttribute("title", "Detalle cliente: "+client.getName());

        return "ver";
    }

    @PostMapping("/form")
    public String save(@Valid Client client, BindingResult result, Model model, SessionStatus status) {
        if(result.hasErrors()) {
            model.addAttribute("title", "Client Form");
            return "form";
        }
        clientService.save(client);
        status.setComplete();
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, Model model) {
        if(id>0){
            clientService.delete(id);
        }else {
            return "redirect:/list";
        }
        return "redirect:/list";
    }
}
