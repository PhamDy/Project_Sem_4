import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private apiUrl = environment.ApiUrl;

  login(params: { userName: string; password: string }): Observable<{ token: string }> {
    return this.http.get<{ token: string }>(`${this.apiUrl}/public/api/v1/login`, {
      params: {
        userName: params.userName,
        password: params.password
      }
    }).pipe(
      tap(res => {
        console.log("ress: ", res);
        localStorage.setItem('token', res.token);
      })
    );
  }

  signup(data: { name: string; userName: string; password: string, email: string }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/public/api/v1`, data);
  }

  logout() {
    localStorage.removeItem('token');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }
}
