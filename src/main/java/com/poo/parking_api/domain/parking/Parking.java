package com.poo.parking_api.domain.parking;

import jakarta.persistence.*;

import com.poo.parking_api.domain.ticket.Ticket;

import java.util.List;

public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private int totalCapacity;

    @OneToMany(mappedBy = "vacancy.parking")
    private List<Ticket> tickets;

    public int getVacanciesAvailable() {
        long ticketsNotDone = tickets.stream()
                .filter(ticket -> !"done".equals(ticket.getStatus()))
                .count();
        return totalCapacity - (int) ticketsNotDone;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}