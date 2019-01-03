import { Component } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-register',
    templateUrl: 'register.component.html'
})
export class RegisterComponent {
    email: string;
    registerOk: boolean = false;
    userAlreadyExist: boolean = false;
    loading: boolean = false;

    constructor(private authenticationService: AuthenticationService,
        private activeModal: NgbActiveModal) { }

    register() {
        this.loading = true;
        this.authenticationService.register(this.email).subscribe(
            data => {
                console.log(data);
                this.registerOk = true;
                this.loading = false;
            }, error => {
                const message = error.error;
                this.loading = false;
                switch (error.status) {
                    case 401:
                        if (message !== undefined && message.code === 100) {
                            this.userAlreadyExist = true;
                        }
                }
                console.log(error);
            });

    }
}
