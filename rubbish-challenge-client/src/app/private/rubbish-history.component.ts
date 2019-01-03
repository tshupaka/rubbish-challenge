import { Component, OnInit } from '@angular/core';
import { RubbishService } from './rubbish.service';
import { Rubbish } from '../domain/rubbish';

@Component({
  selector: 'app-rubbish-history',
  templateUrl: './rubbish-history.component.html',
  styleUrls: ['./rubbish-history.component.css']
})
export class RubbishHistoryComponent implements OnInit {


  rubbishes: Rubbish[];

  constructor(private rubbishService: RubbishService) { }

  ngOnInit() {
    this.rubbishService.getRubbishes().subscribe((rubbishes: Rubbish[]) => { this.rubbishes = rubbishes; });
  }

  addNewRubbish(rubbish: Rubbish) {
    this.rubbishes.push(rubbish);
  }
}
