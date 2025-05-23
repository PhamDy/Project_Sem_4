import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingServicesService } from '../../../services/booking-services.service';
import * as L from 'leaflet';
import { StadiumService } from '../../../services/stadium-service.service';
import { weekSchedule } from './schedule-data';
import * as AOS from 'aos';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { throwIfEmpty } from 'rxjs';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-booking-detail-field',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './booking-detail-field.component.html',
  styleUrl: './booking-detail-field.component.css',
})
export class BookingDetailFieldComponent implements OnInit {
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
    { label: 'Chủ Nhật', value: 1 },
  ];

  timeFrames = [
    { id: 1, label: '10h00 - 11h30' },
    { id: 2, label: '15h00 - 16h30' },
    { id: 3, label: '15h00 - 16h30' },
    { id: 4, label: '17h00 - 18h30' },
    { id: 5, label: '19h00 - 20h30' },
    { id: 6, label: '21h00 - 22h30' },
  ];

  availableTimeFrames: any[] = [];

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
    '1': '8h00 - 9h30',
    '2': '10h00 - 11h30',
    '3': '15h00 - 16h30',
    '4': '17h00 - 18h30',
    '5': '19h00 - 20h30',
    '6': '21h00 - 22h30',
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

  timeFrameMapping: { [key: number]: string } = {
    1: "8h00 - 9h30",
    2: "10h00 - 11h30",
    3: "15h00 - 16h30",
    4: "17h00 - 18h30",
    5: "19h00 - 20h30",
    6: "21h00 - 22h30"
  };
  

  choose(data: any, date: string) {
    const index = this.selectedFields.findIndex(
      (field) => field.timeFrame === data.timeFrame && field.date === date
    );

    const item = {
      ...data,
      date: date,
      quantity: 1,
      availableQuantity: data.quantity,
      amount: data.price,
      timeFrameStr: this.timeFrameMapping[data.timeFrame] || null
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
  urlPayment:any;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private bookingService: BookingServicesService,
    private stadiumService: StadiumService,
    private router: Router,
    private message: NzMessageService
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      this.bookingId = params.get('id');
      if (this.bookingId) {
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
      timeFrame: [{ value: null, disabled: true }, Validators.required],
    });

    this.handleEnableTimeFrame();
  }

  selectedPrice: number | null = null;
  previousFormValues: any = {};
  handleEnableTimeFrame() {
    this.longTermForm.valueChanges.subscribe((currentValues) => {
      const { month, quantity, weekDay } = currentValues;
      const timeFrameControl = this.longTermForm.get('timeFrame');
      const isValid =
        month &&
        quantity &&
        quantity >= 1 &&
        weekDay !== null &&
        weekDay !== undefined;

      if (
        isValid &&
        (this.previousFormValues.month !== month ||
          this.previousFormValues.quantity !== quantity ||
          this.previousFormValues.weekDay !== weekDay)
      ) {
        this.previousFormValues = { ...currentValues };
        const totalWeekdays = this.countWeekdaysInMonth(month, Number(weekDay));
        this.selectedPrice = totalWeekdays * quantity * this.stadiumDetail.price;
        if (timeFrameControl?.disabled) {
          timeFrameControl.enable();
        }
        const payload = {
          quantity: Number(quantity),
          weekDay: Number(weekDay),
          date: month + '-01',
          fieldId: Number(this.bookingId),
        };

        this.bookingService
          .validatePeriod(payload)
          .subscribe((response: number[]) => {
            this.availableTimeFrames = this.timeFrames.filter((tf) =>
              response.includes(tf.id)
            );
          });
      } else if (!isValid && timeFrameControl?.enabled) {
        timeFrameControl.disable();
        timeFrameControl.reset();
      }
    });
  }

  countWeekdaysInMonth(monthString: string, weekday: number): number {
    const [year, month] = monthString.split('-').map(Number);
    const firstDay = new Date(year, month - 1, 1);
    const lastDay = new Date(year, month, 0);

    let count = 0;
    for (let day = 1; day <= lastDay.getDate(); day++) {
      const date = new Date(year, month - 1, day);
      if (date.getDay() === weekday) {
        count++;
      }
    }

    return count;
  }

  onSubmit() {
    console.log("dsds",this.bookingId);
    if (this.longTermForm.valid) {
      const formValue = this.longTermForm.value;

      const payload = {
        quantity: Number(formValue.quantity),
        weekDay: Number(formValue.weekDay),
        month: formValue.month + '-01',
        fieldTypeId: Number(this.bookingId),
        timeFrame: Number(formValue.timeFrame),
        price: this.selectedPrice || 0,
      };

      this.bookingService.createPeriod(payload).subscribe({
        next: (res) => {
          this.message.success('Đặt sân thành công!');
          this.showLongTermPopup = false;
          this.longTermForm.reset();
          this.longTermForm.get('timeFrame')?.disable();
          if (res.url) {
            window.location.href = res.url;
          } else {
            console.error('Không có URL thanh toán hợp lệ!');
          }
        },
        error: () => {
          this.message.error('Đặt sân thất bại!');
        },
      });
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
        this.allFieldArea = res?.fields;
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
