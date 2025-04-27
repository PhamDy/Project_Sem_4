import { Component } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'fe-sem4';
  latitude: number = 21.0285;
  longitude: number = 105.8542;

  isShowLayout = false;

  constructor(private router: Router) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationStart) {
        this.isShowLayout =
          event.url === '/login' ||
          event.url === '/verify-otp' ||
          event.url === '/thank-you';
      }
    });
  }
}
