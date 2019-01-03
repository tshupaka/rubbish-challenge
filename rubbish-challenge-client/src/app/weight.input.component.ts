
import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-weight-input',
  templateUrl: './weight.input.component.html',
  styleUrls: ['./weight.input.component.css']
})
export class WeightInputComponent implements OnInit {

  rubbishes: any;
  userId: number;

  constructor(private _route: ActivatedRoute,
    private _router: Router, ) {

  }

  ngOnInit() {
    this.userId = this._route.snapshot.params['id'];
    this.reloadRubbishes();
  }

  reloadRubbishes() {
   // this.rubbishService.getRubbishes(this.userId).subscribe(rubbishes => this.rubbishes = rubbishes);

  }

  addRubbish(form: NgForm) {
    const weight = form.value.weight;
    const nbUser = form.value.number;
   // this.rubbishService.addRubbish(this.userId, weight, nbUser).subscribe(rubbish => this.rubbishes.push(rubbish));

  }

  deleteRubbish(rubbishId: number) {
   // this.rubbishService.deleteRubbish(rubbishId).subscribe(result => this.reloadRubbishes());
  }
}
