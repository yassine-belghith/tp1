package com.anis.produits;



import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.anis.produits.entities.Produit;
import com.anis.produits.repo.ProduitRepository;

@SpringBootTest
class ProduitsApplicationTests {
	@Autowired
	private ProduitRepository produitRepository;

	@Test
	public void testCreateProduit() {
	Produit prod = new Produit("PC Dell",2200.500,new Date());
	produitRepository.save(prod);
	}
	
@Test
	public void testFindProduit()
	{
	Produit p = produitRepository.findById(1L).get();
	System.out.println(p);
	}
	//test pour update PrixProduit
@Test
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

//test pour delete
	@Test
	public void testDeleteProduit()
	{
	produitRepository.deleteById(1L);;
	}
	
	
	@Test
	public void testListerTousProduits()
	{
	List<Produit> prods = produitRepository.findAll();
	for (Produit p : prods)
	{
	System.out.println(p);
	}
	
	}
	
	
	
}
