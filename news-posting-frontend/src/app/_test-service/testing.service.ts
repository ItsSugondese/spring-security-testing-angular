import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TestingService {

  backendUrl : string = "http://localhost:9192/";
  constructor(private client : HttpClient) { }

  getValue(){
    return this.client.get(this.backendUrl + "forUser", {
      responseType: 'text',
    });
  }
}
