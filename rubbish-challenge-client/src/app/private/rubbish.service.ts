import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Rubbish } from '../domain/rubbish';
import { Group } from '../domain/group';
import { GroupStatistic } from '../domain/group-statistic';
import { HistoGroup } from '../domain/histo-group';

@Injectable()
export class RubbishService {


  constructor(private http: HttpClient) { }

  getUsers(): Observable<any> {
    return this.http.get('api/users');
  }


  addRubbish(rubbish: Rubbish): Observable<any> {
    const user: any = localStorage.getItem('currentUser');
    const headers = new HttpHeaders().set('authorization', user);
    return this.http.post('api/rubbish', rubbish, { headers: headers });
  }


  getRubbishes() {
    const user: any = localStorage.getItem('currentUser');
    const headers = new HttpHeaders().set('authorization', user);
    const params: HttpParams = new HttpParams().set('type', 'list');
    return this.http.get('api/rubbishes/', { 'headers': headers, 'params': params }).map((rubbishes: Rubbish[]) => {

      rubbishes.forEach((rubbish: Rubbish) => {
        rubbish.date = new Date(rubbish.date).toISOString().substr(0, 10);
      }
      );
      return rubbishes;

    });
  }

  getSmoothRubbishes(): any {
    const user: any = localStorage.getItem('currentUser');
    const headers = new HttpHeaders().set('authorization', user);
    const params: HttpParams = new HttpParams().set('type', 'smoothed');
    return this.http.get('api/rubbishes', { 'headers': headers, 'params': params })
      .map((rubbishes: Rubbish[]) => {
        console.log('retour http : ', rubbishes);
        rubbishes.forEach((rubbish: Rubbish) => {
          rubbish.date = new Date(rubbish.date).toISOString().substr(0, 10);
        });
        return rubbishes;
      });
  }

  deleteRubbish(rubbishId: number) {
    return this.http.delete('api/rubbish/' + rubbishId);
  }

  getRubbishType(): any {
    const user: any = localStorage.getItem('currentUser');
    const headers = new HttpHeaders().set('authorization', user);
    return this.http.get('api/rubbishtypes', { headers: headers });
  }


  getGroups(): Observable<any> {
    const user: any = localStorage.getItem('currentUser');
    const headers = new HttpHeaders().set('authorization', user);
    return this.http.get('api/groups', { 'headers': headers });
    // .map((res:any) => {return res.map(item => {item.active=true;return item})});

  }


  getGroupStatistics(groupId: number): Observable<any> {

    const user: any = localStorage.getItem('currentUser');
    const headers = new HttpHeaders().set('authorization', user);
    return this.http.get('api/statistics/group/' + groupId, { 'headers': headers })
      .map((groupStatistics: GroupStatistic) => {
        groupStatistics.histoGroups.forEach((histoGroup: HistoGroup) => {
          histoGroup.date = new Date(histoGroup.date).toISOString().substr(0, 10);
        });
        console.log(groupStatistics);
        return groupStatistics;
      });
  }


  setInvitation(groupId: number, email: string, message: string): any {
    const user: any = localStorage.getItem('currentUser');
    const headers = new HttpHeaders().set('authorization', user);
    return this.http.post('api/group/invitation/' + groupId, {email : email, message : message}, { 'headers': headers });
  }
}
