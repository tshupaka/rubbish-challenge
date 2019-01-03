import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ChangePasswordComponent } from './change-password.component';
import { HomeComponent } from './home.component';
import { PrivateHeaderComponent } from '../private/private-header.component';
import { RubbishFormComponent } from './rubbish-form.component';
import { RubbishHistoryComponent } from './rubbish-history.component';
import { GroupManagerComponent } from './group-manager/group-manager.component';
import { RubbishService } from './rubbish.service';
import { RubbishGraphHistoryComponent } from './rubbish-graph-history.component';
import { GroupMenuComponent } from './group-manager/group-menu.component';
import { InfoGroupComponent } from './group-manager/info-group.component';
import { TableGroupComponent } from './group-manager/table-group.component';
import { HistoGroupComponent } from './group-manager/histo-group.component';
import { GroupManagerPopupComponent } from './group-manager/group-manager-popup.component';

@NgModule({
  declarations: [
    PrivateHeaderComponent,
    ChangePasswordComponent,
    HomeComponent,
    RubbishFormComponent,
    RubbishHistoryComponent,
    RubbishGraphHistoryComponent,
    GroupManagerComponent,
    GroupMenuComponent,
    InfoGroupComponent,
    TableGroupComponent,
    HistoGroupComponent,
    GroupManagerPopupComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
  ],
  providers: [RubbishService],
  exports: [HomeComponent],
  entryComponents: [GroupManagerPopupComponent]
})
export class PrivateModule { }
