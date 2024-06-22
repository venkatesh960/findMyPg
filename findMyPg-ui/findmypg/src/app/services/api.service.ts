import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://localhost:3363'; // Your backend API base URL

  constructor(private http: HttpClient) { }

  getData(): Observable<any> {
    return this.http.get(`${this.apiUrl}/your-endpoint`);
  }

  postData(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/your-endpoint`, data);
  }

  // Add other methods as needed
}
