import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './registration/login/login.component';
import { SignUpComponent } from './registration/sign-up/sign-up.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { UserHomepageComponent } from './homepage/user-homepage/user-homepage.component';
import { AdminHomepageComponent } from './homepage/admin-homepage/admin-homepage.component';
import { AuthInterceptor } from './_auth/auth.interceptor';
import { AuthGuard } from './_auth/auth.guard';
import { TestComponent } from './test/test.component';
import { GlobalComponent } from './global/global.component';
import { NewsComponent } from './news/news.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    SignUpComponent,
    ForbiddenComponent,
    UserHomepageComponent,
    AdminHomepageComponent,
    TestComponent,
    GlobalComponent,
    NewsComponent
  ],
  imports: [
    
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    
  ],
  providers: [
    AuthGuard,
    {
      provide : HTTP_INTERCEPTORS,
      useClass : AuthInterceptor,
      multi : true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
