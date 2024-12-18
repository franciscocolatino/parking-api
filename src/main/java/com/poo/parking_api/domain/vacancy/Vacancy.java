package com.poo.parking_api.domain.vacancy;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "vacancies")
@Entity(name = "vacancy")
@Setter
@Getter
@NoArgsConstructor
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private VacancyType vacancyType;
    private PriorityType priorityType;
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_id")  // Nome da coluna que faz a referência para Parking
    private Parking parking;  // Referência ao estacionamento

    // Relacionamento com Ticket
    @OneToMany(mappedBy = "vacancy", cascade = CascadeType.REMOVE)  // mappedBy faz referência ao atributo 'vacancy' em Ticket
    private List<Ticket> tickets;

}
