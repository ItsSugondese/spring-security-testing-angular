import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { news } from 'src/app/interface';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  backendUrl : string = "http://localhost:9192/"

  constructor(private httpClient : HttpClient) { }

  getNews(nId : Number){
    return this.httpClient.get<news>(this.backendUrl + "getNews/" + nId);
  }
}
