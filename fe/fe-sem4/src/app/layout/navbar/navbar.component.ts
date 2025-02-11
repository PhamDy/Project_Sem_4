import { Component } from '@angular/core';
import { Router } from '@angular/router';
import * as L from 'leaflet';
import * as AOS from 'aos';
import { LocationService } from '../../services/location-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  searchQuery: string = '';
  filteredResults: any[] = [];
  finalSearch: string = '';
  showMap: boolean = false;
  map: any;
  selectedLatLng: { lat: number, lng: number } | null = null;

  latitude: number = 21.0285;
  longitude: number = 105.8542;

  selectedCoordinates: { lat: number, lng: number } = { lat: 0, lng: 0 };
  isCoordinatesSelected: boolean = false;

  fakeData: any[] = [
    { name: 'Sân bóng Thanh Xuân', address: 'Thanh Xuân, Hà Nội' },
  ];

  constructor(private router: Router, private locationService: LocationService) {
    this.filteredResults = this.fakeData;
  }

  ngOnInit(): void {
    AOS.init({
      duration: 2000,
      easing: 'ease-in-out',
      once: false
    });

    window.addEventListener('scroll', () => {
      AOS.refresh();
    });

    this.map = L.map('map-navbar').setView([this.latitude, this.longitude], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);

    this.map.on('click', (e: any) => {
      const { lat, lng } = e.latlng;
      console.log(`Tọa độ được chọn: ${lat}, ${lng}`);
      this.selectedCoordinates = { lat, lng };
      this.isCoordinatesSelected = true;

      L.marker([lat, lng])
        .addTo(this.map)
        .bindPopup(`Tọa độ: ${lat}, ${lng}`)
        .openPopup();
    });
  }

  openMap(): void {
    this.showMap = true;
    console.log("tọa độ bản đồ")
    setTimeout(() => {
      if (this.map) {
        this.map.invalidateSize();
      }
    }, 0);
  }

  closeMap(): void {
    this.showMap = false;
  }

  onMapClick(event: any): void {
    const { lat, lng } = event.latlng;
    console.log(`Tọa độ được chọn: ${lat}, ${lng}`);
    this.selectedLatLng = { lat, lng };
    this.isCoordinatesSelected = true;
    L.marker([lat, lng])
      .addTo(this.map)
      .bindPopup(`Tọa độ: ${lat}, ${lng}`)
      .openPopup();
  }

  // Hàm tìm kiếm
  onSearch() {
    if (this.searchQuery.trim() === '') {
      this.filteredResults = [];
    } else {
      this.filteredResults = this.fakeData.filter(item =>
        item.name.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        item.address.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    }
  }

  selectPlace(place: any) {
    this.searchQuery = place.name;
    this.filteredResults = [];
  }

  search() {
    this.finalSearch = this.searchQuery.trim();
    if (this.finalSearch !== '') {
      this.router.navigate(['/booking'], { queryParams: { query: this.finalSearch } });
    }
  }

  goToBooking() {
    if (this.isCoordinatesSelected) {
      this.router.navigate(['/booking'], { queryParams: { lat: this.selectedCoordinates.lat, lng: this.selectedCoordinates.lng } });
      this.showMap = false;
    } else {
      alert('Vui lòng chọn một địa điểm trên bản đồ.');
    }
  }
}
