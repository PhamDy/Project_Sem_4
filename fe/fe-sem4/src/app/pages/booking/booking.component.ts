import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StadiumService } from '../../services/stadium-service.service';
import { Router } from '@angular/router';
import * as L from 'leaflet';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  fieldSize: string = '';
  city: string = '';
  district: string = '';
  searchQuery: string = '';
  dataStadium: any[] = [];
  dataStadiumSearch: any[] = [];
  findStadiumByKeyWord: any[] = [];
  latitude: number | null = null;
  longitude: number | null = null;
  latitudeMap: number | null = null;
  longitudeMap: number | null = null;
  img: any = 'https://imgur.com/JMP9KcJ.png'
  itemsPerPage: number = 8;
  currentPage: number = 1;

  showDetailPopup: boolean = false;
  stadiumDetail: any = null;

  showMap: boolean = false;
  map: any;
  marker: any;

  constructor(private route: ActivatedRoute,
              private stadiumService: StadiumService,
              private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.fieldSize = params['fieldSize'] || 'Chưa chọn';
      this.city = params['city'] || 'Chưa chọn';
      this.district = params['district'] || 'Chưa chọn';
      this.searchQuery = params['query'] || '';
      console.log('Received search query:', this.searchQuery);
      this.performSearch(this.searchQuery);
      this.latitude = params['lat'] ? parseFloat(params['lat']) : null;
      this.longitude = params['lng'] ? parseFloat(params['lng']) : null;
      // const findAreaRequest: any = {
      //   distance: 3,
      //   // latitude:  this.latitude,
      //   // longitude: this.longitude,
      //   latitude:  21.026935,
      //   longitude: 105.784988,
      // }
      // this.getAreaByCondition(findAreaRequest);
    });
    console.log("long lat", this.latitude,this.longitude);
    this.getAllStadiums();
    const findAreaRequest: any = {
      distance: 3,
      latitude:  this.latitude,
      longitude: this.longitude,
    }
    this.getAreaByCondition(findAreaRequest);
  }

  performSearch(query: string): void {
    if (query) {
      console.log('Performing search with query:', query);
    } else {
      console.log('No search query provided.');
    }
  }
  get paginatedStadiums(): any[] {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.dataStadiumSearch.slice(startIndex, endIndex);
  }

  get totalPages(): number {
    return Math.ceil(this.dataStadiumSearch.length / this.itemsPerPage);
  }

  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }
  getAllStadiums(): void {
    this.stadiumService.getAllStadiumPageable().subscribe(
      (res) => {
        this.dataStadium = res;
        console.log("data is:", this.dataStadium);
      },
      (error) => {
        console.error('Error fetching stadium data:', error);
      }
    );
  }

  getAreaByCondition(findAreaRequest: any): void {
    this.stadiumService.getStadiumsCoordinates(findAreaRequest).subscribe(
      (res) => {
        console.log("data search", res);
        this.dataStadiumSearch = res;
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

  // Hàm xem chi tiết sân bóng
  viewDetail(stadiumId: number): void {
    this.stadiumDetail = this.dataStadium.find(stadium => stadium.areaId === stadiumId);
    this.showDetailPopup = true;
  }

  onClickBooking(id: any) {
    this.router.navigate([`/detail-area/${id}`]);
  }

  initMap(): void {
    // Kiểm tra nếu bản đồ chưa được khởi tạo
    if (this.latitude && this.longitude) {
      if (!this.map) {  // Nếu bản đồ chưa được khởi tạo
        this.map = L.map('map').setView([this.latitude, this.longitude], 15);

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
          attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(this.map);
      } else {  // Nếu bản đồ đã có, chỉ cần thay đổi vị trí
        this.map.setView([this.latitude, this.longitude], 15);
      }

      // Kiểm tra nếu marker chưa được tạo, tạo mới
      if (!this.marker) {
        this.marker = L.marker([this.latitude, this.longitude]).addTo(this.map);
      } else {
        // Nếu marker đã tồn tại, chỉ cần thay đổi vị trí
        this.marker.setLatLng([this.latitude, this.longitude]);
      }
    }
  }

  closeMap(): void {
    this.showMap = false;
  }


  protected readonly Number = Number;
}
