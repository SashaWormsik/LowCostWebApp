package com.sasha.grodno.website.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotNull
    @Positive
    @Min(100) @Max(999)
   // @Pattern(regexp = "[0-9]+", message = "There should only be numbers")
    private Integer number;

    @Column(name = "model", nullable = false)
    @NotNull
    @Size(min = 2, max = 20)
    private String model;

    @Column(name = "number_of_seats", nullable = false)
    @NotNull
    @Positive
    @Min(1) @Max(300)
    //@Pattern(regexp = "[0-9]+", message = "There should only be numbers")
    private Integer numberOfSeats;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    @Override
    public String toString() {
        return (model + " flight № " + number.toString());
    }
}
