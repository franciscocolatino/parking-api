package com.poo.parking_api.domain.parking;

import jakarta.persistence.*;

import com.poo.parking_api.domain.ticket.Ticket;

import java.util.List;

@Table(name = "parkings")
@Entity(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String address;
    private int totalCapacity;

    //@OneToMany(mappedBy = "vacancies.parking")
    //private List<Ticket> tickets;

    //public int getVacanciesAvailable() {
    //    long ticketsNotDone = tickets.stream()
    //            .filter(ticket -> !"done".equals(ticket.getStatus()))
    //            .count();
    //    return totalCapacity - (int) ticketsNotDone;
    //}

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    //public List<Ticket> getTickets() {
    //    return tickets;
    //}

    //public void setTickets(List<Ticket> tickets) {
    //    this.tickets = tickets;
    //}
}