angular.module('iw3')
    .factory('taskListService',function ($http, URL_API_BASE) {

        return{
            getAllSprints : function () {
                return $http.get(URL_API_BASE+"taskList/sprints");

            },
            getAllTaskListsBySprint : function(sprintName){
                return $http.get(URL_API_BASE+"taskList?sprint_name="+sprintName+"");
            },
        }

    });