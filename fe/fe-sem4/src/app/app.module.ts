import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { BookingDetailAreaComponent } from './pages/booking/booking-detail-area/booking-detail-area.component';
import { PaymentComponent } from './pages/payment/payment.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { CommonModule, registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AuthInterceptor } from './interceptors/http.interceptors';
import { NgZorroAntdModule } from './shared/ng-zorro-antd.module';
import { NgOtpInputModule } from 'ng-otp-input';

registerLocaleData(en);

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
    BookingDetailAreaComponent,
    PaymentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    CommonModule,
    NgZorroAntdModule,
    NgOtpInputModule
  ],
  providers: [
    provideHttpClient(withInterceptorsFromDi()),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    { provide: NZ_I18N, useValue: en_US },
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
