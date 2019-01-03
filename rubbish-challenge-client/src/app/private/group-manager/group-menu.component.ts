import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Group } from '../../domain/group';
import { RubbishService } from '../rubbish.service';

@Component({
  selector: 'app-group-menu',
  templateUrl: './group-menu.component.html',
  styleUrls: ['./group-menu.component.css']
})
export class GroupMenuComponent implements OnInit {

  groups: Group[];
  @Output()
  selectGroup: EventEmitter<Group> = new EventEmitter<Group>();

  constructor(private rubbishService: RubbishService) { }

  ngOnInit() {
    this.rubbishService.getGroups()
      .subscribe((groups: Group[]) => {
        this.groups = groups;
        if (this.groups.length > 0) {
          groups[0].active = true;
          this.selectGroup.emit(groups[0]);
        }
      });
  }


  setGroupActive(group: Group) {
    this.groups.forEach(group1 => group1.active = false);
    group.active = true;
    this.selectGroup.emit(group);
  }

}
