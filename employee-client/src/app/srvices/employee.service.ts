import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { observable, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

    private baseUrl = "http://localhost:8080/employees";
  constructor(private http:HttpClient) { }

  getEmployee(id: number):Observable<any>{
    return this.http.get(`http://localhost:8080/employee/${id}`);
  }

  createEmployee(employee: object):Observable<any>{
    return this.http.post(`${this.baseUrl}`, employee);
  }

  updateEmployee(id: number, value:any): Observable<any>{
    return this.http.put(`http://localhost:8080/employee/${id}`, value);
  }

  deleteEmployee(id: number):Observable<any>{
    return this.http.delete(`http://localhost:8080/employee/${id}`, {responseType:'text'});
  }

  getEmployeeList():Observable<any>{
    return this.http.get(`${this.baseUrl}`);
  }
}
