import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from '../authentication/login.component';
import { RegisterComponent } from '../authentication/register.component';

@Component({
    selector: 'app-header',
    templateUrl: 'header.component.html',
    styleUrls: ['header.component.css']
})
export class HeaderComponent {

    constructor(private modalService: NgbModal) { }

    openConnect(): any {
        const mondalConnect = this.modalService.open(LoginComponent);
    }

    openRegister(): any {
        this.modalService.open(RegisterComponent);
    }
}
