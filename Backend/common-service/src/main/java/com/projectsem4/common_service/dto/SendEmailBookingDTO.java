package com.projectsem4.common_service.dto;

import com.projectsem4.common_service.dto.entity.BookingDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailBookingDTO {
    private List<BookingDetail> bookingDetails;
    private UserInfor userInfor;
}
