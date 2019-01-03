import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { RubbishService } from '../rubbish.service';

@Component({
  selector: 'app-group-manager-popup',
  templateUrl: './group-manager-popup.component.html',
  styleUrls: ['./group-manager-popup.component.css']
})
export class GroupManagerPopupComponent implements OnInit {


  public groupId: number;
  private email: string;
  private message: string;


  constructor(public rubbishService: RubbishService, public activeModal: NgbActiveModal) { }

  ngOnInit() {
  }

  closeModal() {
    this.activeModal.close('close');
  }


  sendInvitation() {
    this.rubbishService.setInvitation(this.groupId, this.email, this.message).subscribe();
  }
}
