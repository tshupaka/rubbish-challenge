import { Component, OnInit } from '@angular/core';
import { Group } from '../../domain/group';
import { GroupStatistic } from '../../domain/group-statistic';
import { RubbishService } from '../rubbish.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { GroupManagerPopupComponent } from './group-manager-popup.component';

@Component({
  selector: 'app-info-group',
  templateUrl: './info-group.component.html',
  styleUrls: ['./info-group.component.css']
})
export class InfoGroupComponent implements OnInit {

  constructor(private rubbishService: RubbishService, private modalService: NgbModal) { }

  private groupStatistics: GroupStatistic[];
  private groupId: number;

  ngOnInit() {
  }

  updateGroup(group: Group) {
    this.groupId = group.id;
    this.rubbishService.getGroupStatistics(group.id).subscribe(groupStatistics => this.groupStatistics = groupStatistics);
  }

  openGroupManager() {
    const popupInstance = this.modalService.open(GroupManagerPopupComponent).componentInstance;
    popupInstance.groupId = this.groupId;
  }
}
