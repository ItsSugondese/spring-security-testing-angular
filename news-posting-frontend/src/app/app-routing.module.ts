import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './registration/login/login.component';
import { SignUpComponent } from './registration/sign-up/sign-up.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AdminHomepageComponent } from './homepage/admin-homepage/admin-homepage.component';
import { UserHomepageComponent } from './homepage/user-homepage/user-homepage.component';
import { AuthGuard } from './_auth/auth.guard';
import { TestComponent } from './test/test.component';
import { GlobalComponent } from './global/global.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NewsComponent } from './news/news.component';

const routes: Routes = [
  {path : 'login', component: LoginComponent},
  {path : 'register', component: SignUpComponent},
  {path : 'forbidden', component : ForbiddenComponent},
  {path : 'adminHomepage', component : AdminHomepageComponent, canActivate:[AuthGuard], data: {roles:['Admin']}},
  {path : 'userHomepage', component : UserHomepageComponent, canActivate:[AuthGuard], data: {roles:['User', 'Admin']}},
  {path : 'userHomepage/:nId',  component: NewsComponent, canActivate:[AuthGuard], data: {roles:['User', 'Admin']}},
  {path:'test', component : TestComponent, canActivate: [AuthGuard], data: {roles:['User', 'Admin']}},
  {path : '', redirectTo: '/userHomepage', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule 
{ }
