package ru.lalibrairiestore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "postcard")
public class Postcard extends Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
}
