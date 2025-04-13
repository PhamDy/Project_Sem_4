import { Component, Input, OnInit,EventEmitter, Output } from '@angular/core';
import * as L from 'leaflet';
import { LocationService } from '../../services/location-service.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  @Input() latitude: number = 21.0285;
  @Input() longitude: number = 105.8542;

  @Output() coordinatesSelected = new EventEmitter<{ lat: number, lng: number }>();

  map: any;
  selectedLatLng: { lat: number, lng: number } | null = null;
  isCoordinatesSelected: boolean = false;

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
    // Khởi tạo bản đồ
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

  // Xử lý sự kiện click trên bản đồ
  onMapClick(event: any): void {
    const latlng = event.latlng;
    if (latlng) {
      const { lat, lng } = latlng;
      console.log("đây")
      console.log(`Tọa độ được chọn: ${lat}, ${lng}`);
      this.selectedLatLng = { lat, lng };
      this.isCoordinatesSelected = true;
      this.coordinatesSelected.emit({ lat, lng });

      // Thêm marker tại vị trí đã click
      L.marker([lat, lng])
        .addTo(this.map)
        .bindPopup(`Tọa độ: ${lat}, ${lng}`)
        .openPopup();
    } else {
      console.error('Lỗi: event.latlng là undefined');
    }
  }
}
