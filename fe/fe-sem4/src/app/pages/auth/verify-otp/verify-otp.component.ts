import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth-service.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'auth-verify-otp',
  templateUrl: './verify-otp.component.html',
  styleUrls: ['./verify-otp.component.css']
})
export class VerifyOtpComponent {
  constructor(private authService: AuthService, private router: Router, private message: NzMessageService) {}

  otpCode: string = '';
  loading: boolean = false;

  otpConfig = {
    length: 6,
    inputClass: 'otp-input'
  };

  onOtpChange(otp: string) {
    this.otpCode = otp;
  }

  verifyOtp() {
    if (this.otpCode.length === 6) {
      this.loading = true;
      this.authService.verfifyOtp(this.otpCode).subscribe( {
        next: () => {
          this.loading = false;
          this.message.success('Đăng ký tài khoản thành công, vui lòng đăng nhập lại!')
          this.router.navigate(['/login']);
        },
        error: () => {
          this.loading = false;
          this.message.error('Mã OTP không hợp lệ!')
        },
      }
      );
    }
  }

  reSendOtp() {
    //call resend api & logic
  }
  
  cancel() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
