import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StadiumService {

  private baseUrl = 'http://localhost:9001/stadium-service/private/api/v1';
  private getAllStadium = `${this.baseUrl}/all-area`;
  private searchStadiumByCoordinates = `${this.baseUrl}/search-field`;

  constructor(private http: HttpClient) { }

  // Lấy tất cả sân vận động
  getAllStadiumPageable(): Observable<any> {
    return this.http.get<any>(this.getAllStadium);
  }

  // Tìm kiếm sân theo tọa độ
  getStadiumsCoordinates(findAreaRequest: any): Observable<any> {
    return this.http.post<any>(this.searchStadiumByCoordinates, findAreaRequest);
  }

  // 🔥 API mới: Lấy danh sách sân theo `id` của `area`
  getFieldsByAreaId(areaId: any): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/find-area-by-id?id=${areaId}`);
  }
}
