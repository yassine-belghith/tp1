package com.anis.produits.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.anis.produits.entities.Produit;


public interface ProduitService {
	
	Produit saveProduit(Produit p);
	Produit updateProduit(Produit p);
	void deleteProduit(Produit p);
	void deleteProduitById(Long id);	
	Produit getProduit(Long id);
	List<Produit> getAllProduits();

	//ADDED CODE Q4- TP3
    Page<Produit> getAllProduitsParPage(int page, int size);

}
