import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AreaListComponent } from './pages/area/area-list/area-list.component';
import { TimeFrameListComponent } from './pages/timeFrame/time-frame-list/time-frame-list.component';
import { TimeFrameFormComponent } from './pages/timeFrame/time-frame-form/time-frame-form.component';

const routes: Routes = [
  {
    path: "",
    component: DashboardComponent
  },
  {
    path: "area",
    component: AreaListComponent
  },
  // {
  //   path: "product/:id",
  //   component: ProductFormComponent
  // },
  // {
  //   path: "newProduct",
  //   component: ProductFormComponent
  // },
  {
    path: "timeFrame",
    component: TimeFrameListComponent
  },
  {
    path: "editTimeFrame",
    component: TimeFrameFormComponent
  },
  // {
  //   path: "newCategory",
  //   component: CategoryFormComponent
  // },
  // {
  //   path: "user",
  //   component: UserComponent
  // },
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
