import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth-service.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css'],
})
export class AuthComponent {
  isLogin = true;
  loading = false;
  errorMessage: string | null = null;

  showOtp = false;
  otpCode = '';

  loginData = {
    userName: '',
    password: ''
  };

  signupData = {
    name: '',
    userName: '',
    password: '',
    confirmPassword: '',
    email: 'tiennguyenhienvx1@gmail.com'
  };

  constructor(private authService: AuthService, private location: Location, private router: Router) {}

  toggleForm() {
    this.isLogin = !this.isLogin;
    this.errorMessage = null;
  }

  onLogin() {
    this.loading = true;
    this.errorMessage = null;

    this.authService.login(this.loginData).subscribe({
      next: () => {
        this.loading = true;
        const prevUrl = this.router.getCurrentNavigation()?.previousNavigation?.finalUrl?.toString();
        this.router.navigateByUrl(prevUrl || '/');
      },
      error: err => {
        this.loading = false;
        this.errorMessage = err.error?.message || 'Đăng nhập thất bại';
      }
    });
  }

  onSignup() {
    this.loading = true;
    this.errorMessage = null;

    if (this.signupData.password !== this.signupData.confirmPassword) {
      this.loading = false;
      alert('Mật khẩu xác nhận không khớp ')
      return;
    }

    const { name, userName, password, email } = this.signupData;

    this.authService.signup({ name, userName, password, email }).subscribe({
      next: () => {
        this.loading = false;
        this.toggleForm();
      },
      error: err => {
        this.loading = false;
        this.errorMessage = err.error?.message || 'Đăng ký thất bại';
      }
    });
  }

  verifyOtp() {
    this.loading = true;
    // this.authService.verifyOtp(this.otpCode).subscribe({
    //   next: () => {
    //     this.loading = false;
    //     alert('Xác minh OTP thành công');
    //     this.showOtp = false;
    //     this.toggleForm(); // Cho phép đăng nhập sau khi xác minh
    //   },
    //   error: err => {
    //     this.loading = false;
    //     alert('Xác minh OTP thất bại');
    //   }
    // });
  }

  otpConfig = {
    length: 6,
    inputClass: 'otp-input',
    allowNumbersOnly: true
  };

  onOtpChange(otp: string) {
    this.otpCode = otp;
    console.log('OTP entered:', otp);
  }
}
