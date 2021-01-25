package com.sasha.grodno.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDetailTicket {

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

    @ManyToOne
    @JoinColumn(name = "id_user_info")
    private UserInfo idUserInfo;

    @OneToOne(mappedBy = "userDetailTicket")
    private Ticket ticket;

}
