package com.myshop.myshop_backend.entity;

public enum OrderStatus {
    PENDING,     // Buyurtma yaratilgan, lekin hali tasdiqlanmagan
    CONFIRMED,   // Admin yoki system tomonidan tasdiqlangan
    SHIPPED,     // Yetkazib berishga yuborilgan
    DELIVERED,   // Yetkazib berilgan
    CANCELLED    // Bekor qilingan
}
