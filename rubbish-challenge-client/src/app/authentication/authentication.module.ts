import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { AuthenticationService } from './authentication.service';
import { RegisterComponent } from './register.component';
import { RegisterValidComponent} from './register-valid.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RegisterAcceptComponent } from './register-accept.component';
import { RegisterDeclineComponent } from './register-decline.component';

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    RegisterValidComponent,
    RegisterAcceptComponent,
    RegisterDeclineComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    RouterModule.forRoot( [
                    {path: 'login', component: LoginComponent},
                    {path: 'register', component: RegisterComponent},
                    {path: 'register/valid', component: RegisterValidComponent},
                    {path: 'register/accept', component: RegisterAcceptComponent},
                    {path: 'register/decline', component: RegisterDeclineComponent},
                    {path : '', redirectTo : 'login', pathMatch : 'full'}
                  ]),
  ],
  providers: [AuthenticationService],
  exports: [LoginComponent]
})
export class AuthenticationModule { }
