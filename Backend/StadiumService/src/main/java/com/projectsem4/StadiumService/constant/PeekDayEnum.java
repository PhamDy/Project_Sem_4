package com.projectsem4.StadiumService.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PeekDayEnum {

    PD_1(1, "ngày thường", 1),
    PD_2(2,"ngày lễ", 1.5);

    private int key;
    private String description;
    private double value;

    public static double fromValue(int key) {
        for (PeekDayEnum type : PeekDayEnum.values()) {
            if (type.getKey() == key) {
                return type.getValue();
            }
        }
        throw new IllegalArgumentException("Không tìm thấy loại khung giờ với key: " + key);
    }

}
