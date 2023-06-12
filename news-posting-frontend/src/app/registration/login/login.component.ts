import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs/internal/Subscription';
import { LoginService } from './login-service/login.service';
import { jwtResponse, loginFormHeader } from 'src/app/interface';
import { UserService } from 'src/app/user-service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
 

  loginForm !: FormGroup

  postData$ !: Subscription;

  formHeader !: loginFormHeader;

  constructor(private fb : FormBuilder,
    private ls : LoginService,
    private us : UserService,
    private router : Router){

      this.formHeader = ls.formHeader;
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)]],
      password: ['', Validators.required]
    });
  }


  handleSubmit(){
    if (this.loginForm.valid) {
      console.log(this.loginForm.value.email);

      const email = this.loginForm.value.email;
      const password = this.loginForm.value.password;
      this.postData$ = this.ls.login(email, password).subscribe(
        (result) => {
          this.formHeader = {
            status : "Login Successful",
            color : "Green"
          }

          console.log(result)
          this.us.setRoles(result.user.role);
          this.us.setToken(result.jwtToken);
          this.us.setUsername(result.user.username);

          const role = result.user.role[0].role;
          console.log(role);
          if(role == "Admin"){
            this.router.navigate(['/adminHomepage'])
          } 
          else{
            console.log(role)
            setInterval(() => {
              this.router.navigate(['/userHomepage'])
            }, 2000);
            
          }
        // this.us.setToken()
        },
        (error) => {
         
          this.formHeader.color = "red";
          if (error.status == 500) {
            this.formHeader.status = "Error occured in server"
          } else if(error.status == 401){
            this.formHeader.status = "Invalid login details"
          }  else {
            this.formHeader.status = error.message;
          }
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }



  get email(){
    return this.loginForm.get('email');
  }

  get password(){
    return this.loginForm.get('password');
  }

  public setFormHeader(status: string, color: string){
    this.formHeader = {
      status: "Login",
    color: "black"
    }
  }

}
