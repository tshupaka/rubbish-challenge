import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { WeightInputComponent } from './weight.input.component';
import { RouterModule } from '@angular/router';

import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { StatisticsComponent } from './statistics.component';
import { PublicModule } from './public/public.module';
import { PrivateModule } from './private/private.module';
import { MainComponent } from './public/main.component';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './private/home.component';


@NgModule({
  declarations: [
    AppComponent,
    WeightInputComponent,
    StatisticsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    PublicModule,
    PrivateModule,
    NgbModule.forRoot(),
    RouterModule.forRoot( [
                    {path: 'public', component: MainComponent},
                    {path: 'home', component: HomeComponent},
                    {path: 'stats/:id', component : StatisticsComponent},
                    {path : '', redirectTo : 'public', pathMatch : 'full'}
                  ]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
