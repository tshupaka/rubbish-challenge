import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { Router } from '@angular/router';


@Component({
    selector: 'app-register-valid',
    templateUrl: 'register-valid.component.html'
})
export class RegisterValidComponent implements OnInit {


    constructor(private authenticationService: AuthenticationService, private router: Router) {

    }
    ngOnInit(): void {
        const id: string = this.router.parseUrl(this.router.url).queryParams['id'];
        if (id) {
            this.authenticationService.validRegister(id).subscribe(user => { }
            );
        }
    }
}
