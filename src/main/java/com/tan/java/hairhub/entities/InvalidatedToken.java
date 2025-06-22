package com.tan.java.hairhub.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvalidatedToken {

    @Id
    private String Id;

    private Date exipiredDate;
}
