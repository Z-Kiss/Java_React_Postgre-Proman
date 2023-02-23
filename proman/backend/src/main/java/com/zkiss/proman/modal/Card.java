package com.zkiss.proman.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zkiss.proman.modal.DTO.cardDTO.CardCreateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CARD_ID")
    private Long id;

    private String title;

    private String cardDescription;

    private Integer cardOrder;

    private String bgColor;

    private String textColor;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "COLUMN_ID")
    @JsonIgnore
    private BoardColumn boardColumn;

    public Card(CardCreateRequest createRequest,BoardColumn boardColumn) {
        this.boardColumn = boardColumn;
        this.cardOrder = boardColumn.getCards().size();
        this.title = createRequest.getTitle();
        this.cardDescription = createRequest.getCardDescription();
        this.bgColor = createRequest.getColor();
        this.textColor = createRequest.getTextColor();
    }

    public void update(Card updatedCard) {
        if(updatedCard.getCardDescription() != null){this.setCardDescription(updatedCard.getCardDescription());}
        if(updatedCard.getTitle() != null){this.setTitle(updatedCard.getTitle());}
        if(updatedCard.getCardOrder() != null){this.setCardOrder(updatedCard.getCardOrder());}
        if(updatedCard.getBgColor() != null){this.setBgColor(updatedCard.getBgColor());}
        if(updatedCard.getTextColor() != null){this.setTextColor(updatedCard.getTextColor());}
        if(updatedCard.getBoardColumn() != null){this.setBoardColumn(updatedCard.getBoardColumn());}
    }
}
