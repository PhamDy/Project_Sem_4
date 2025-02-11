import { Component } from '@angular/core';
import * as AOS from 'aos';

@Component({
  selector: 'app-content-5',
  templateUrl: './content-5.component.html',
  styleUrl: './content-5.component.css'
})
export class Content5Component {
 ngOnInit(): void {

    AOS.init({
      duration: 1000,
      easing: 'ease-in-out',
      once: true
    })
  }
}
