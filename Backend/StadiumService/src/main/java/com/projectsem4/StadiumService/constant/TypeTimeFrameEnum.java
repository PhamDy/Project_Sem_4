package com.projectsem4.StadiumService.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
