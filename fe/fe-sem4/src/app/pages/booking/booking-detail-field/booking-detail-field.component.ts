import {Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingServicesService } from '../../../services/booking-services.service';
import * as L from 'leaflet';
import { StadiumService } from '../../../services/stadium-service.service';
import { weekSchedule } from './schedule-data';
import * as AOS from 'aos';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { throwIfEmpty } from 'rxjs';

@Component({
  selector: 'app-booking-detail-field',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './booking-detail-field.component.html',
  styleUrl: './booking-detail-field.component.css',
})
export class BookingDetailFieldComponent implements OnInit{
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

  longTermForm!: FormGroup;

  daysInWeek = [
    { label: 'Thứ 2', value: 2 },
    { label: 'Thứ 3', value: 3 },
    { label: 'Thứ 4', value: 4 },
    { label: 'Thứ 5', value: 5 },
    { label: 'Thứ 6', value: 6 },
    { label: 'Thứ 7', value: 7 },
    { label: 'Chủ Nhật', value: 1 }
  ];

  timeFrames = [
    { label: '06:00 - 07:00', value: 1 },
    { label: '18:00 - 19:00', value: 2 },
    { label: '19:00 - 20:00', value: 3 }
  ];

  // Thông tin đặt giải đấu
  tournament = {
    name: '',
    teams: null,
    matches: null,
    startDate: '',
    endDate: '',
    registrantName: '',
    email: '',
    phone: '',
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
  currentPage: number = 0;

  get paginatedStadiums(): any[] {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.dataStadium.slice(startIndex, endIndex);
  }

  get totalPages(): number {
    return Math.ceil(this.dataStadium.length / this.itemsPerPage);
  }

  changePage(page: number): void {
    if (page >= 0 && this.bookingId) {
      this.currentPage = page;
      this.getFieldTypeByAreaId(this.bookingId, this.currentPage);
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

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private bookingService: BookingServicesService,
    private stadiumService: StadiumService,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      this.bookingId = params.get('id');
      if(this.bookingId) {
        this.getAllStadiums();
        this.getBookingAreaById(this.bookingId);
        this.getFieldsByAreaIds(this.bookingId);
        this.getFieldTypeByAreaId(this.bookingId, this.currentPage);
      }
    });
    AOS.init({
      duration: 500,
      easing: 'ease-in-out',
      once: true,
    });

    this.longTermForm = this.fb.group({
      month: [null, Validators.required],
      quantity: [null, [Validators.required, Validators.min(1)]],
      weekDay: [null, Validators.required],
      timeFrame: [{ value: null, disabled: true }, Validators.required]
    });

    this.handleEnableTimeFrame();
  }

  handleEnableTimeFrame() {
    this.longTermForm.valueChanges.subscribe(values => {
      const { month, quantity, weekDay } = values;
      const timeFrameControl = this.longTermForm.get('timeFrame');

      if (month && quantity && weekDay !== null && timeFrameControl?.disabled) {
        timeFrameControl.enable();
      } else if ((!month || !quantity || weekDay === null) && timeFrameControl?.enabled) {
        timeFrameControl.disable();
        timeFrameControl.reset();
      }
    });
  }

  onSubmit() {
    if (this.longTermForm.valid) {
      const payload = this.longTermForm.value;
      console.log('Sending payload:', payload);

      
    }
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

  currentIndex = 0;


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

  getFieldTypeByAreaId(id: string, index: number): void {
    this.bookingService.getfieldTypeByArea(id, index).subscribe(
      (res) => {
        this.scheduleData = res;
      },
      (error) => {
        console.error('Error fetching stadium data:', error);
      }
    );
  }

  openMapWithCoordinates(lat: number, lng: number): void {
    this.latitudeMap = lat;
    this.longitudeMap = lng;
    this.showMap = true;
    console.log('Tọa độ của sân là', lat, lng);
    this.showMap = true;
    setTimeout(() => {
      if (!this.map) {
        this.map = L.map('map').setView(
          [(this.longitude = lng), (this.longitudeMap = lat)],
          13
        );
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
          attribution:
            '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
        }).addTo(this.map);
      }
    }, 0);

    // this.showMap = true;
    // this.initMap();
  }

  viewDetail(stadiumId: number): void {
    this.stadiumDetail = this.dataStadium.find(
      (stadium) => stadium.areaId === stadiumId
    );
    console.log('detail area', this.stadiumDetail);
    this.showDetailPopup = true;
    console.log('Detail of Stadium:', this.stadiumDetail);
  }

  // choose(time: string, name: string, price: string, day: string) {
  //   const index = this.selectedFields.findIndex(
  //     field => field.time === time && field.name === name && field.day === day
  //   );

  //   if (index === -1) {
  //     this.selectedFields.push({ time, name, price, day }); // Chọn sân
  //   } else {
  //     this.selectedFields.splice(index, 1); // Bỏ chọn nếu đã chọn trước đó
  //   }
  // }

  // isSelected(time: string, name: string, day: string): boolean {
  //   return this.selectedFields.some(field => field.time === time && field.name === name && field.day === day);
  // }

  goToPayment() {
    this.router.navigate(['/payment'], {
      queryParams: { selectedFields: JSON.stringify(this.selectedFields) },
    });
  }
  protected readonly Number = Number;

}
