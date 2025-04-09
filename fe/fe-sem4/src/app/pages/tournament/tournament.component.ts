import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { weekSchedule} from './schedule-data';
import {Router} from '@angular/router';

@Component({
  selector: 'app-tournament',
  templateUrl: './tournament.component.html',
  styleUrl: './tournament.component.css'
})
export class TournamentComponent {
  weekSchedule = weekSchedule;
  selectedFields: { time: string; name: string; price: string; day: string }[] = [];

  constructor(private router: Router) {}

  choose(time: string, name: string, price: string, day: string) {
    const index = this.selectedFields.findIndex(
      field => field.time === time && field.name === name && field.day === day
    );

    if (index === -1) {
      this.selectedFields.push({ time, name, price, day }); // Chọn sân
    } else {
      this.selectedFields.splice(index, 1); // Bỏ chọn nếu đã chọn trước đó
    }
  }

  isSelected(time: string, name: string, day: string): boolean {
    return this.selectedFields.some(field => field.time === time && field.name === name && field.day === day);
  }

  goToPayment() {
    this.router.navigate(['/payment'], {
      queryParams: { selectedFields: JSON.stringify(this.selectedFields) }
    });
  }
}
