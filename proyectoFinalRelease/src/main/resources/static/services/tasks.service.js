angular.module('iw3')
.factory('tasksService',function ($http, URL_API_BASE) {

    return{
        loadBacklog : function () {
            return $http.get(URL_API_BASE+"task/all?list_name=Backlog&sprint_name=Backlog&order_by=*");

        },
        addTask : function(task){
            return $http.post(URL_API_BASE+"task",task);
        },

        getTasksByTaskList : function (id) {
            return $http.get(URL_API_BASE + "task?list_id=" + id + "");

        },

        getTasksByListAndSprintName : function (listName, sprintName, orderBy) {
            return $http.get(URL_API_BASE+"task/all?list_name="+listName+"&sprint_name="+sprintName+"&order_by="+orderBy+"");

        },

        moveTask : function(id, task){
            return $http.put(URL_API_BASE+"task/?id="+id+"",task);
        },
        deleteTask: function (id) {
            return $http.delete(URL_API_BASE+"task/"+id+"");

        }

    }

});