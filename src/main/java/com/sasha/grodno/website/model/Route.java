package com.sasha.grodno.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "city_from")
    @Size(min = 2, max = 30)
    private String cityFrom;

    @Column(name = "city_to")
    @Size(min = 2, max = 30)
    private String cityTo;

    @Column(name = "price")
    @Positive
    private BigDecimal price;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;



    public String toString(){
        return (cityFrom + " - " + cityTo);
    }
}
