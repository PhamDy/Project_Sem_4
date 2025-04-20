import {Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingServicesService } from '../../../services/booking-services.service';
import * as L from 'leaflet';
import { StadiumService } from '../../../services/stadium-service.service';
import { weekSchedule } from './schedule-data';
import * as AOS from 'aos';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {AreaServicesService} from '../../../services/area-services.service';

@Component({
  selector: 'app-booking-tournament',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './booking-tournament.component.html',
  styleUrl: './booking-tournament.component.css',
})
export class BookingTournamentComponent implements OnInit{
  bookingType: any;
  searchQuery: string = '';
  dataStadium: any[] = [];
  dataFields: any = null;
  dataStadiumSearch: any[] = [];
  findStadiumByKeyWord: any[] = [];
  latitude: number | null = null;
  longitude: number | null = null;
  latitudeMap: number | null = null;
  longitudeMap: number | null = null;
  img: any = 'https://imgur.com/JMP9KcJ.png';
  isPopup = false;

  weekSchedule = weekSchedule;
  selectedFields: any[] = [];

  showDetailPopup: boolean = false;
  stadiumDetail: any = null;

  allFieldArea: any = null;

  showMap: boolean = false;
  map: any;
  marker: any;

  showLongTermPopup = false;
  showTournamentPopup = false;

  // Thông tin đặt giải đấu

  tournament = {
    registrantName: '',
    name: '',
    teams: 0,
    matches: 0,
    startDate: '',
    endDate: '',
    email: '',
    phone: '',
    shirtCount: 1
  };

  longTerm = {
    name: '',
    phone: '',
    startDate: '',
    endDate: '',
    timeSlot: '',
    days: {} as Record<string, boolean>,
    frequency: 'weekly',
  };

  timeSlots = [
    '6:00 - 7:30',
    '7:30 - 9:00',
    '9:00 - 10:30',
    '16:00 - 17:30',
    '17:30 - 19:00',
    '19:00 - 20:30',
    '20:30 - 22:00',
  ];

  weekDays = ['Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7', 'Chủ Nhật'];

  scheduleData: any;
  timeFramesMap: { [key: string]: string } = {
    '1': '6:00 - 7:30',
    '2': '7:30 - 9:00',
    '3': '9:00 - 10:30',
    '4': '16:00 - 17:30',
    '5': '17:30 - 19:00',
    '6': '19:00 - 20:30',
    '7': '20:30 - 22:00',
  };

  itemsPerPage: number = 8;
  currentPage: number = 1;

  get paginatedStadiums(): any[] {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.dataStadium.slice(startIndex, endIndex);
  }

  get totalPages(): number {
    return Math.ceil(this.dataStadium.length / this.itemsPerPage);
  }

  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }
  getAllTimeFrames(): number[] {
    return Object.keys(this.timeFramesMap).map((k) => +k);
  }

  getScheduleForTimeFrame(frame: any, timeFrameId: number) {
    return frame.fieldSchedules.find((s: any) => s.timeFrame === timeFrameId);
  }

  choose(data: any, date: string) {
    const index = this.selectedFields.findIndex(
      (field) => field.timeFrame === data.timeFrame && field.date === date
    );

    const item = {
      ...data,
      date: date,
      quantity: 1,
      availableQuantity: data.quantity,
      amount: data.price
    };
    if (index === -1) {
      this.selectedFields.push(item);
    } else {
      this.selectedFields.splice(index, 1);
    }
  }

  isSelected(data: any, date: string): boolean {
    return this.selectedFields.some(
      (field) => field.timeFrame === data.timeFrame && field.date === date
    );
  }

  onQuantityChange(field: any): void {
    // Đảm bảo quantity luôn trong khoảng 1 đến availableQuantity
    if (field.quantity < 1) {
      field.quantity = 1;
    } else if (field.quantity > field.availableQuantity) {
      field.quantity = field.availableQuantity;
    }

    // Cập nhật lại amount
    field.amount = field.quantity * field.price;
  }

  openLongTermBooking() {
    this.showLongTermPopup = true;
  }

  openTournamentBooking() {
    this.showTournamentPopup = true;
  }

  closePopupLong() {
    this.showLongTermPopup = false;
    this.showTournamentPopup = false;
  }

  confirmLongTerm() {
    const selectedDays = Object.keys(this.longTerm.days)
      .filter((day) => this.longTerm.days[day])
      .join(', '); // Lọc các ngày đã chọn

    // Hiển thị thông tin trong alert
    const message = `
      Tên người đặt sân: ${this.longTerm.name}
      Số điện thoại: ${this.longTerm.phone}
      Ngày bắt đầu: ${this.longTerm.startDate}
      Ngày kết thúc: ${this.longTerm.endDate}
      Khung giờ đá: ${this.longTerm.timeSlot}
      Ngày đá trong tuần: ${selectedDays}
    `;
    alert(message);
    this.closePopupLong();
    this.closePopup();
  }

  confirmTournament() {
    const message = `
      Tên giải đấu: ${this.tournament.name}
      Số đội tham gia: ${this.tournament.teams}
      Số trận đấu dự kiến: ${this.tournament.matches}
      Ngày bắt đầu: ${this.tournament.startDate}
      Ngày kết thúc: ${this.tournament.endDate}
      Tên người đăng ký: ${this.tournament.registrantName}
      Email: ${this.tournament.email}
      Số điện thoại liên hệ: ${this.tournament.phone}

      Admin sẽ liên hệ đến bạn trong vòng 24h
    `;

    alert(message);
    this.closePopup();
  }

  bookingId: string | null = '';
  areaDetail: any = null;
  areaId: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingServicesService,
    private stadiumService: StadiumService,
    private router: Router,
    private areaServicesService: AreaServicesService,
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      this.areaId = params.get('id');
      if (this.areaId) {
        this.getAreaById(this.areaId);
      }
    });
    this.getAreaById(this.areaId);
    this.getFieldTypeByAreaId(1, 0);
    AOS.init({
      duration: 500,
      easing: 'ease-in-out',
      once: true,
    });
    console.log("id is",this.areaId);
  }

  openDate() {
    if (this.bookingType === 'single') {
      this.isPopup = !this.isPopup;
    }
    if (this.bookingType === 'longTerm') {
      this.showLongTermPopup = !this.showLongTermPopup;
      console.log('showLongTermPopup:', this.showLongTermPopup);
    }
    if (this.bookingType === 'tournament') {
      this.showTournamentPopup = !this.showTournamentPopup;
      console.log('showTournamentPopup:', this.showTournamentPopup);
    }
  }

  openPopup() {
    this.isPopup = true;
  }
  closePopup(): void {
    // Close the popup
    this.isPopup = false;
    this.showLongTermPopup = false; // or whatever variable controls your popup
    this.showTournamentPopup = false;
  }
  //
  // closePopup() {
  //   this.isPopup = false;
  //   this.showTournamentPopup = false;
  //   this.showLongTermPopup = false;
  // }

  getFieldsByAreaIds(areaId: any) {
    this.stadiumService.getFieldsByAreaId(areaId).subscribe(
      (res) => {
        this.stadiumDetail = res;
        console.log('Dâ ', res);
      },
      (error) => {
        console.error('Error fetching stadium data:', error);
      }
    );
  }

  getAreaById(id: any) {
    this.areaServicesService.getDetailById(id).subscribe((data) => {
      if (data) {
        this.areaDetail = data;
      }
    });
  }

  getAllStadiums(): void {
    this.stadiumService.getAllStadiumPageable().subscribe(
      (res) => {
        this.dataStadium = res;
      },
      (error) => {
        console.error('Error fetching stadium data:', error);
      }
    );
  }

  getBookingAreaById(id: any): void {
    this.bookingService.bookingGetAreaById(id).subscribe(
      (res) => {
        this.allFieldArea = res.fields;
        console.log('data is', this.allFieldArea);
      },
      (error) => {
        console.error('Error fetching stadium data:', error);
      }
    );
  }

  getFieldTypeByAreaId(id: number, index: number): void {
    this.bookingService.getfieldTypeByArea("1", index).subscribe(
      (res) => {
        this.scheduleData = res;
      },
      (error) => {
        console.error('Error fetching stadium data:', error);
      }
    );
  }


  viewDetail(stadiumId: number): void {
    this.stadiumDetail = this.dataStadium.find(
      (stadium) => stadium.areaId === stadiumId
    );
    console.log('detail area', this.stadiumDetail);
    this.showDetailPopup = true;
    console.log('Detail of Stadium:', this.stadiumDetail);
  }

  selectedStadiumIds: number[] = [];

  onCheckboxChange(stadiumId: number, event: Event) {
    const checked = (event.target as HTMLInputElement).checked;

    if (checked) {
      this.selectedStadiumIds.push(stadiumId);
    } else {
      this.selectedStadiumIds = this.selectedStadiumIds.filter(id => id !== stadiumId);
    }

    console.log('Danh sách sân được chọn:', this.selectedStadiumIds);
  }


  goToPayment() {
    this.router.navigate(['/payment'], {
      queryParams: { selectedFields: JSON.stringify(this.selectedFields) },
    });
  }
  protected readonly Number = Number;

  availableServices = [
    { id: 1, name: 'Thuê trọng tài' },
    { id: 2, name: 'Làm băng rôn / banner' },
    { id: 3, name: 'Chụp ảnh / quay video' },
    { id: 4, name: 'Thuê áo dự bị' },
    { id: 5, name: 'Mua nước uống' },
    { id: 6, name: 'Thuê loa / âm thanh' },
    { id: 7, name: 'Tổ chức MC / Bình luận viên' },
    { id: 8, name: 'Tổ chức lễ khai mạc / bế mạc' }
  ];

  selectedServices: number[] = [];

  toggleService(id: number, event: any): void {
    if (event.target.checked) {
      this.selectedServices.push(id);
    } else {
      this.selectedServices = this.selectedServices.filter(serviceId => serviceId !== id);
    }
  }
  showInput1 = false;
  showInput2 = false;
  showInput3 = false;
  showInput4 = false;
  showInput5 = false;
  showInput6 = false;
  showInput7 = false;
  showInput8 = false;
  showInputPoli = false;
  showInputTou = false;

  selectedDates: string[] = [];

  selectedFiles: File[] = [];

  onFilesSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input?.files) {
      this.selectedFiles = Array.from(input.files); // Lưu các file đã chọn
    }
  }

  pricePerMatch: {
    [key in 'referee' | 'photography' | 'water' | 'soundSystem' | 'mc']: number;
  } = {
    referee: 200000, // Thuê trọng tài
    photography: 100000, // Chụp ảnh / quay video
    water: 100000, // Mua nước lạnh
    soundSystem: 150000, // Thuê loa / âm thanh
    mc: 100000 // Thuê MC / Bình luận viên
  };

  // Hàm tính tổng số tiền cho từng dịch vụ
  calculateTotal(service: 'referee' | 'photography' | 'water' | 'soundSystem' | 'mc'): number {
    return this.tournament.matches * this.pricePerMatch[service];
  }

  pricePerShirt: number = 20000;


  days: string[] = ['2025-04-20', '2025-04-21', '2025-04-22', '2025-04-23']; // Ví dụ danh sách ngày

  onDateChange(index: number, event: any) {
    if (this.selectedDates.includes(event.target.value)) {
      // Nếu ngày này đã được chọn, giữ lại
    } else {
      // Nếu không có trong mảng, thêm ngày vào
      this.selectedDates.push(event.target.value);
    }
  }

  calculateShirtCost(): number {
    return this.pricePerShirt * this.tournament.shirtCount * this.tournament.matches;
  }

  isSelected2(index: number): boolean {
    return this.selectedDates.includes(this.days[index]);
  }

}
