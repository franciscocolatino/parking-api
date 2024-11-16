package com.poo.parking_api.domain.ticket;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.vacancy.Vacancy;
import com.poo.parking_api.domain.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "tickets")
@Entity(name = "ticket")
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    //private String plateCar;
    private TicketStatus status;
    private float paymentTotal;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


    @Transient
    private Parking parking;


    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(float paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

//    public Vacancy getVacancy() {
//        return vacancy;
//    }
//
//    public void setVacancy(Vacancy vacancy) {
//        this.vacancy = vacancy;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
}