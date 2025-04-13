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

  latitude: any | null = null;
  longitude: any | null = null;

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

}
