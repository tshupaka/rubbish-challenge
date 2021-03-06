import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';


@Injectable()
export class AuthenticationService {

    constructor(private http: HttpClient) { }

    logout() {

    }

    login(email, password): Observable<any> {
        return this.http.post('api/auth', { 'email': email, 'password': password }, { observe: 'response' })
            .pipe(map((response: HttpResponse<any>) => {
                console.log(response.headers);
                const user = response.headers.get('Authorization');
                console.log('token', user);
                localStorage.setItem('currentUser', user);
                return user;

            }),
            catchError((error: any) => Observable.throw(error || 'Server error')));
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
