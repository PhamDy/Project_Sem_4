package com.projectsem4.common_service.dto.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;

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

    public static class TypeAccessory {
        public static Integer Pay = 1;
        public static Integer Rent = 2;
    }

    public static class TypeUpdate {
        public static Integer Add = 1;
        public static Integer Subtract = 2;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum PeekDayEnum {

        PD_1(1, "ngày thường", 1.0),
        PD_2(2,"ngày lễ", 1.5);

        Integer key;
        String description;
        Double value;

        public static Double fromValue(int key) {
            for (PeekDayEnum type : PeekDayEnum.values()) {
                if (type.getKey() == key) {
                    return type.getValue();
                }
            }
            throw new IllegalArgumentException("Không tìm thấy loại khung giờ với key: " + key);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum DistrictEnum {
        BA_DINH(1, "Quận Ba Đình"),
        HOAN_KIEM(2, "Quận Hoàn Kiếm"),
        TAY_HO(3, "Quận Tây Hồ"),
        LONG_BIEN(4, "Quận Long Biên"),
        CAU_GIAY(5, "Quận Cầu Giấy"),
        DONG_DA(6, "Quận Đống Đa"),
        HAI_BA_TRUNG(7, "Quận Hai Bà Trưng"),
        HOANG_MAI(8, "Quận Hoàng Mai"),
        THANH_XUAN(9, "Quận Thanh Xuân"),
        SOC_SON(16, "Huyện Sóc Sơn"),
        DONG_ANH(17, "Huyện Đông Anh"),
        GIA_LAM(18, "Huyện Gia Lâm"),
        NAM_TU_LIEM(19, "Quận Nam Từ Liêm"),
        THANH_TRI(20, "Huyện Thanh Trì"),
        BAC_TU_LIEM(21, "Quận Bắc Từ Liêm"),
        ME_LINH(250, "Huyện Mê Linh"),
        HA_DONG(268, "Quận Hà Đông"),
        SON_TAY(269, "Thị xã Sơn Tây"),
        BA_VI(271, "Huyện Ba Vì"),
        PHUC_THO(272, "Huyện Phúc Thọ"),
        DAN_PHUONG(273, "Huyện Đan Phượng"),
        HOAI_DUC(274, "Huyện Hoài Đức"),
        QUOC_OAI(275, "Huyện Quốc Oai"),
        THACH_THAT(276, "Huyện Thạch Thất"),
        CHUONG_MY(277, "Huyện Chương Mỹ"),
        THANH_OAI(278, "Huyện Thanh Oai"),
        THUONG_TIN(279, "Huyện Thường Tín"),
        PHU_XUYEN(280, "Huyện Phú Xuyên"),
        UNG_HOA(281, "Huyện Ứng Hòa"),
        MY_DUC(282, "Huyện Mỹ Đức");

        private final int key;
        private final String value;

        public static String fromValue(int key) {
            for (DistrictEnum type : DistrictEnum.values()) {
                if (type.getKey() == key) {
                    return type.getValue();
                }
            }
            throw new IllegalArgumentException("Không tìm thấy loại khung giờ với key: " + key);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum TimeFrameEnum {

        TF_1(1L, "10h00 - 11h30",1.0),
        TF_2(2L, "15h00 - 16h30",1.0),
        TF_3(3L, "15h00 - 16h30",1.0),
        TF_4(4L, "17h00 - 18h30",1.0),
        TF_5(5L, "19h00 - 20h30",1.0),
        TF_6(6L, "21h00 - 22h30",1.0);

        private Long key;
        private String value;
        private Double scale;

        public static String fromValue(int key) {
            for (TimeFrameEnum type : TimeFrameEnum.values()) {
                if (type.getKey() == key) {
                    return type.getValue();
                }
            }
            throw new IllegalArgumentException("Không tìm thấy loại khung giờ với key: " + key);
        }

        public static List<TimeFrameEnum> getAllTimeFrames() {
            return Arrays.asList(TF_1, TF_2, TF_3, TF_4, TF_5,TF_6);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum TypeTimeFrameEnum {

        NORMAL_TIME(1, "Giờ bình thường", 1),
        PEAK_TIME(2, "Giờ cao điểm", 1.5);

        private int key;
        private String description;
        private double multiplier;

        public static double fromMultiplier(int key) {
            for (TypeTimeFrameEnum type : TypeTimeFrameEnum.values()) {
                if (type.getKey() == key) {
                    return type.getMultiplier();
                }
            }
            throw new IllegalArgumentException("Không tìm thấy loại khung giờ với key: " + key);
        }

    }

    @Getter
    @AllArgsConstructor
    public enum  RoleEnum {

        USER(1, "User"),
        ADMIN(2, "Admin");

        private final Integer key;
        private final String values;

        public static RoleEnum fromKey(Integer key) {
            for (RoleEnum role : RoleEnum.values()) {
                if (role.getKey().equals(key)) {
                    return role;
                }
            }
            throw new IllegalArgumentException("Invalid role key: " + key);
        }

    }

}
