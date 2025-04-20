import { Component } from '@angular/core';
import { NgForm, NgModel } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth-service.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css'],
})
export class AuthComponent {
  isLogin = true;
  loading = false;

  showOtp = false;
  otpCode = '';

  //refactor formbuilder & detach component login, signup

  loginData = {
    userName: '',
    password: '',
  };

  signupData = {
    name: '',
    userName: '',
    password: '',
    confirmPassword: '',
    email: '',
  };

  constructor(
    private authService: AuthService,
    private router: Router,
    private message: NzMessageService
  ) {}

  toggleForm() {
    this.isLogin = !this.isLogin;
  }

  onLogin(form: NgForm) {
    if (form.invalid) {
      this.markFormControlsTouched(form);
      return;
    }

    this.authService.login(this.loginData).subscribe({
      next: () => {
        this.loading = false;
        const prevUrl = this.router
          .getCurrentNavigation()
          ?.previousNavigation?.finalUrl?.toString();
        this.router.navigateByUrl(prevUrl || '/');
      },
      error: (err) => {
        this.loading = false;
        this.message.error('Tài khoản hoặc mật khẩu không đúng');
      },
    });
  }

  onSignup(form: NgForm) {
    if (form.invalid) {
      this.markFormControlsTouched(form);
      return;
    }

    if (this.signupData.password !== this.signupData.confirmPassword) {
      form.controls['confirmPassword']?.setErrors({ notMatch: true });
      return;
    }
    this.loading = true;
    const { name, userName, password, email } = this.signupData;

    this.authService.signup({ name, userName, password, email }).subscribe({
      next: () => {
        this.loading = false;
        this.toggleForm();
        this.router.navigate(['/verify-otp']);
        this.authService.genOtp(email).subscribe({
          next: () => {
            console.log('genOtp success');
          },
          error: () => {
            this.message.error('Hệ thống xảy ra lỗi, vui lòng thử lại sau!');
          },
        });
      },
      error: (err) => {
        this.loading = false;
        this.message.error(
          err?.response?.message || err?.message || 'Đăng ký thất bại'
        );
      },
    });
  }

  validateConfirmPassword(confirmModel: NgModel) {
    if (
      this.signupData.confirmPassword &&
      this.signupData.confirmPassword !== this.signupData.password
    ) {
      confirmModel.control.setErrors({ notMatch: true });
    } else {
      confirmModel.control.setErrors(null);
    }
  }

  private markFormControlsTouched(form: NgForm) {
    Object.values(form.controls).forEach((control) => {
      control.markAsTouched();
      control.updateValueAndValidity();
    });
  }
}
