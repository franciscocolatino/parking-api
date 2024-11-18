package com.poo.parking_api.domain.ticket;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.vacancy.PriorityType;
import com.poo.parking_api.domain.vacancy.Vacancy;
import com.poo.parking_api.domain.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Table(name = "tickets")
@Entity(name = "ticket")
@Getter
@Setter
@EntityListeners(TicketEntityListener.class)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String code;
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
    @Transient
    private PriorityType priorityType;


    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(double paymentTotal) {
        this.paymentTotal = (float) paymentTotal;
    }

    public double calcularValor() {
        if (dateStart != null && dateEnd != null) {
            long tempoEstacionado = TimeUnit.MILLISECONDS.toHours(java.sql.Timestamp.valueOf(dateEnd).getTime() - java.sql.Timestamp.valueOf(dateStart).getTime());
            return tempoEstacionado * 10.0;
        }
        return 0.0;
    }

    public void finalizarPagamento(double valorPago) {
        this.paymentTotal = (float) (float) valorPago;
        this.status = TicketStatus.FINALIZADO;
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