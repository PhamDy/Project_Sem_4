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

  constructor(private http:HttpClient) { }

  addArea(area: FormData): Observable<Number>{
    return this.http.post<number>(`${urlPrivate + 'area'}`,
      area
    );
  }


}
