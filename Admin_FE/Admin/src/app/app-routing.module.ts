import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AreaListComponent } from './pages/area/area-list/area-list.component';

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
  // {
  //   path: "category",
  //   component: CategoryListComponent
  // },
  // {
  //   path: "category/:id",
  //   component: CategoryFormComponent
  // },
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
