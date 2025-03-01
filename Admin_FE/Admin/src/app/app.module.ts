import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SliderComponent } from './layout/slider/slider.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { FooterComponent } from './layout/footer/footer.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { AreaModule } from './pages/area/area.module';
import { ProductModule } from './pages/products/product.module';
import { TimeFrameModule } from './pages/timeFrame/timeFrame.module';

@NgModule({
  declarations: [
    AppComponent,
    SliderComponent,
    DashboardComponent,
    NavbarComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    // AreaModule,
    // ProductModule,
    TimeFrameModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
