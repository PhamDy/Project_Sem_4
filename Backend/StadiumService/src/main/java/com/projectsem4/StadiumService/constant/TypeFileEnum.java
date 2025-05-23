package com.projectsem4.StadiumService.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum TypeFileEnum {

    TYPE_FILE_1(1, "Khu vực"),
    TYPE_FILE_2(2, "Sân"),
    TYPE_FILE_3(2, "Sản phẩm");

    TypeFileEnum(int key, String description) {
        this.key = key;
        this.description = description;
    }

    private int key;
    private String description;

}
