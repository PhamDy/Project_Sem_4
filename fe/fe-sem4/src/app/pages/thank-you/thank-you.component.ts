import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-thank-you',
  templateUrl: './thank-you.component.html',
  styleUrl: './thank-you.component.css',
})
export class ThankYouComponent {
  constructor(private router: Router) {}

  goHome() {
    this.router.navigate(['/']);
  }

  goToOrder() {
    this.router.navigate(['/orders']);
  }
}
