package com.anis.produits.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anis.produits.entities.Produit;
import com.anis.produits.service.ProduitService;

@Controller
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // Display paginated list of products
    @RequestMapping("/ListeProduits")
    public String listeProduits(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "listeProduits";
    }

    // Show the create product form
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("produit", new Produit());
        return "createProduit";
    }

    // Save new product
    @RequestMapping("/saveProduit")
    public String saveProduit(
            @ModelAttribute("produit") Produit produit,
            @RequestParam("date") String date,
            ModelMap modelMap) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(date);
        produit.setDateCreation(dateCreation);

        Produit savedProduit = produitService.saveProduit(produit);
        String msg = "Produit enregistré avec Id " + savedProduit.getIdProduit();
        modelMap.addAttribute("msg", msg);

        return "createProduit";
    }

    // Delete a product
    @RequestMapping("/supprimerProduit")
    public String supprimerProduit(
            @RequestParam("id") Long id,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
    
        // Delete the product by its ID
        produitService.deleteProduitById(id);

        // Reload the paginated list of products after deletion
        Page<Produit> prods = produitService.getAllProduitsParPage(page, size);

        // Add the necessary attributes to the model for Thymeleaf
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page); // Keep the current page
        modelMap.addAttribute("size", size); // Keep the page size

        // Redirect back to the "listeProduits" page to show the updated list
        return "listeProduits";
    }


    // Show edit form for a product
    @RequestMapping("/modifierProduit")
    public String editerProduit(
            @RequestParam("id") Long id,
            ModelMap modelMap) {

        Produit produit = produitService.getProduit(id);
        modelMap.addAttribute("produit", produit);

        return "editerProduit";
    }

    // Update product
    @RequestMapping("/updateProduit")
    public String updateProduit(
            @ModelAttribute("produit") Produit produit,
            @RequestParam("date") String date,
            ModelMap modelMap) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(date);
        produit.setDateCreation(dateCreation);

        produitService.updateProduit(produit);
        List<Produit> prods = produitService.getAllProduits();
        modelMap.addAttribute("produits", prods);

        return "listeProduits";
    }
}
