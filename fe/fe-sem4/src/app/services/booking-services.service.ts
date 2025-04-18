import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingServicesService {
  private getAreaById = "http://localhost:9001/stadium-service/private/api/v1/area"
  private getFieldTypeByArea = "http://localhost:9001/stadium-service/private/api/v1/calender"
  private createBookingApi = "http://localhost:9002/booking-service/private/api/v1/create"
  constructor(private http: HttpClient) { }

  bookingGetAreaById(id: any): Observable<any> {
    return this.http.get<any>(`${this.getAreaById}?id=${id}`);
  }

  getfieldTypeByArea(id: number, index: number) {
    return this.http.get<any>(`${this.getFieldTypeByArea}?id=${id}&index=${index}`);
  }

  createBooking(CreateBookingRequest: any): Observable<any> {
    return this.http.post<any>(`${this.createBookingApi}`,CreateBookingRequest);
  }

}
