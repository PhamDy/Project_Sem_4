import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { AuthService } from '../services/auth-service.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private readonly PUBLIC_URLS = [
    '/public'
  ];

  constructor(private router: Router, private authService: AuthService){}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const isPublic = this.PUBLIC_URLS.some(url => req.url.includes(url));
    let clonedRequest = req;

    if (!isPublic) {
      const token = this.authService.getToken();
      if (token) {
        clonedRequest = req.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`
          }
        });
      } else {
        this.router.navigate(['/login']);
      }
    }

    return next.handle(clonedRequest).pipe(
      tap((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          const body: any = event.body;
          if (body?.status === 401) {
            this.handleUnauthorized();
          }
        }
      }),
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          this.handleUnauthorized();
        }
        return throwError(() => error);
      })
    );
  }

  private handleUnauthorized(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
