package com.zkiss.proman.modal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CARD_ID")
    private Long id;

    private String cardTitle;

    private String cardDescription;

    private int cardOrder;

    private String color;

    @ManyToOne
    @JoinColumn(name = "COLUMN_ID")
    private BoardColumn boardColumn;

}