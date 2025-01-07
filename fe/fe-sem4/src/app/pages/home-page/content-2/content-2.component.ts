import { Component, OnInit } from '@angular/core';
import * as AOS from 'aos';

@Component({
  selector: 'app-content-2',
  templateUrl: './content-2.component.html',
  styleUrl: './content-2.component.css'
})
export class Content2Component implements OnInit {
  currentSlide: number = 0;

  ngOnInit(): void {
    setInterval(() => {
      this.nextSlide();
    }, 3000);

    AOS.init({
      duration: 500,
      easing: 'ease-in-out',
      once: true
    })
  }
  nextSlide(): void {
    this.currentSlide = (this.currentSlide + 1) % 3;
  }

  prevSlide(): void {
    this.currentSlide = (this.currentSlide - 1 + 3) % 3;
  }
}
