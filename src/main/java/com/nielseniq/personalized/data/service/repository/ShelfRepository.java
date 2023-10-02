package com.nielseniq.personalized.data.service.repository;

import com.nielseniq.personalized.data.service.repository.entity.Shelf;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {

    @Query("select s from Shelf s join s.shopper sh join s.product p " +
            "where sh.shopperId = :shopperId " +
            "and (:category is null or p.category = :category) " +
            "and (:brand is null or p.brand = :brand)")
    List<Shelf> findByShopperIdAndProductCategoryAndProductBrand(
            @Param("shopperId") String shopperId,
            @Param("category") String category,
            @Param("brand") String brand,
            Pageable pageable);

    @Query("select s from Shelf s join s.product p where p.productId = :productId")
    List<Shelf> findByProductId(@Param("productId") String productId, Pageable pageable);
}
