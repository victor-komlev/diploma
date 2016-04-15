'use strict';

var app = window.angular.module('Stats', ["Stats.Notification", "chart.js"]);

app.run(["NotificationService", function (NotificationService) {
    NotificationService.connect("localhost:8080");
}]);

app.controller('TaskStatsCntrl', ['$scope', '$timeout', '$rootScope', function ($scope, $timeout, $rootScope) {
    $scope.options = {scaleShowVerticalLines: true};
    $scope.labels = [];
    $scope.series = ['Series A'];
    $scope.data = [[]];

    $rootScope.$on("EmployeeTaskAvg", function (e, taskStats) {

        $scope.data = [];
        $scope.labels = [];
        var values = [];
        $scope.labels.push('Company Average');
        values.push($scope.taskAvg);

        for (var taskStat in taskStats) {
            $scope.labels.push(taskStats[taskStat].employee);
            values.push(taskStats[taskStat].value);
        }
        $scope.data.push(values);
        $scope.$apply();
    });

    $rootScope.$on("CompanyTaskAvg", function (e, taskAvg) {
        $scope.taskAvg = taskAvg.avgTime;
        $scope.$apply();
    });
}]);

app.controller('ActionStatsCntrl', ['$scope', '$timeout', '$rootScope', function ($scope, $timeout, $rootScope) {
    $scope.optionsAction = {scaleShowVerticalLines: true};
    $scope.labelsAction = [];
    $scope.seriesAction = [];
    $scope.dataAction = [[]];

    $rootScope.$on("EmployeeActionAvg", function (e, actionStats) {

        $scope.dataAction = [];
        $scope.labelsAction = [];
        $scope.seriesAction = [];


        var workValues = [];
        var negotiationValues = [];
        var meetingValues = [];

        $scope.labelsAction.push('Company Average');

        workValues.push($scope.actionAvgWork);
        negotiationValues.push($scope.actionAvgNeg);
        meetingValues.push($scope.actionAvgMeet);

        for (var id in actionStats) {
            if ($scope.labelsAction.indexOf(actionStats[id].employee) < 0) {
                $scope.labelsAction.push(actionStats[id].employee);
            }
            if ($scope.seriesAction.indexOf(actionStats[id].actionType) < 0) {
                $scope.seriesAction.push(actionStats[id].actionType);
            }
            switch (actionStats[id].actionType) {
                case "WORK":
                    workValues.push(actionStats[id].value);
                    break;
                case "NEGOTIATE":
                    negotiationValues.push(actionStats[id].value);
                    break;
                case "MEETING":
                    meetingValues.push(actionStats[id].value);
                    break;

            }

        }
        $scope.dataAction.push(workValues);
        $scope.dataAction.push(negotiationValues);
        $scope.dataAction.push(meetingValues);
        $scope.$apply();
    });

    $rootScope.$on("CompanyActionAvg", function (e, actionStats) {
        $scope.actionAvgWork = actionStats[0].value;
        $scope.actionAvgNeg = actionStats[1].value;
        $scope.actionAvgMeet = actionStats[2].value;
        $scope.$apply();
    });
}]);
