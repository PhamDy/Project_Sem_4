import { NgModule } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { BookingComponent } from './pages/booking/booking.component';
import { TournamentComponent } from './pages/tournament/tournament.component';
import { AboutUsComponent } from './pages/about-us/about-us.component';
import { ProductComponent } from './pages/product/product.component';
import { AuthComponent } from './pages/auth/auth.component';
import { LoginComponent } from './pages/login/login.component';
import { MapComponent } from './pages/map/map.component';
import { Content1Component } from './pages/home-page/content-1/content-1.component';
import { Content2Component } from './pages/home-page/content-2/content-2.component';
import { Content3Component } from './pages/home-page/content-3/content-3.component';
import { Content4Component } from './pages/home-page/content-4/content-4.component';
import { Content5Component } from './pages/home-page/content-5/content-5.component';
import { RouterModule } from '@angular/router';
import { ContentBooking1Component } from './pages/booking/content-booking1/content-booking1.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavbarComponent,
    HomePageComponent,
    BookingComponent,
    TournamentComponent,
    AboutUsComponent,
    ProductComponent,
    AuthComponent,
    LoginComponent,
    MapComponent,
    Content1Component,
    Content2Component,
    Content3Component,
    Content4Component,
    Content5Component,
    ContentBooking1Component,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule
  ],
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
