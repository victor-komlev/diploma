(function () {
    var notificationServices = angular.module("Notification.Services", []);

    notificationServices.factory("NotificationService", ["$rootScope", "$window",
        function ($rootScope, $window) {

            var sockJSsocket = null,
                serviceAddress;

            function connect(statsServiceAddress) {
                serviceAddress = statsServiceAddress + "/main-stats-unit/subscribe/subscribeme";
                sockJSsocket = new SockJS(serviceAddress);
                sockJSsocket.onopen = function () {
                    console.log("NotificationService::onopen()");
                    var sessionId = "SESSION";
                    var subscriptoinTopic = '{\"sessionId\":\"' + sessionId + '\", \"clientType\":\"WebClient\"}';
                    sockJSsocket.send(subscriptoinTopic);
                };

                sockJSsocket.onmessage = function (message) {
                    var messageObject = $window.angular.fromJson(message.data);
                    if (messageObject.route == "EMPLOYEE") {
                        var taskStats = [];
                        for (var employeeTime in messageObject.employeesTaskTimeAverage) {
                            if (messageObject.employeesTaskTimeAverage != null && messageObject.employeesTaskTimeAverage[employeeTime] != null) {
                                taskStats.push({
                                    "employee": messageObject.employeesTaskTimeAverage[employeeTime].employeeName,
                                    "value": messageObject.employeesTaskTimeAverage[employeeTime].avgTime
                                });
                            }
                        }
                        $rootScope.$broadcast("EmployeeTaskAvg", taskStats);
                        var actionStats = [];
                        for (var employeeTime in messageObject.employeesAverageActionTime) {
                            if (messageObject.employeesAverageActionTime != null && messageObject.employeesAverageActionTime[employeeTime] != null) {

                                actionStats.push({
                                    "actionType": messageObject.employeesAverageActionTime[employeeTime].actionType,
                                    "employee": messageObject.employeesAverageActionTime[employeeTime].employeeName,
                                    "value": messageObject.employeesAverageActionTime[employeeTime].avgTime
                                });
                            }
                        }
                        $rootScope.$broadcast("EmployeeActionAvg", actionStats);

                    }
                    else if (messageObject.route == "COMPANY") {
                        var actionStats = [];
                        for (var itemId in messageObject.perActionAverageTimes) {
                            if (messageObject.perActionAverageTimes != null && messageObject.perActionAverageTimes[itemId] != null) {

                                actionStats.push({
                                    "actionType": messageObject.perActionAverageTimes[itemId]._id,
                                    "value": messageObject.perActionAverageTimes[itemId].avgTime
                                });
                            }
                        }
                        $rootScope.$broadcast("CompanyActionAvg", actionStats);

                        $rootScope.$broadcast("CompanyTaskAvg", messageObject.taskAverageTime);

                    }
                };

                sockJSsocket.onclose = function () {
                    console.log("NotificationService::onclose()");
                };

                sockJSsocket.onerror = function () {
                    console.log("NotificationService::onerror()");
                };


            }

            function disconnect() {
                sockJSsocket.close();
            }

            return {
                connect: connect,
                disconnect: disconnect
            };
        }
    ])
    ;

})
();