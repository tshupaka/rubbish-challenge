import { Component, OnInit } from '@angular/core';
import { RubbishService } from './rubbish.service';
import { Rubbish } from '../domain/rubbish';

@Component({
    selector: 'app-rubbish-graph-history',
    templateUrl: './rubbish-graph-history.component.html',
    styleUrls: ['./rubbish-graph-history.component.css']
})
export class RubbishGraphHistoryComponent implements OnInit {



    rubbishes: Rubbish[];
    rubbishGraph: any;

    constructor(private rubbishService: RubbishService) {



    }

    ngOnInit() {
        this.createInstantSerial();
        this.rubbishService.getSmoothRubbishes().subscribe((rubbishes: Rubbish[]) => {
            this.rubbishes = rubbishes;
            console.log(rubbishes);

            this.rubbishGraph.dataProvider = rubbishes;
            this.rubbishGraph.validateData();
        });
    }


    createInstantSerial() {
        this.rubbishGraph = AmCharts.makeChart('rubbishGraphHisto', {
            'type': 'serial',
            'categoryField': 'date',
            'graphs': [
                {
                    'valueField': 'weight',
                    'type': 'smoothedLine',
                    /*'bullet': 'round',
                    'balloonText': '<span style='font-size:12px;'>[[weight]]</span>',

                    'bulletBorderAlpha': 1,
                    'bulletSize': 5,
                    'hideBulletsCount': 50,
                    'balloon': {
                        'drop': true,
                        'adjustBorderColor': false,
                    },*/
                }
            ],
            'categoryAxis': {
                'minPeriod': 'mm',
                'parseDates': true,
                'minorGridAlpha': 0.1,
                'minorGridEnabled': true
            },
            'dataDateFormat': 'YYYY-MM-DD',
            'chartCursor': {
                'categoryBalloonDateFormat': 'YYYY-MM-DD',
                'cursorAlpha': 0,
                'valueLineEnabled': true,
                'valueLineBalloonEnabled': true,
                'valueLineAlpha': 0.5,
                'fullWidth': true
            }
        });

    }
}
