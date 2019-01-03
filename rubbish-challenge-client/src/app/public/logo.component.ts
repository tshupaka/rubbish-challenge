
import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from '../authentication/login.component';
import { RegisterComponent } from '../authentication/register.component';

@Component({
    moduleId: module.id,
    selector: 'app-logo',
    templateUrl: 'logo.component.html',
    styleUrls: ['logo.component.css']
})
export class LogoComponent {

    constructor(private modalService: NgbModal) { }

    openConnect(): any {
        const mondalConnect = this.modalService.open(LoginComponent);
    }

    openRegister(): any {
        this.modalService.open(RegisterComponent);
    }
}
