import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { AboutUsComponent } from './pages/about-us/about-us.component';
import { AuthComponent } from './pages/auth/auth.component';
import { BookingComponent } from './pages/booking/booking.component';
import { TournamentComponent } from './pages/tournament/tournament.component';
import { LoginComponent } from './pages/login/login.component';
import { BookingDetailAreaComponent } from './pages/booking/booking-detail-area/booking-detail-area.component';
import {PaymentComponent} from './pages/payment/payment.component';
import { AuthGuard } from './guard/auth.guard';
import { VerifyOtpComponent } from './pages/auth/verify-otp/verify-otp.component';

const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
    // canActivate: [AuthGuard], 
  },
  {
    path: 'booking',
    component: BookingComponent
  },
  {
    path: 'payment',
    component: PaymentComponent
  },
  {
    path: 'booking-area',
    component: BookingDetailAreaComponent
  },
  {
    path: 'aboutUs',
    component: AboutUsComponent
  },
  {
    path: 'login',
    component: AuthComponent
  },
  {
    path: 'auth',
    component: AuthComponent
  },

  {
    path: 'verify-otp',
    component: VerifyOtpComponent
  },
  {
    path: 'tournament',
    component: TournamentComponent
  },
  {
    path: '**',
    redirectTo: '/',
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
