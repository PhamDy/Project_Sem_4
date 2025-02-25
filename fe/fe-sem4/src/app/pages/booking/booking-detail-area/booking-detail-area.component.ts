import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookingServicesService } from '../../../services/booking-services.service';
import * as L from 'leaflet';
import { StadiumService } from '../../../services/stadium-service.service';

@Component({
  selector: 'app-booking-detail-area',
  templateUrl: './booking-detail-area.component.html',
  styleUrl: './booking-detail-area.component.css'
})
export class BookingDetailAreaComponent {
  searchQuery: string = '';
  dataStadium: any[] = [];
  dataStadiumSearch: any[] = [];
  findStadiumByKeyWord: any[] = [];
  latitude: number | null = null;
  longitude: number | null = null;
  latitudeMap: number | null = null;
  longitudeMap: number | null = null;
  img: any = 'https://imgur.com/JMP9KcJ.png'

  showDetailPopup: boolean = false;
  stadiumDetail: any = null;

  allFieldArea: any = null;

  showMap: boolean = false;
  map: any;
  marker: any;


  bookingId: string | null = '';

  constructor(private route: ActivatedRoute,
    private bookingService: BookingServicesService,
    private stadiumService: StadiumService,
  ) {}

  ngOnInit() {
    this.route.queryParamMap.subscribe(params => {
      this.bookingId = params.get('id');
      console.log('Booking ID:', this.bookingId);
      this.getAllStadiums();
      this.getBookingAreaById(this.bookingId);

    });
  }

  getAllStadiums(): void {
    this.stadiumService.getAllStadiumPageable().subscribe(
      (res) => {
        this.dataStadium = res.content;
        console.log("data is bookingggggggggg:", this.dataStadium);
      },
      (error) => {
        console.error('Error fetching stadium data:', error);
      }
    );
  }

  getBookingAreaById(id: any): void{
    this.bookingService.bookingGetAreaById(id).subscribe(
      (res) => {
        this.allFieldArea = res.fields
        console.log("data is", this.allFieldArea)
      },
      (error) => {
        console.error('Error fetching stadium data:', error);
      }
    )

  }
   openMapWithCoordinates(lat: number, lng: number): void {
      this.latitudeMap = lat;
      this.longitudeMap = lng;
      this.showMap = true;
      console.log("Tọa độ của sân là",lat, lng);
      this.showMap = true;
      setTimeout(() => {
        if (!this.map) {
          this.map = L.map('map').setView([this.longitude = lng, this.longitudeMap = lat], 13);
          L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          }).addTo(this.map);
        }
      }, 0);

      // this.showMap = true;
      // this.initMap();
    }

    viewDetail(stadiumId: number): void {
      this.stadiumDetail = this.dataStadium.find(stadium => stadium.areaId === stadiumId);
      console.log("detail area", this.stadiumDetail);
      this.showDetailPopup = true;
      console.log('Detail of Stadium:', this.stadiumDetail);
    }
}
