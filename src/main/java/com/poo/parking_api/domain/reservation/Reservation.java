package com.poo.parking_api.domain.reservation;

import com.poo.parking_api.domain.ticket.Ticket;
import jakarta.persistence.*;

@Table(name = "reservations")
@Entity(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String customerName;
    private String plateCar;

    @Column(name = "vacancy_id")
    private int vacancyId;

    @Column(name = "employee_id")
    private int employeeId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Reservation() {
    }

    public Reservation(String customerName, String plateCar, int vacancyId, int employeeId) {
        this.customerName = customerName;
        this.plateCar = plateCar;
        this.vacancyId = vacancyId;
        this.employeeId = employeeId;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPlateCar() {
        return plateCar;
    }

    public void setPlateCar(String plateCar) {
        this.plateCar = plateCar;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
