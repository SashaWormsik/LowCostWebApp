package com.sasha.grodno.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "departure")
    @NotNull
    private Date departure;

    @Column(name = "arrival")
    @NotNull
    private Date arrival;

    @Column(name = "places_available")
    private Integer placesAvailable;

    @Column(name = "price_schedule")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;


    @Override
    public String toString() {
        return "Schedule:" +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", placesAvailable=" + placesAvailable +
                ", airplane=" + airplane +
                ", route=" + route;
    }
}
