import { Component } from '@angular/core';
import * as AOS from 'aos';

@Component({
  selector: 'app-content-3',
  templateUrl: './content-3.component.html',
  styleUrl: './content-3.component.css'
})
export class Content3Component {
  ngOnInit(): void {
    AOS.init({
      duration: 2000,
      easing: 'ease-in-out',
      once: false
    })

    window.addEventListener('scroll', () => {
      AOS.refresh();
    });
}
}
