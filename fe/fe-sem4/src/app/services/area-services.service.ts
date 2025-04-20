import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AreaServicesService {
  private getAreaById = "http://localhost:9001/stadium-service/private/api/v1/area"
  constructor(private http: HttpClient) { }

  // api chi tiết area
  getDetailById(id: any): Observable<any> {
    return this.http.get<any>(`${this.getAreaById}/${id}/field`);
  }
}
