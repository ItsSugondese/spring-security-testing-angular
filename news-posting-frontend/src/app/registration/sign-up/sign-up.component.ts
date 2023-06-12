import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SignUpService } from './signUp-service/sign-up.service';
import { Observable, Subscribable, Subscription } from 'rxjs';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit, OnDestroy {

  //Subscription
  postData$ !: Subscription;

  showReset: boolean = false;


  signUpForm !: FormGroup;
  formHeader = {
    status: "Register",
    color: "black"
  };

  constructor(private ss: SignUpService,
    private fb: FormBuilder) {

  }

  ngOnInit(): void {
    this.signUpForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.pattern(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)]],
      password: ['', Validators.required]
    });
  }

  handleSubmit() {
    if (this.signUpForm.valid) {
      console.log(this.signUpForm.value.username);
      this.postData$ = this.ss.postData(this.signUpForm).subscribe(
        (result) => {
          console.log(result);
          this.formHeader = {
            status: "Registration Successful",
            color: "green"
          };
          this.showReset = true;

        },
        (error) => {
          this.showReset = true;
          this.formHeader.color = "red";
          if (error.status == 500) {
            this.formHeader.status = "Error occured in server"
          }else if(error.status == 409) {
            this.formHeader.status = "User with that email already exists"
          }



        }
      )
    } else {
      console.log('Form is invalid');
    }
  }

  reset() {
    this.formHeader = {
      status : "Register",
      color : "Black"
    }
    this.showReset = false;
    this.signUpForm.reset();
  }


  ngOnDestroy(): void {
    if (this.postData$) {
      this.postData$.unsubscribe();
    }
  }











  get username() {
    return this.signUpForm.get('username');
  }

  get email() {
    return this.signUpForm.get('email');
  }

  get password() {
    return this.signUpForm.get('password');
  }

}

