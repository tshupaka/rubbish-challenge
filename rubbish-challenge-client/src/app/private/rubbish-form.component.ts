import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Rubbish } from '../domain/rubbish';
import { RubbishService } from './rubbish.service';

@Component({
  selector: 'app-rubbish-form',
  templateUrl: './rubbish-form.component.html',
  styleUrls: ['./rubbish-form.component.css']
})
export class RubbishFormComponent implements OnInit {

  @Output()
  addNewRubbish: EventEmitter<Rubbish> = new EventEmitter<Rubbish>();
  rubbish: Rubbish;
  types: any[];

  constructor(private rubbishService: RubbishService) { }

  ngOnInit() {
    const today = new Date();
    this.rubbish = new Rubbish(today.toISOString().substring(0, 10), null, null, null);
    this.rubbishService.getRubbishType().subscribe(types => { this.types = types; });
  }



  addRubbish() {
    this.rubbishService.addRubbish(this.rubbish).subscribe(result => {
      this.rubbish.number = null;
      this.rubbish.weight = null;
      this.rubbish.type = null;
      console.log(result);
      this.addNewRubbish.emit(result);
    });
  }
}
