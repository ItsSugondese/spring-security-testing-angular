import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { news } from 'src/app/interface';

@Injectable({
  providedIn: 'root'
})
export class PostingService {

  backendUrl : string = "http://localhost:9192/";

  constructor(private httpClient : HttpClient) { }

  postNews(data : FormData){
    return this.httpClient.post<news>(this.backendUrl + "addNews",data);
  }
}
