import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
    moduleId: module.id,
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['login.component.css']
})
export class LoginComponent implements OnInit {

    model: any = {};
    loading = false;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        public activeModal: NgbActiveModal) { }


    ngOnInit() {
        // reset login status
        this.authenticationService.logout();


    }

    login() {
        console.log(this.model.username + '-' + this.model.password);
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
                user => {
                    this.activeModal.close('close');
                    localStorage.setItem('token', user.jwtToken);
                    this.router.navigate(['home']);
                },
                error => {
                    console.log('Erreur', error);
                    this.loading = false;
                });
    }
}
