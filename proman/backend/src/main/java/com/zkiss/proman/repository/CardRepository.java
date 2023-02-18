package com.zkiss.proman.repository;

import com.zkiss.proman.modal.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card getCardById(Long id);
}