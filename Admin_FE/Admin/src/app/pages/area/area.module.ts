import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AreaListComponent } from './area-list/area-list.component';
import { AreaFormComponent } from './area-form/area-form.component';


@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [AreaListComponent, AreaListComponent],
   exports: [
      AreaListComponent,
      AreaListComponent
    ]
})
export class AreaModule { }
