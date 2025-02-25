import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingServicesService {
  private getAreaById = "http://localhost:9001/stadium-service/private/api/v1/area"

  constructor(private http: HttpClient) { }

  bookingGetAreaById(id: any): Observable<any> {
    return this.http.get<any>(`${this.getAreaById}?id=${id}`);
  }
}
