import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { TimeFrameFormComponent } from './time-frame-form/time-frame-form.component';
import { TimeFrameListComponent } from './time-frame-list/time-frame-list.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule
  ],
  declarations: [
    TimeFrameFormComponent,
    TimeFrameListComponent
  ], exports: [
    TimeFrameFormComponent,
    TimeFrameListComponent
  ]
})
export class TimeFrameModule { }
