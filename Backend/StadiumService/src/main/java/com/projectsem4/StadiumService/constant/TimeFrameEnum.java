package com.projectsem4.StadiumService.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TimeFrameEnum {

    TF_1(1, "10h00 - 11h30"),
    TF_2(2, "15h00 - 16h30"),
    TF_3(3, "15h00 - 16h30"),
    TF_4(4, "17h00 - 18h30"),
    TF_5(5, "19h00 - 20h30"),
    TF_6(6, "21h00 - 22h30");

    private int key;
    private String value;

    public static String fromValue(int key) {
        for (TimeFrameEnum type : TimeFrameEnum.values()) {
            if (type.getKey() == key) {
                return type.getValue();
            }
        }
        throw new IllegalArgumentException("Không tìm thấy loại khung giờ với key: " + key);
    }

}
