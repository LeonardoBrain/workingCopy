angular.module("iw3",['ngRoute', 'ngSanitize', 'ngAnimate', 'ngTouch', 'ui.bootstrap',
    'ngSanitize', 'angularUtils.directives.dirPagination',
    'angucomplete-alt', 'ngLoadingSpinner', 'ui.select',
    'adaptv.adaptStrap', 'ngDragDrop', 'ui-notification',
    'chart.js', 'ngStomp', 'uiSwitch', 'dndLists'])
    .constant('URL_API_BASE', '/api/v1/')
    .constant('URL_BASE', '/')
    .run(['$rootScope','$log','$location','taskListService',
    function ($rootScope,$log,$location,taskListService) {
        $log.log("Iniciando....")

        $rootScope.sprints = [];


        $rootScope.getAllSprints = function () {
            taskListService.getAllSprints().then(
                function (resp) {
                    $rootScope.sprints = resp.data;
                },
                function (reason) {  }
            );

        };


        $rootScope.relocate = function(loc) {
            $location.path(loc);
        };

}]);