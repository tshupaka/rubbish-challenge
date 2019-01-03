/// <reference path='../assets/typings/AmCharts.d.ts' />

import { Component } from '@angular/core';
import { templateSourceUrl } from '@angular/compiler';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
    selector: 'app-statistics',
    templateUrl: 'statistics.component.html',
    styleUrls: ['statistics.component.css']
})
export class StatisticsComponent implements OnInit {

    users: any[];
    usersSelected: any[];

    constructor() {

    }


    setUserClicked(userId: number): void {
        this.usersSelected[userId] = !this.usersSelected[userId];
        console.log(this.usersSelected);
    }

    ngOnInit(): void {
        this.usersSelected = [{}];
        /* this.rubbishService.getUsers().subscribe(
             users => {
                 this.users = users;
                 for(let user in users){
                     this.usersSelected[user['id']] = false;
                 }
             });
 */



        this.initYearlyGraph([
            { 'date': '2017-06-04', 'value': '1250' },
            { 'date': '2017-06-05', 'value': '2048' },
            { 'date': '2017-06-06', 'value': '1410' },
            { 'date': '2017-06-07', 'value': '950' },
            { 'date': '2017-06-08', 'value': '4350' },
            { 'date': '2017-06-09', 'value': '1478' },
            { 'date': '2017-06-10', 'value': '6521' },
            { 'date': '2017-06-11', 'value': '0235' },
            { 'date': '2017-06-12', 'value': '1452' },
            { 'date': '2017-06-13', 'value': '6511' },
            { 'date': '2017-06-14', 'value': '3214' },
            { 'date': '2017-06-15', 'value': '2513' },
            { 'date': '2017-06-16', 'value': '2897' },
            { 'date': '2017-06-17', 'value': '1895' },
            { 'date': '2017-06-18', 'value': '4515' },
            { 'date': '2017-06-19', 'value': '4254' },
            { 'date': '2017-06-20', 'value': '3215' }

        ]);
        this.init3MonthPodium([{
            'name': 'John',
            'points': 35654,
            'color': '#7F8DA9',
            'bullet': 'https://www.amcharts.com/lib/images/faces/A04.png'
        }, {
            'name': 'Damon',
            'points': 65456,
            'color': '#FEC514',
            'bullet': 'https://www.amcharts.com/lib/images/faces/C02.png'
        }, {
            'name': 'Patrick',
            'points': 45724,
            'color': '#DB4C3C',
            'bullet': 'https://www.amcharts.com/lib/images/faces/D02.png'
        }, {
            'name': 'Mark',
            'points': 13654,
            'color': '#DAF0FD',
            'bullet': 'https://www.amcharts.com/lib/images/faces/E01.png'
        }]);

        this.initTotalPodium([{
            'name': 'John',
            'points': 35654,
            'color': '#7F8DA9',
            'bullet': 'https://www.amcharts.com/lib/images/faces/A04.png'
        }, {
            'name': 'Damon',
            'points': 65456,
            'color': '#FEC514',
            'bullet': 'https://www.amcharts.com/lib/images/faces/C02.png'
        }, {
            'name': 'Patrick',
            'points': 45724,
            'color': '#DB4C3C',
            'bullet': 'https://www.amcharts.com/lib/images/faces/D02.png'
        }, {
            'name': 'Mark',
            'points': 13654,
            'color': '#DAF0FD',
            'bullet': 'https://www.amcharts.com/lib/images/faces/E01.png'
        }]);
    }

    initYearlyGraph(data: any) {


        console.log('Chargement data : ' + data);
        const chart2 = AmCharts.makeChart('histo', {
            'type': 'serial',
            'categoryField': 'date',
            'graphs': [
                {
                    'valueField': 'value'
                }
            ],
            'dataProvider': data,
            'categoryAxis': {
                'minPeriod': 'mm',
                'parseDates': true,
                'minorGridAlpha': 0.1,
                'minorGridEnabled': true
            },
            'chartCursor': {
                'categoryBalloonDateFormat': 'YYYY-MM-DD JJ:NN',
                'cursorAlpha': 0,
                'valueLineEnabled': true,
                'valueLineBalloonEnabled': true,
                'valueLineAlpha': 0.5,
                'fullWidth': true
            },
        });

    }


    init3MonthPodium(data: any) {
        const chart = AmCharts.makeChart('podium3Month',
            {
                'type': 'serial',
                'theme': 'light',
                'dataProvider': data,
                'valueAxes': [{
                    'maximum': 80000,
                    'minimum': 0,
                    'axisAlpha': 0,
                    'dashLength': 4,
                    'position': 'left'
                }],
                'startDuration': 1,
                'graphs': [{
                    'balloonText': '<span style="font-size: 13px; ">[[category]]: <b>[[value]]</b></span>',
                    'bulletOffset': 10,
                    'bulletSize': 52,
                    'colorField': 'color',
                    'cornerRadiusTop': 8,
                    'customBulletField': 'bullet',
                    'fillAlphas': 0.8,
                    'lineAlpha': 0,
                    'type': 'column',
                    'valueField': 'points'
                }],
                'marginTop': 0,
                'marginRight': 0,
                'marginLeft': 0,
                'marginBottom': 0,
                'autoMargins': false,
                'categoryField': 'name',
                'categoryAxis': {
                    'axisAlpha': 0,
                    'gridAlpha': 0,
                    'inside': true,
                    'tickLength': 0
                },
                'export': {
                    'enabled': true
                }
            });
    }

    initTotalPodium(data: any) {
        const chart = AmCharts.makeChart('podiumTotal',
            {
                'type': 'serial',
                'theme': 'light',
                'dataProvider': data,
                'valueAxes': [{
                    'maximum': 80000,
                    'minimum': 0,
                    'axisAlpha': 0,
                    'dashLength': 4,
                    'position': 'left'
                }],
                'startDuration': 1,
                'graphs': [{
                    'balloonText': '<span style="font-size: 13px; ">[[category]]: <b>[[value]]</b></span>',
                    'bulletOffset': 10,
                    'bulletSize': 52,
                    'colorField': 'color',
                    'cornerRadiusTop': 8,
                    'customBulletField': 'bullet',
                    'fillAlphas': 0.8,
                    'lineAlpha': 0,
                    'type': 'column',
                    'valueField': 'points'
                }],
                'marginTop': 0,
                'marginRight': 0,
                'marginLeft': 0,
                'marginBottom': 0,
                'autoMargins': false,
                'categoryField': 'name',
                'categoryAxis': {
                    'axisAlpha': 0,
                    'gridAlpha': 0,
                    'inside': true,
                    'tickLength': 0
                },
                'export': {
                    'enabled': true
                }
            });
    }
}
