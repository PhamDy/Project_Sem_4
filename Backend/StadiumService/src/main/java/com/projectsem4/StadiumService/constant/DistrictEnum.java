package com.projectsem4.StadiumService.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DistrictEnum {

    BA_DINH(1, "Quận Ba Đình"),
    HOAM_KIEM(2, "Quận Hoàn kiếm"),

    ;

    private int key;
    private String value;

    public static String fromMultiplier(int key) {
        for (DistrictEnum type : DistrictEnum.values()) {
            if (type.getKey() == key) {
                return type.getValue();
            }
        }
        throw new IllegalArgumentException("Không tìm thấy loại khung giờ với key: " + key);
    }

}
