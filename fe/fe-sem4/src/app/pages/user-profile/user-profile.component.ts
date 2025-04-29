import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AreaServicesService } from '../../services/area-services.service';
import { BookingServicesService } from '../../services/booking-services.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
  user: any = {};

  isLoading = true;

  bookings: any[] = [];
  visibleDetails: { [key: number]: boolean } = {};
  bookingDate: string = '';
  areaDetail: any;
  isDetailsVisible: boolean = false;
  img: any = 'https://imgur.com/JMP9KcJ.png';

  constructor(
    private route: ActivatedRoute,
    private areaService: AreaServicesService,
    private bookingServices: BookingServicesService
  ) {}

  ngOnInit() {
    this.getAllHistoryBooking();
    // const stored = localStorage.getItem('bookingDetails');
    // if (stored) {
    //   const parsed = JSON.parse(stored);
    //   this.bookingDate = parsed.date;
    //   this.bookingDetails = parsed.details;
    //   const listIdfield = this.bookingDetails.map(item => item.fieldId)
    //   const idArea = listIdfield[0];
    //   this.getAreaById(idArea);
    //   console.log(this.bookingDate);
    //   console.log('Ngày lưu:', this.bookingDate);
    //   console.log('Chi tiết booking:', this.bookingDetails);
    // } else {
    //   console.log('Không tìm thấy dữ liệu trong localStorage.');
    // }

    // Get user ID from route or use logged in user
    const userId = this.route.snapshot.paramMap.get('id') || 'current';
    // this.loadUserData(userId);
  }

  toggleDetails() {
    this.isDetailsVisible = !this.isDetailsVisible; // Lật lại trạng thái hiển thị
  }

  timeFrameMapping: { [key: number]: string } = {
    1: "8h00 - 9h30",
    2: "10h00 - 11h30",
    3: "15h00 - 16h30",
    4: "17h00 - 18h30",
    5: "19h00 - 20h30",
    6: "21h00 - 22h30"
  };
  

  // toggleDetails(bookingId: number) {
  //   this.visibleDetails[bookingId] = !this.visibleDetails[bookingId];
  // }

  selectedBooking: any = null;

  openPopup(booking: any) {
    this.selectedBooking = booking;
  }

  closePopup() {
    this.selectedBooking = null;
  }

  loadUserData(userId: string) {
    // this.isLoading = true;
    //   todo: load user data from service
  }
  isEditing = false;
  toggleEdit() {
    this.isEditing = !this.isEditing;
  }

  saveChanges() {}

  activeTab = 'info';

  changeTab(tab: string) {
    this.activeTab = tab;
  }

  getAreaById(id: string) {
    this.areaService.getDetailById(id).subscribe((data) => {
      if (data) {
        this.areaDetail = data;
        console.log('data', this.areaDetail);
      }
    });
  }

  getAllHistoryBooking(): void {
    this.bookingServices.getHistoryBooking().subscribe(
      (res) => {
        this.bookings = res;
      },
      (error) => {
        console.error('Error fetching stadium data:', error);
      }
    );
  }
}
