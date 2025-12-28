package project.demo.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "readings")
public class Reading {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;
    
    @Column(name = "kind_of_meter")
    private String kindOfMeter;
    
    private String comment;
    
    @Column(name = "meter_id")
    private String meterId;
    
    @Column(name = "meter_count")
    private Double meterCount;
    
    private boolean substitute;
    
    @Column(name = "date_of_reading")
    private LocalDate dateOfReading;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
 
    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getKindOfMeter() {
        return kindOfMeter;
    }

    public void setKindOfMeter(String kindOfMeter) {
        this.kindOfMeter = kindOfMeter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public Double getMeterCount() {
        return meterCount;
    }

    public void setMeterCount(Double meterCount) {
        this.meterCount = meterCount;
    }

    public boolean isSubstitute() {
        return substitute;
    }

    public void setSubstitute(boolean substitute) {
        this.substitute = substitute;
    }

    public LocalDate getDateOfReading() {
        return dateOfReading;
    }

    public void setDateOfReading(LocalDate dateOfReading) {
        this.dateOfReading = dateOfReading;
    }


    
}
