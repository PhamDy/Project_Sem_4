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
  private validatePeriodApi = "http://localhost:9001/stadium-service/private/api/v1/validate-period"
  private createPeriodApi = "http://localhost:9002/booking-service/private/api/v1/create-period"
  private getBookingHistory = "http://localhost:9002/booking-service/private/api/v1/booking"
  constructor(private http: HttpClient) { }

  bookingGetAreaById(id: any): Observable<any> {
    return this.http.get<any>(`${this.getAreaById}?id=${id}`);
  }

  getfieldTypeByArea(id: string, index: number) {
    return this.http.get<any>(`${this.getFieldTypeByArea}?id=${id}&index=${index}`);
  }

  createBooking(CreateBookingRequest: any): Observable<any> {
    return this.http.post<any>(`${this.createBookingApi}`,CreateBookingRequest);
  }

  validatePeriod(validate: any): Observable<any> {
    return this.http.post<any>(`${this.validatePeriodApi}`,validate);
  }

  createPeriod(CreatePeriodRequest: any): Observable<any> {
    return this.http.post<any>(`${this.createPeriodApi}`,CreatePeriodRequest);
  }

  getHistoryBooking(): Observable<any> {
    return this.http.get<any>(`${this.getBookingHistory}`);
  }
}
