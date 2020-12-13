package com.sasha.grodno.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String passengerFirstName;

    @Column
    private String passengerLastName;

    @Column
    private String passportId;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserInfo idUser;

    @OneToOne(mappedBy = "userDetail")
    private Ticket ticket;

}
