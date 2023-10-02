package com.nielseniq.personalized.data.service.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "shopper")
@AllArgsConstructor
@NoArgsConstructor
public class Shopper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopper_seq_gen")
    @SequenceGenerator(name = "shopper_seq_gen", sequenceName = "shopper_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "shopperId", unique = true, nullable = false)
    private String shopperId;

}

