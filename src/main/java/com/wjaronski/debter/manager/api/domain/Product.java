package com.wjaronski.debter.manager.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Wojciech Jaronski
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "products")
public class Product {
    @Id
    private Long productId;
    private String name;
    @NotNull
    private Double price;
    private String creditor;
    private String debtor;
    private Boolean everyonePays;

    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill;

}
