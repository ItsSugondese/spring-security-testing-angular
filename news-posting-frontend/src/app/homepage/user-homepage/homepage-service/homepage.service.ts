import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { news } from 'src/app/interface';

@Injectable({
  providedIn: 'root'
})
export class HomepageService {

  backendUrl : string = "http://localhost:9192/";

  constructor(private httpClient : HttpClient) { }

  getNews(){
    return this.httpClient.get<news[]>(this.backendUrl + "getNews")
  }
}
