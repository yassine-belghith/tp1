package com.anis.produits.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anis.produits.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit , Long>{

}
