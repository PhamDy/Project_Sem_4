import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Route, Router } from '@angular/router';
import * as L from 'leaflet';
import * as AOS from 'aos';

@Component({
  selector: 'app-content-1',
  templateUrl: './content-1.component.html',
  styleUrl: './content-1.component.css'
})
export class Content1Component {
  searchForm: FormGroup;
  districts: string[] = [];
  showMap: boolean = false;
  isCoordinatesSelected: boolean = false;
  selectedCoordinates: { lat: number, lng: number } = { lat: 0, lng: 0 };
  map: any;
  latitude: any | null = null;
  longitude: any | null = null;
  selectedLatLng: { lat: number, lng: number } | null = null;

  cityDistrictsMap: { [key: string]: string[] } = {
    'Hà Nội': ['Cầu Giấy', 'Đống Đa', 'Ba Đình'],
    'Đà Nẵng': ['Hải Châu', 'Sơn Trà', 'Liên Chiểu'],
    'TP.Hồ Chí Minh': ['Quận 1', 'Quận 3', 'Quận 7']
  };

  ngOnInit(): void {
    AOS.init({
      duration: 2000,
      easing: 'ease-in-out',
      once: false
    });



    window.addEventListener('scroll', () => {
      AOS.refresh();
    });

    this.map = L.map('map').setView([this.latitude, this.longitude], 13);

    // Thêm lớp tile layer
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);

    // Thêm marker cho vị trí ban đầu
    L.marker([this.latitude, this.longitude]).addTo(this.map)
      .bindPopup('Vị trí ban đầu')
      .openPopup();

    // Gắn sự kiện click vào đối tượng bản đồ, không phải vào div
    this.map.on('click', (event: any) => this.onMapClick(event));
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



  constructor(private fb: FormBuilder,private router: Router) {
    this.searchForm = this.fb.group({
      fieldSize: [''],
      city: [''],
      district: ['']
    });
  }

  onCoordinatesSelected(coords: { lat: number, lng: number }) {
    this.selectedCoordinates = coords;
    this.isCoordinatesSelected = true;
    console.log('Tọa độ nhận từ component con:', coords);
    console.log("type",this.selectedCoordinates);
  }

  goToBooking() {
    this.isCoordinatesSelected = true;
    if (this.isCoordinatesSelected) {
      this.router.navigate(['/booking'], { queryParams: { lat: this.selectedCoordinates.lat, lng: this.selectedCoordinates.lng } });
      this.showMap = false;
    } else {
      alert('Vui lòng chọn một địa điểm trên bản đồ.');
    }
  }

  closeMap(): void {
    this.showMap = false;
  }



  onMapClick(event: any): void {
    const { lat, lng } = event.latlng;
    console.log("type");
    console.log(`Tọa độ được chọn: ${lat}, ${lng}`);
    this.selectedLatLng = { lat, lng };
    this.isCoordinatesSelected = true;

    L.marker([lat, lng])
      .addTo(this.map)
      .bindPopup(`Tọa độ: ${lat}, ${lng}`)
      .openPopup();
  }

  onCityChange() {
    const selectedCity = this.searchForm.get('city')?.value;
    this.districts = this.cityDistrictsMap[selectedCity] || [];
    this.searchForm.patchValue({ district: '' }); // Reset quận/huyện khi đổi tỉnh
  }

  search() {
    const { fieldSize, city, district } = this.searchForm.value;

    this.router.navigate(['/booking'], {
      queryParams: {
        fieldSize: fieldSize || '',
        city: city || '',
        district: district || ''
      }
    });
  }

}
