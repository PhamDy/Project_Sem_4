import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { KeyValue } from 'src/app/models/keyValue';
import { TimeFrame } from 'src/app/models/timeFrame';

const urlRoot = "http://localhost:3000"

@Injectable({
  providedIn: 'root'
})
export class TimeFrameServiceService {

  constructor(private http:HttpClient) { }

  getListTimeFrame(): Observable<TimeFrame[]> {
    return this.http.get<TimeFrame[]>(urlRoot + '/timeFrame');
  }

  getListType(): Observable<KeyValue[]> {
    return this.http.get<KeyValue[]>(urlRoot + '/type');
  }

}
