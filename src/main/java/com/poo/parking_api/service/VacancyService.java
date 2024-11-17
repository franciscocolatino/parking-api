package com.poo.parking_api.service;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.vacancy.PriorityType;
import com.poo.parking_api.domain.vacancy.Vacancy;
import com.poo.parking_api.domain.vacancy.VacancyType;
import com.poo.parking_api.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    public void createVacancies(Parking parking) {
        int carCapacity = parking.getCarCapacity();
        int motocycleCapacity = parking.getMotocycleCapacity();
        int bicycleCapacity = parking.getBicycleCapacity();
        int truckCapacity = parking.getTruckCapacity();

        List<Vacancy> vacancies = new ArrayList<>();

        generateVacanciesForType(parking, vacancies, carCapacity, VacancyType.CARRO, Parking.PRIORITY_PERCENTAGE);
        generateVacanciesForType(parking, vacancies, motocycleCapacity, VacancyType.MOTO, Parking.PRIORITY_PERCENTAGE);
        generateVacanciesForType(parking, vacancies, bicycleCapacity, VacancyType.BICICLETA, Parking.PRIORITY_PERCENTAGE);
        generateVacanciesForType(parking, vacancies, truckCapacity, VacancyType.CAMINHÃO, Parking.PRIORITY_PERCENTAGE);

        vacancyRepository.saveAll(vacancies);
    }

    private void generateVacanciesForType(Parking parking, List<Vacancy> vacancies, int capacity, VacancyType vacancyType, double priorityPercentage) {

        int priorityCount = (int) Math.ceil(capacity * priorityPercentage); // Vagas prioritárias
        int generalCount = capacity - priorityCount; // Vagas gerais

        PriorityType[] relevantPriorityTypes = Arrays.stream(PriorityType.values())
                .filter(priority -> priority != PriorityType.GERAL)
                .toArray(PriorityType[]::new);

        for (int i = 0; i < priorityCount; i++) {
            Vacancy vacancy = new Vacancy();
            PriorityType priorityType = relevantPriorityTypes[i % relevantPriorityTypes.length]; // Alterna entre os tipos de prioridade

            vacancy.setParking(parking);
            vacancy.setVacancyType(vacancyType);
            vacancy.setCode(vacancyType.toString().substring(0, 1) + "-" + (i + 1) + "-" + priorityType.toString().substring(0, 1));
            vacancy.setPriorityType(priorityType);
            vacancies.add(vacancy);
        }

        // Criar vagas gerais
        for (int i = 0; i < generalCount; i++) {
            Vacancy vacancy = new Vacancy();
            vacancy.setParking(parking);
            vacancy.setVacancyType(vacancyType);
            vacancy.setCode(vacancyType.toString().substring(0, 1) + "-" + (i + 1) + "-" + PriorityType.GERAL);
            vacancy.setPriorityType(PriorityType.GERAL);
            vacancies.add(vacancy);
        }
    }
}
