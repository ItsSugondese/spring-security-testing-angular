import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {

  backendUrl : string = "http://localhost:9192/"

  constructor(private httpClient : HttpClient) { }

  verify(){
    return this.httpClient.get(this.backendUrl + "checking");
  }
}
