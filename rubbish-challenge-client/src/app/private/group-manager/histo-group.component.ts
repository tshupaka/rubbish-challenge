import { Component, OnInit, Input, SimpleChange, OnChanges } from '@angular/core';
import { HistoGroup } from '../../domain/histo-group';

@Component({
    selector: 'app-histo-group',
    templateUrl: './histo-group.component.html',
    styleUrls: ['./histo-group.component.css']
})
export class HistoGroupComponent implements OnInit, OnChanges {

    @Input()
    histoGroup: HistoGroup[];


    rubbishGraph: any;

    constructor() { }

    ngOnInit() {
        console.log('HistoGroupe: ', this.histoGroup);
        // this.createInstantSerial(this.histoGroup);
    }

    ngOnChanges(changes: { [propName: string]: SimpleChange }) {
        const histoGroups = changes.histoGroup.currentValue;
        this.createInstantSerial(histoGroups);
    }

    createInstantSerial(histoGroups: HistoGroup[]) {
        if (histoGroups == null) {
            return;
        }
        const dataProvider = [];
        const usernames = {};
        histoGroups.forEach(data => {

            const date = data.date;
            const values = data.values;
            const lineProvider = {};
            lineProvider['date'] = date;
            values.forEach(value => {
                const username: any = value.username;
                const weight = value.weight;
                lineProvider[username] = weight;
                usernames[username] = 1;
            });
            dataProvider.push(lineProvider);


        });

        const graph = [];
        Object.keys(usernames).forEach(value => {
            const lineGraph = {
                'id': value,
                'valueField': value,
                'title': value,
                'type': 'smoothedLine',
            };
            graph.push(lineGraph);
        });

        this.rubbishGraph = AmCharts.makeChart('rubbishGroupGraphHisto', {
            'type': 'serial',
            'categoryField': 'date',
            'legend': {
                'useGraphSettings': true
            },
            'dataProvider': dataProvider,
            'graphs': graph,
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
