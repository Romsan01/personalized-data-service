package com.nielseniq.personalized.data.service.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "shelf")
@AllArgsConstructor
@NoArgsConstructor
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shelf_seq_gen")
    @SequenceGenerator(name = "shelf_seq_gen", sequenceName = "shelf_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopperId", nullable = false)
    private Shopper shopper;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @Column(name = "relevancyScore", nullable = false)
    private Double relevancyScore;

}
