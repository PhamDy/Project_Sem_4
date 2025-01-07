import { Component,Input } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrl: './map.component.css'
})
export class MapComponent {
  @Input() latitude: number = 21.0285;
  @Input() longitude: number = 105.8542;
  
  constructor() { }

  ngOnInit(): void {

    const map = L.map('map').setView([this.latitude, this.longitude], 13);


    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    // Thêm một marker vào vị trí truyền vào
    L.marker([this.latitude, this.longitude]).addTo(map)
      .bindPopup('Đây là Hà Nội, Việt Nam!')
      .openPopup();
  }
  }
