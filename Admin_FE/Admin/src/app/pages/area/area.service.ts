import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { Observable } from 'rxjs';

const urlPublic = environment.apiUrlPublic;
const urlPrivate = environment.apiUrlPrivate;
const endPoint = '';

@Injectable({
  providedIn: 'root'
})
export class AreaService {
  private apiUrlPublic = `${environment.apiUrlPublic}`;
  private apiUrlPrivate = `${environment.apiUrlPrivate}`;

  constructor(private http:HttpClient) { }

  addArea(area: FormData): Observable<Number>{
    return this.http.post<number>(`${urlPrivate + 'area'}`,
      area
    );
  }

  getDistricts(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrlPublic + '/danhMucQuanHuyen');
  }

  getAllArea(param: any): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrlPrivate + '/area/list', { params: param });
  }

  createArea(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrlPrivate + '/area', data)
  }

  deleteArea(areaId : any): Observable<any> {
    return this.http.delete<any>(`${this.apiUrlPrivate}/area/${areaId}`)
  }

}
