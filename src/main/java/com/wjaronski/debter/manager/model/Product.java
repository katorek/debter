package com.wjaronski.debter.manager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Wojciech Jaronski
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    private Long productId;
    private String name;
    private Double price;
    private String creditor;
    private String debtor;
    private Boolean everyonePays;

    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill;

}
