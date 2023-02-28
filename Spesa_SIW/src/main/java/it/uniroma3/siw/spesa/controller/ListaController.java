package it.uniroma3.siw.spesa.controller;

import it.uniroma3.siw.spesa.model.Lista;
import it.uniroma3.siw.spesa.model.Prodotto;
import it.uniroma3.siw.spesa.model.User;
import it.uniroma3.siw.spesa.service.ListaService;
import it.uniroma3.siw.spesa.service.ProdottoService;
import it.uniroma3.siw.spesa.service.UserService;
import it.uniroma3.siw.spesa.validator.ListaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ListaController {

    @Autowired
    ProdottoService prodottoService;

    @Autowired
    ListaValidator listaValidator;

    @Autowired
    ListaService listaService;

    @Autowired
    UserService userService;

    @GetMapping("addLista")
    public String viewAddLista(Model model) {
        model.addAttribute("prodotti", prodottoService.findAll());
        model.addAttribute("userId", userService.getLocalUserId());
        model.addAttribute("lista", listaService.createLista());
        return "addListaForm";
    }

    @PostMapping("lista")
    public String addLista(@ModelAttribute("lista") Lista lista, BindingResult bindingResult, Model model,
                           @RequestParam("nome")String nome,
                           @RequestParam("prodottiIds") List<Long> prodottiIds, @RequestParam("userId")Long userId) {


        Lista valid = new Lista(null, nome,userService.getUser(userId),null);
        listaValidator.validate(valid, bindingResult);

        if (!bindingResult.hasErrors()) {
            listaService.addNome(lista, nome);
            listaService.addUserById(lista.getId(), userId);
            prodottiIds.forEach((Long id) -> {
                listaService.addProdotto(lista.getId(), id);
            });
            model.addAttribute("lista", lista);
            return "home";
        }

        model.addAttribute("prodotti", prodottoService.findAll());
        return "addListaForm";

    }

    @GetMapping("/deleteLista")
    public String viewDeleteLista(Model model){
        model.addAttribute("listeObjs",listaService.listaByUserId(userService.getLocalUserId()));
        return "removeListaForm";
    }

    @PostMapping("/listaRemove")
    public String deleteListaUser(@RequestParam("listeIds")List<Long> listeIds, Model model){
        listeIds.forEach((Long id) -> {
            listaService.deleteById(id);
        });
        return "home";
    }

    @GetMapping("/selectListaEditUser")
    public String viewSelectListaEdit(Model model){
        model.addAttribute("listeObjs", listaService.listaByUserId(userService.getLocalUserId()));
        return "selectListaEdit";
    }

    @PostMapping("/sendListaId")
    public String sendPiattoId(@RequestParam("listaId")Long id,@RequestParam("invio")String scelta) {
        if(scelta.equals("editNome"))
            return "redirect:/editNomeLista/" + id;
        else if(scelta.equals("add"))
            return "redirect:/editAddProdottoLista/" + id;
        else if(scelta.equals("remove"))
            return "redirect:/editRemoveProdottoLista/" + id;
        else
            return "home";
    }

    @GetMapping("/editNomeLista/{id}")
    public String viewEditNomeLista(@PathVariable("id")Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("lista", listaService.findById(id));
        model.addAttribute("user", userService.getUser(userService.getLocalUserId()));
        return "editNomeLista";

    }

    @PostMapping("editListaNome")
    public String viewPiattoEditFormId(@Valid @ModelAttribute("lista")Lista lista, BindingResult bindingResult,
                                       @RequestParam("userId")Long userId, @RequestParam("id")Long id,
                                       @RequestParam("name") String nome, Model model){
        listaValidator.validate(new Lista(null, nome, userService.getUser(userId), null), bindingResult);
        if(!bindingResult.hasErrors()) {
            listaService.editNome(id, nome);
            return "home";
        }
        model.addAttribute("listeObjs", listaService.listaByUserId(userService.getLocalUserId()));
        return "selectListaEdit";
    }

   @GetMapping("/editAddProdottoLista/{id}")
    public String viewEditAddProdottoLista(@PathVariable("id")Long id, Model model){
        model.addAttribute("prodottiObjs", listaService.getAllNotIn(id));
        model.addAttribute("id",id);
        return "editAddProdottoLista";
    }

    @PostMapping("/editAddProdottoListaUser")
    public String editAddProdottoListaUser(@RequestParam("prodottiIds")List<Long> prodottiIds,
                                           @RequestParam("id")Long id, Model model){
        prodottiIds.forEach((Long prodId) -> {
           listaService.addProdotto(id, prodId);
        });
        return "home";
    }

   @GetMapping("/editRemoveProdottoLista/{id}")
    public String viewEditNomeLista3(@PathVariable("id")Long id, Model model){
       model.addAttribute("prodottiObjs", listaService.prodotti(id));
       model.addAttribute("id",id);
       return "editRemoveProdottoLista";
    }

    @PostMapping("/editRemoveProdottoListaUser")
    public String editRemoveProdottoListaUser(@RequestParam("prodottiIds")List<Long> prodottiIds,
                                           @RequestParam("id")Long id, Model model){
        prodottiIds.forEach((Long prodId) -> {
            listaService.deleteProdotto(id, prodId);
        });
        return "home";
    }

    @GetMapping("/viewListaSelect")
    public String viewSelectListaView(Model model){
        model.addAttribute("listeObjs", listaService.listaByUserId(userService.getLocalUserId()));
        return "selectListaView";
    }

    @PostMapping("sendListaIdView")
    public String sendListaIdView(@RequestParam("listaId")Long id, Model model){
        return "redirect:/viewLista/" + id;
    }

    @GetMapping("/viewLista/{id}")
    public String viewProdottiLista(@PathVariable("id")Long id, Model model){
        model.addAttribute("prodottiObjs", listaService.prodotti(id));
        model.addAttribute("somma", listaService.costoTotale(id));
        return "viewLista";
    }

    @GetMapping("/admin/selectUtenteRemove")
    public String viewSelectUtenteLista(Model model){
        model.addAttribute("userObjs", userService.getAllUsers());
        return "admin/selectUtenteLista";
    }

    @PostMapping("/admin/selectUtente")
    public String selectUtenteSend(@RequestParam("userId")Long id, Model model){
        return "redirect:/admin/removeListaFormAdmin/"+id;
    }

    @GetMapping("/admin/removeListaFormAdmin/{id}")
    public String viewRemoveListaFormAdmin(@PathVariable("id")Long id, Model model){
        model.addAttribute("listeObjs",listaService.listaByUserId(id));
        return "admin/removeListaFormAdmin";
    }

    @PostMapping("/admin/listaRemove")
    public String deleteListaAdmin(@RequestParam("listeIds")List<Long> listeIds, Model model){
        listeIds.forEach((Long id) -> {
            listaService.deleteById(id);
        });
        return "admin/home";
    }
}