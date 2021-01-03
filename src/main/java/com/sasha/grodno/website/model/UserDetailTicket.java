package com.sasha.grodno.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDetailTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "passenger_first_name")
    private String passengerFirstName;

    @Column(name = "passenger_last_name")
    private String passengerLastName;

    @Column(name = "passport_id")
    private String passportId;

    @ManyToOne
    @JoinColumn(name = "id_user_info")
    private UserInfo idUserInfo;

    @OneToOne(mappedBy = "userDetailTicket")
    private Ticket ticket;

}
