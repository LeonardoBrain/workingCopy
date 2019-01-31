angular.module('iw3').config(
                        function($routeProvider,$locationProvider){



                            $routeProvider
                                .when('/main', {
                                    templateUrl : 'views/main.html',
                                    controller : 'MainController'
                                })
                                .when('/backlog', {
                                    templateUrl : 'views/backlog.html',
                                    controller : 'TaskController'
                                })
                                .when('/activeSprint', {
                                    templateUrl : 'views/activeSprint.html',
                                    controller : 'TaskController'
                                })
                                .otherwise({
                                    redirectTo : '/main'
                                });

                            $locationProvider.hashPrefix('!');
                            //$locationProvider.html5Mode({enabled:true, requireBase:false});




});