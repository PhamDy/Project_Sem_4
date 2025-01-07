package com.projectsem4.common_service.dto.constant;

public class Constant {
    public static class PaymentStatus {
        public static Integer pending = 1;
        public static Integer completed = 2;
        public static Integer fail = 3;
    }

    public static class OrderStatus {
        public static Integer pending = 1;
        public static Integer paid = 2;
        public static Integer completed = 3;
        public static Integer fail = 4;
        public static Integer refund = 5;
    }
}
