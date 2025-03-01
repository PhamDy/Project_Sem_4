package com.projectsem4.StadiumService.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
