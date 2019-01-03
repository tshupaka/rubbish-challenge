import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import { HeaderComponent } from './header.component';
import { LogoComponent } from './logo.component';
import { SpeechComponent } from './speech.component';
import { NewsComponent} from './news.component';
import { FooterComponent} from './footer.component';
import { AuthenticationModule } from '../authentication/authentication.module';
import { BrowserModule } from '@angular/platform-browser';


@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    AuthenticationModule
  ],
  declarations: [MainComponent, HeaderComponent, LogoComponent, SpeechComponent, NewsComponent, FooterComponent]
})
export class PublicModule { }
