package com.sasha.grodno.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_detail_id")
    private UserDetailTicket userDetailTicket;

}
