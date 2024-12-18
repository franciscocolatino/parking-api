package com.poo.parking_api.domain.parking;

import com.poo.parking_api.domain.vacancy.Vacancy;
import jakarta.persistence.*;

import com.poo.parking_api.domain.ticket.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "parkings")
@Entity(name = "parking")
@Getter
@Setter
@EntityListeners(ParkingEntityListener.class)
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String address;
    private int totalCapacity;
    private int vacanciesAvailable;
    public static final double PRIORITY_PERCENTAGE = 0.2;

    @Transient
    private int carCapacity;
    @Transient
    private int motocycleCapacity;
    @Transient
    private int bicycleCapacity;
    @Transient
    private int truckCapacity;


    @OneToMany(mappedBy = "parking", cascade = CascadeType.REMOVE)
    private List<Vacancy> vacancies;


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public void setVacanciesAvailable(int vacanciesAvailable) {
        this.vacanciesAvailable = vacanciesAvailable;
    }

    //public List<Ticket> getTickets() {
    //    return tickets;
    //}

    //public void setTickets(List<Ticket> tickets) {
    //    this.tickets = tickets;
    //}
}