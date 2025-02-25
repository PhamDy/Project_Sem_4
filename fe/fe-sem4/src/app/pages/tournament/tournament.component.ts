import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-tournament',
  templateUrl: './tournament.component.html',
  styleUrl: './tournament.component.css'
})
export class TournamentComponent {
  timeForm: FormGroup;
  timeSlots: string[] = [];

  constructor(private fb: FormBuilder) {
    this.timeForm = this.fb.group({
      fromHour: [''],
      fromMinute: [''],
      toHour: [''],
      toMinute: ['']
    });
  }

  addTimeSlot() {
    const { fromHour, fromMinute, toHour, toMinute } = this.timeForm.value;

    if (fromHour !== '' && fromMinute !== '' && toHour !== '' && toMinute !== '') {
      const timeSlot = `${fromHour}h${fromMinute} - ${toHour}h${toMinute}`;
      this.timeSlots.push(timeSlot);
      this.timeForm.reset();
    }
  }

  removeTimeSlot(index: number) {
    this.timeSlots.splice(index, 1);
  }
}
