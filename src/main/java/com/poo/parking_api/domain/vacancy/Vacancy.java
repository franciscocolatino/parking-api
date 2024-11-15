package com.poo.parking_api.domain.vacancy;

import com.poo.parking_api.domain.parking.Parking;
import jakarta.persistence.*;

@Table(name = "vacancies")
@Entity(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private VacancyType vacancyType;
    private PriorityType priorityType;

    @ManyToOne  // Relacionamento "muitas vagas para um estacionamento"
    @JoinColumn(name = "parking_id", nullable = false)  // Definindo a chave estrangeira
    private Parking parking;
}
