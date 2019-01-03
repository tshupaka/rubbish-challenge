import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';


@Injectable()
export class AuthenticationService {

    constructor(private http: HttpClient) { }

    logout() {

    }

    login(email, password): Observable<any> {
        return this.http.post('api/auth', { 'email': email, 'password': password }, { observe: 'response' })
            .map((response: HttpResponse<any>) => {
                console.log(response.headers);
                const user = response.headers.get('Authorization');
                console.log('token', user);
                localStorage.setItem('currentUser', user);
                return user;

            })
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }

    register(email): Observable<any> {
        return this.http.post('api/register', { 'email': email });
    }

    validRegister(id: string): Observable<any> {
        const params: HttpParams = new HttpParams().set('id', id);
        return this.http.get('api/register', { 'params': params });
    }


    registerAccept(id: string): Observable<any> {
        const params: HttpParams = new HttpParams().set('id', id);
        return this.http.get('api/register/accept', { 'params': params });
    }

    registerDecline(id: string): any {
        const params: HttpParams = new HttpParams().set('id', id);
        return this.http.get('api/register/decline', { 'params': params });
    }


}
