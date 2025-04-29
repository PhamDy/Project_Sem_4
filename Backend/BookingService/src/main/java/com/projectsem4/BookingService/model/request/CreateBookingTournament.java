package com.projectsem4.BookingService.model.request;

import com.projectsem4.BookingService.entity.BookingTournamentDetailTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingTournament {
    private Long id;        //ko cần truyền
    private String registerName;
    private String tournamentName;
    private Long numberTeam;
    private Long numberMatch;
    private LocalDate startDay;
    private LocalDate endDay;
    private String email;
    private String phoneNumber;
    private Boolean referee;
    private String banner;
    private Boolean photo;
    private Long jersey;
    private Boolean water;
    private Boolean speaker;
    private Boolean mc;
    private Long quantity;
    private Long price;
    private Boolean opening;
    private Long bookingId;     //ko cần truyền
    private List<Long> timeFrames;
    private Long fieldIds;
    List<BookingTournamentDetailTime> bookingTimeFrames;    //ko cần truyền
    private String url; //ko cần truyền
}
