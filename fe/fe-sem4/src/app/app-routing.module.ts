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
import {BookingDetailFieldComponent} from './pages/booking/booking-detail-field/booking-detail-field.component';
import {BookingTournamentComponent} from './pages/booking/booking-tournament/booking-tournament.component';
import {UserProfileComponent} from './pages/user-profile/user-profile.component';
import { ThankYouComponent } from './pages/thank-you/thank-you.component';

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
    path: 'detail-area/:id',
    component: BookingDetailAreaComponent
  },
  {
    path: 'booking-area/:id',
    component: BookingDetailFieldComponent
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
    path: 'user-profile',
    component: UserProfileComponent
  },
  {
    path: 'auth',
    component: AuthComponent
  },
  {
    path: 'tournament/:id',
    component: BookingTournamentComponent
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
    path: 'thank-you',
    component: ThankYouComponent
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
