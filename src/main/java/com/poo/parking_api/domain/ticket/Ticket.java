package com.poo.parking_api.domain.ticket;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Ticket {
    @Id
    private Long id;  // Alterado para Long
    private String plateCar;
    private int vacancyId;
    private int employeeId;
    private float paymentTotal;
    private String status; // Status do ticket: "OPEN", "CLOSED"
    private Date dateStart;
    private Date dateEnd;

    public Ticket(Long id, String plateCar, int vacancyId, int employeeId) {
        this.id = id;
        this.plateCar = plateCar;
        this.vacancyId = vacancyId;
        this.employeeId = employeeId;
        this.paymentTotal = 0.0f;
        this.status = "OPEN";
        this.dateStart = new Date();
    }

    public Ticket() {

    }

    public void setPaymentTotal(float paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setId(Long id) {  // Alterado para Long
        this.id = id;
    }

    public Long getId() {  // Alterado para Long
        return id;
    }

    public float getPaymentTotal() {
        return paymentTotal;
    }
}
