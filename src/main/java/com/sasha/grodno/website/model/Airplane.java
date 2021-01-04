package com.sasha.grodno.website.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "number_of_seats", nullable = false)
    private Integer numberOfSeats;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;


    @Override
    public String toString() {
        return (model + " flight № " + number.toString());
    }
}
