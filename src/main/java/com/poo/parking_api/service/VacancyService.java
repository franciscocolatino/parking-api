package com.poo.parking_api.service;

<<<<<<< HEAD
import java.time.LocalDateTime;

public class VacancyService {

    public boolean isVacancyAvailable(int vacancyId, LocalDateTime startDate, LocalDateTime endDate) {
        return true;
    }

    public void updateVacancyStatus(int vacancyId, String status) {
=======
import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.vacancy.PriorityType;
import com.poo.parking_api.domain.vacancy.Vacancy;
import com.poo.parking_api.domain.vacancy.VacancyType;
import com.poo.parking_api.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        generateVacanciesForType(parking, vacancies, carCapacity, VacancyType.CAR, Parking.PRIORITY_PERCENTAGE);
        generateVacanciesForType(parking, vacancies, motocycleCapacity, VacancyType.MOTORCYCLE, Parking.PRIORITY_PERCENTAGE);
        generateVacanciesForType(parking, vacancies, bicycleCapacity, VacancyType.BICYCLE, Parking.PRIORITY_PERCENTAGE);
        generateVacanciesForType(parking, vacancies, truckCapacity, VacancyType.TRUCK, Parking.PRIORITY_PERCENTAGE);

        vacancyRepository.saveAll(vacancies);
    }

    private void generateVacanciesForType(Parking parking, List<Vacancy> vacancies, int capacity, VacancyType vacancyType, double priorityPercentage) {

        int priorityCount = (int) Math.ceil(capacity * priorityPercentage); // Vagas prioritárias
        int generalCount = capacity - priorityCount; // Vagas gerais

        PriorityType[] priorityTypes = PriorityType.values();

        for (int i = 0; i < priorityCount; i++) {
            Vacancy vacancy = new Vacancy();
            vacancy.setParking(parking);
            vacancy.setVacancyType(vacancyType);  // Tipo de vaga (Carro, Moto, etc)
            vacancy.setPriorityType(priorityTypes[i % priorityTypes.length]);  // Alterna entre os tipos de prioridade
            vacancies.add(vacancy);
        }

        // Criar vagas gerais
        for (int i = 0; i < generalCount; i++) {
            Vacancy vacancy = new Vacancy();
            vacancy.setParking(parking);
            vacancy.setVacancyType(vacancyType);
            vacancy.setPriorityType(PriorityType.GENERAL);
            vacancies.add(vacancy);
        }
>>>>>>> 2a97ff39c04d32c077ca7d0c921e79f9602e8bf2
    }
}
