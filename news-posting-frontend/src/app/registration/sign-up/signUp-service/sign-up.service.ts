import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private httpClient : HttpClient) { }

  postData(formData : FormGroup){
    const formDataValue = formData.value;
    return this.httpClient.post<FormGroup>("http://localhost:9192/addUser", formDataValue);
  }
}
