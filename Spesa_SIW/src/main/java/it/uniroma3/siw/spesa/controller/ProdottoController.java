package it.uniroma3.siw.spesa.controller;

import it.uniroma3.siw.spesa.model.Prodotto;
import it.uniroma3.siw.spesa.service.ProdottoService;
import it.uniroma3.siw.spesa.validator.ProdottoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProdottoController {

    @Autowired
    ProdottoService prodottoService;

    @Autowired
    ProdottoValidator prodottoValidator;

    @GetMapping("/admin/addProdotto")
    public String viewAddProdottoForm(Model model) {
        model.addAttribute("prodotto", prodottoService.createProdotto());
        return "admin/addProdottoForm";
    }

    @PostMapping("/admin/prodotto")
    public String addProdotto(@Valid @ModelAttribute("prodotto") Prodotto prodotto, BindingResult bindingResult, Model model) {

        prodottoValidator.validate(prodotto, bindingResult);
        if (!bindingResult.hasErrors()) {
            model.addAttribute("prodotto", prodotto);
            prodottoService.save(prodotto);
            return "admin/home";
        }
        return "admin/addProdottoForm";
    }
    @GetMapping("/admin/removeProdotto")
    public String viewRemoveProdottoForm(Model model){
        model.addAttribute("prodottiObjs", prodottoService.findAll());
        return "admin/removeProdottoForm";
    }

    @PostMapping("/admin/prodottoRemove")
    public String removeProdotto(@RequestParam("prodottiIds") List<Long> prodottiIds, Model model){
        prodottiIds.forEach((Long id)->{
            prodottoService.deleteById(id);
        });
        return "admin/home";
    }

    @GetMapping("/admin/selectProdotto")
    public String viewSelecEditForm(Model model){
        model.addAttribute("prodottiObjs", prodottoService.findAll());
        return "admin/selectProdottoEdit";
    }

    @PostMapping("/admin/prodottoSelectEdit")
    public String selectEditInvio(@RequestParam("prodottoId")Long id, Model model){
        return "redirect:/admin/prodottoEdit/" + id;
    }

    @GetMapping("/admin/prodottoEdit/{id}")
    public String viewEditProdotto(@PathVariable("id")Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("prodotto",prodottoService.findById(id));
        return "admin/editProdottoForm";
    }

    @PostMapping("/admin/editProdotto")
    public String editProdotto(@Valid @ModelAttribute("prodotto")Prodotto prodotto,BindingResult bindingResult, @RequestParam("nome")String nome, @RequestParam("descrizione")String descrizione,
                               @RequestParam("prezzo")Double prezzo, @RequestParam("id")Long id, Model model){

        prodottoValidator.validate(new Prodotto(null, nome, descrizione,prezzo,null), bindingResult);
        if(!bindingResult.hasErrors()) {
            prodottoService.edit(id, nome, descrizione, prezzo);
            model.addAttribute("prodotto", prodotto);
            return "admin/home";
        }
        model.addAttribute("prodottiObjs", prodottoService.findAll());
        return "admin/selectProdottoEdit";
    }



}
