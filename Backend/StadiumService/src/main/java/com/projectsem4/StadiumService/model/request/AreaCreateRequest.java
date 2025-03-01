package com.projectsem4.StadiumService.model.request;

import com.projectsem4.StadiumService.entity.Area;
import com.projectsem4.StadiumService.entity.FileDb;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaCreateRequest extends Area {
    private List<FileDb> fileList;
}
