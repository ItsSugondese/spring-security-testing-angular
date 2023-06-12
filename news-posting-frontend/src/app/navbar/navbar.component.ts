import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserService } from '../user-service/user.service';
import { NavbarService } from './navbar-service/navbar.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnDestroy {

  checkToken$ !: Subscription;
  constructor(public userService : UserService,
    private navbarService : NavbarService){

  }
 
  ngOnInit(): void {
    // this.checkToken$ = this.navbarService.verify().subscribe();
  }

  loggingOut(){
    this.userService.clear()
  }


  ngOnDestroy(): void {
    // if(this.checkToken$){
    //   this.checkToken$.unsubscribe();
    // }
  }

}
