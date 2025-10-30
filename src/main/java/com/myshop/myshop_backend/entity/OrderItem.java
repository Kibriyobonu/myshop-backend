package com.myshop.myshop_backend.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Har bir order item faqat bitta orderga tegishli
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Order item faqat bitta productga tegishli
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;  // nechta mahsulot

    @Column(nullable = false)
    private BigDecimal price; // product narxi (buyurtma vaqtida)
}
