import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StadiumService {


  private getAllStadium = 'http://localhost:9001/stadium-service/private/api/v1/all-area'
  private searchStadiumByCoordinates = 'http://localhost:9001/stadium-service/private/api/v1/search-field';


  constructor(private http: HttpClient) { }


  getAllStadiumPageable(): Observable<any>{
    return this.http.get<any>(`${this.getAllStadium}`)
  }

  getStadiumsCoordinates(findAreaRequest: any): Observable<any>{
    return this.http.post<any>(`${this.searchStadiumByCoordinates}`,findAreaRequest)
  }
}
