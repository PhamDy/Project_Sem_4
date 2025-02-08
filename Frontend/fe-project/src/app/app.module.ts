import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
