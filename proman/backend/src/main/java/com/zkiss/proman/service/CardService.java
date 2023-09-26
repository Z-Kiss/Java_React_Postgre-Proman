package com.zkiss.proman.service;

import com.zkiss.proman.model.BoardColumn;
import com.zkiss.proman.model.Card;
import com.zkiss.proman.model.DTO.cardDTO.CardCreateRequest;
import com.zkiss.proman.model.DTO.cardDTO.CardBoardColumnUpdateRequest;
import com.zkiss.proman.model.DTO.cardDTO.CreateCardResponse;
import com.zkiss.proman.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final BoardColumnService boardColumnService;
    private final CardRepository cardRepository;

    @Transactional
    public CreateCardResponse registerCard(CardCreateRequest createRequest) {
        BoardColumn boardColumn = boardColumnService.getBoardColumnById(createRequest.getBoardColumnId());
        Card card = new Card(createRequest, boardColumn);
        cardRepository.save(card);
        boardColumn.addCard(card);
        boardColumnService.updateBoardColumn(boardColumn);
        return new CreateCardResponse(boardColumn.getId(), card);
    }
    @Transactional
    public void updateCard(Card updatedCard) {
        Card card = cardRepository.findById(updatedCard.getId()).orElseThrow(EntityNotFoundException::new);
        card.update(updatedCard);
        cardRepository.save(card);
    }
    @Transactional
    public void updateCardsBoardColumn(CardBoardColumnUpdateRequest updateRequest){
        BoardColumn boardColumn = boardColumnService.getBoardColumnById(updateRequest.getBoardColumnId());
        Card updatedCard = updateRequest.getCard();
        updatedCard.setBoardColumn(boardColumn);
        Card card = cardRepository.findById(updatedCard.getId()).orElseThrow(EntityNotFoundException::new);
        card.update(updatedCard);
        cardRepository.save(card);
    }
    @Transactional
    public Integer deleteCard(Long id) {
       return cardRepository.deleteCardById(id);
    }
}
