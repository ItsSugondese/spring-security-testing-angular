import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { jwtResponse } from 'src/app/interface';

@Injectable({
  providedIn: 'root'
})
export class LoginService implements OnInit {

  backendUrl : string = "http://localhost:9192/"
  constructor(private httpClient : HttpClient) { }

  public formHeader = {
    status: "Login",
    color: "black"
  };

  ngOnInit(): void {

  }

 requestHeaders = new HttpHeaders(
  {"No-Auth" : "True"}
 ) ;

  login(userEmail : string, userPassword : string){
    const data = { userEmail, userPassword };
    return this.httpClient.post<jwtResponse>(this.backendUrl + "authenticate", data, {headers : this.requestHeaders});
  }

  public setFormHeader(status: string, color: string){
    this.formHeader = {
      status: status,
    color: color
    }
  }
}
