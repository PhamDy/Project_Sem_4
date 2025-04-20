import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  isLogin = true;
  loading = false;
  errorMessage: string | null = null;

  loginData = {
    username: '',
    password: ''
  };

  signupData = {
    fullName: '',
    username: '',
    password: '',
    confirmPassword: ''
  };

  constructor(private authService: AuthService, private location: Location) {}

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
        console.log('Login successful');
        this.location.back();
      },
      error: err => {
        this.loading = true;
        this.errorMessage = err.error?.message || 'Đăng nhập thất bại';
      }
    });
  }

  onSignup() {
    this.loading = true;
    this.errorMessage = null;

    if (this.signupData.password !== this.signupData.confirmPassword) {
      this.loading = false;
      this.errorMessage = 'Mật khẩu xác nhận không khớp';
      return;
    }

    const { fullName, username, password } = this.signupData;

    this.authService.signup({ fullName, username, password }).subscribe({
      next: () => {
        this.loading = false;
        console.log('Signup successful');
        this.toggleForm();
      },
      error: err => {
        this.loading = false;
        this.errorMessage = err.error?.message || 'Đăng ký thất bại';
      }
    });
  }
}
