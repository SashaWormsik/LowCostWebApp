package com.sasha.grodno.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "passenger_first_name")
    @NotNull
    @Size(min = 2, max = 20)
    private String passengerFirstName;

    @Column(name = "passenger_last_name")
    @NotNull
    @Size(min = 2, max = 20)
    private String passengerLastName;

    @Column(name = "passport_id")
    @NotNull
    private String passportId;

    @Column(name = "luggage")
    @NotNull
    private Boolean luggage;

    @Column(name = "priority_registration")
    @NotNull
    private Boolean priorityRegistration;

    @Column(name = "priority_boarding")
    @NotNull
    private Boolean priorityBoarding;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;


    @ManyToOne
    @JoinColumn(name = "id_user_info")
    private UserInfo idUserInfo;

}
