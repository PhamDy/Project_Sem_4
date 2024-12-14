package com.projectsem4.common_service.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDTO <T> {
    List<T> listData;
    Long size;
    Long page;
    Long totalPages;
    Long totalElements;
}
