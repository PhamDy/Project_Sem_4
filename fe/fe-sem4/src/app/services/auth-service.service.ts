import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private apiUrl = environment.ApiUrl;

  login(params: {
    userName: string;
    password: string;
  }): Observable<{ token: string, userInfor: any }> {
    return this.http
      .get<{ token: string, userInfor: any }>(`${this.apiUrl}/public/api/v1/login`, {
        params: {
          userName: params.userName,
          password: params.password,
        },
      })
      .pipe(
        tap((res) => {
          localStorage.setItem('token', res.token);
          localStorage.setItem('userInfor', JSON.stringify(res.userInfor));

        })
      );
  }

  signup(data: {
    name: string;
    userName: string;
    password: string;
    email: string;
  }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/public/api/v1`, data);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userInfor');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUserInfor(): any | null {
    const data = localStorage.getItem('userInfor');
    return data ? JSON.parse(data) : null;
  }

  genOtp(email: string): Observable<any> {
    return this.http.get<{ token: string }>(
      `${this.apiUrl}/public/api/v1/regenerate-otp`,
      {
        params: {
          email: email,
        },
      }
    );
  }

  verfifyOtp(otp: string): Observable<any> {
    return this.http.put<{ token: string }>(
      `${this.apiUrl}/public/api/v1/active-user`,
      {},
      {
        params: {
          otp: otp,
        },
      }
    );
  }
}
