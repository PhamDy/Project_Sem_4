import { Component, OnInit } from '@angular/core';
import * as AOS from 'aos';

@Component({
  selector: 'app-content-booking1',
  templateUrl: './content-booking1.component.html',
  styleUrl: './content-booking1.component.css'
})
export class ContentBooking1Component implements OnInit{
  ngOnInit(): void {
    AOS.init({
      duration: 500,
      easing: 'ease-in-out',
      once: true
    })
  }
}
