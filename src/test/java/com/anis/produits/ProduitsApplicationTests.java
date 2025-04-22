package com.anis.produits;

import com.anis.produits.entities.Produit;
import com.anis.produits.repo.ProduitRepository;
import com.anis.produits.service.ProduitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ProduitsApplicationTests implements CommandLineRunner {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitService produitService;

    public static void main(String[] args) {
        SpringApplication.run(ProduitsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("▶ Initialisation des produits...");

        // Seeding the data to the repository when the application starts
        produitRepository.save(new Produit("PC Dell", 2200.500, new Date()));
        produitRepository.save(new Produit("PC Asus Tuf", 3400.500, new Date()));
        produitRepository.save(new Produit("PC MSI", 4530.500, new Date()));
        produitRepository.save(new Produit("Imprimante EPSON", 2000.500, new Date()));
        produitRepository.save(new Produit("Imprimante Canon", 2300.500, new Date()));
        produitRepository.save(new Produit("PC DELL", 2320.500, new Date()));

        System.out.println("✅ Produits initialisés avec succès.");
    }

    // Test case for creating products - for seeding data you could manually call this from somewhere else or use the `run` method above.
    public void testCreateProduit() {
        Produit prod = new Produit("PC Dell", 2200.500, new Date());
        Produit prod1 = new Produit("PC Asus Tuf", 3400.500, new Date());
        Produit prod2 = new Produit("PC MSI", 4530.500, new Date());
        Produit prod3 = new Produit("Imprimante EPSON", 2000.500, new Date());
        Produit prod4 = new Produit("Imprimante Canon", 2000.500, new Date());
        Produit prod5 = new Produit("PC DELL", 2300.500, new Date());
        produitRepository.save(prod);
        produitRepository.save(prod1);
        produitRepository.save(prod2);
        produitRepository.save(prod3);
        produitRepository.save(prod4);
        produitRepository.save(prod5);
    }

    // Test case for finding a product by ID
    public void testFindProduit() {
        Produit p = produitRepository.findById(1L).orElse(null);
        if (p != null)
            System.out.println(p);
        else
            System.out.println("⚠️ Produit introuvable");
    }

    // Test case for updating a product
    public void testUpdateProduit() {
        Produit p = produitRepository.findById(1L).orElse(null);
        if (p != null) {
            p.setPrixProduit(1000.0);
            produitRepository.save(p);
            System.out.println("Produit mis à jour : " + p);
        } else {
            System.out.println("⚠️ Produit avec ID 1 non trouvé pour mise à jour.");
        }
    }

    // Test case for deleting a product
    public void testDeleteProduit() {
        produitRepository.deleteById(1L);
    }

    // Test case to list all products
    public void testListerTousProduits() {
        List<Produit> prods = produitRepository.findAll();
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    // Test case for pagination - Find products by page
    public void testFindByNomProduitContains() {
        Page<Produit> prods = produitService.getAllProduitsParPage(0, 2);
        System.out.println("Taille page : " + prods.getSize());
        System.out.println("Total éléments : " + prods.getTotalElements());
        System.out.println("Total pages : " + prods.getTotalPages());
        prods.getContent().forEach(p -> {
            System.out.println(p.toString());
        });
    }
}
