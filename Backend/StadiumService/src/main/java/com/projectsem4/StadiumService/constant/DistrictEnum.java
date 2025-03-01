package com.projectsem4.StadiumService.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DistrictEnum {

    BA_DINH(1, "Giờ bình thường"),
    h(2, "Giờ cao điểm");

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
