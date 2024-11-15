package com.poo.parking_api.domain.vacancy;

import com.poo.parking_api.domain.parking.Parking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_id")  // Nome da coluna que faz a referência para Parking
    private Parking parking;  // Referência ao estacionamento
}
