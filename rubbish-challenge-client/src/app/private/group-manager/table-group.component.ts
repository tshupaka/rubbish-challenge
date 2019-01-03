import { Component, OnInit, Input } from '@angular/core';
import { GroupStatistic } from '../../domain/group-statistic';
import { UserRubbishSummary } from '../../domain/user-rubbish-summary';

@Component({
  selector: 'app-table-group',
  templateUrl: './table-group.component.html',
  styleUrls: ['./table-group.component.css']
})
export class TableGroupComponent implements OnInit {

  @Input()
  userRubbishSummaries: UserRubbishSummary[];

  constructor() { }

  ngOnInit() {
  }

}
