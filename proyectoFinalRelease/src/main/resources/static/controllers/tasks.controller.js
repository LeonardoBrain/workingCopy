angular.module('iw3')
.controller('TaskController', function ($rootScope, $scope, $http, tasksService,$uibModal, taskListService) {

    $scope.title = "Backlog";

    $scope.dataSource = [];
    $scope.instancia = {};
    $scope.taskListToAdd={};
    $scope.actualSprint = $rootScope.sprints[0] || 'Sprint1';
    $scope.actualOrder = ' ';
    $scope.taskLists = {};
    $scope.backLogIdCurrentSprint=0;
    $scope.sprintToAdd ='';

//INICIALIZAR LAS LISTAS
    $scope.models = {
        selected: null,
        lists: {}
    };


    // CARGAR LAS LISTAS
    $scope.getAllTaskLists = function () {
        document.getElementById("addTaskButton").disabled = true;
        document.getElementById("addListButton").disabled = false;
        //INICIALIZA EL OBJETO LISTS PARA METERLE LAS LISTAS ADENTRO
        $scope.models = {
            selected: null,
            lists: {}
        };

        //CARGA LAS TAREAS EN LAS LISTAS SEGUN CORRESPONDA
        taskListService.getAllTaskListsBySprint($scope.actualSprint).then(
            function (resp) {
                $scope.taskLists = resp.data;
                if( $scope.taskLists.length>=5){
                    document.getElementById("addListButton").disabled = true;
                }
                for(var i=0 ; i<$scope.taskLists.length ; i++){

                    switch ($scope.taskLists[i].name) {

                        case 'TODO':
                            $scope.models.lists.TODO = [];
                            tasksService.getTasksByListAndSprintName('TODO', $scope.actualSprint, $scope.actualOrder).then(
                                function (resp) {
                                    $scope.models.lists.TODO=resp.data;
                                },
                                function (reason) {
                                }
                            );
                            break;
                        case 'In Progress':
                            $scope.models.lists.InProgress = [];
                            tasksService.getTasksByListAndSprintName('In Progress', $scope.actualSprint, $scope.actualOrder).then(
                                function (resp) {
                                    $scope.models.lists.InProgress=resp.data;
                                },
                                function (reason) {

                                    alert(reason.toString());
                                }
                            );
                            break;
                        case 'Done':
                            $scope.models.lists.Done = [];
                            tasksService.getTasksByListAndSprintName('Done', $scope.actualSprint, $scope.actualOrder).then(
                                function (resp) {
                                    $scope.models.lists.Done=resp.data;
                                },
                                function (reason) {

                                }
                            );
                            break;
                        case 'Waiting':
                            $scope.models.lists.Waiting = [];
                            tasksService.getTasksByListAndSprintName('Waiting', $scope.actualSprint, $scope.actualOrder).then(
                                function (resp) {
                                    $scope.models.lists.Waiting=resp.data;
                                },
                                function (reason) {

                                }
                            );
                            break;
                        case 'Backlog':
                            $scope.models.lists.Backlog = [];
                            document.getElementById("addTaskButton").disabled = false;
                            tasksService.getTasksByListAndSprintName('Backlog', $scope.actualSprint, $scope.actualOrder).then(
                                function (resp) {
                                    $scope.models.lists.Backlog=resp.data;
                                    taskListService.getTaskListIdByNameAndSprintName('Backlog',$scope.actualSprint).then(
                                        function (value) {
                                            $scope.backLogIdCurrentSprint=value.data;
                                        }
                                    );
                                },
                                function (reason) {

                                }
                            );
                            break;
                        default:

                            break;

                    }
                }


            },
            function (reason) {  }
        );

    };

    //CARGAR EL BACKLOG
    $scope.loadBacklog = function () {
        tasksService.loadBacklog().then(
            function (resp) {
                $scope.dataSource = resp.data;
            },
            function (reason) {  }
        );

    };

    //MODAL PARA TAREA NUEVA
    $scope.newTaskModal = function(tasklistName, taskListId){
        $scope.taskListToAdd.id = taskListId;
        $scope.taskListToAdd.name = tasklistName;
        $scope.sprintToAdd = $scope.actualSprint;
        var mi=$uibModal.open({
            animation : true,
            backdrop : 'static',
            keyboard : false,
            templateUrl : 'views/addTaskModal.html',
            controller : 'AddTaskModalController',
            controllerAs: '$taskModalCtrl',
            size : 'large',
            resolve : {
                instancia : $scope.instancia
            }
        });

        mi.result.then(
            function(r){
                $scope.instancia=r;
                $scope.instancia.taskList={};
                $scope.instancia.taskList.name =  $scope.taskListToAdd.name;
                $scope.instancia.taskList.taskListId =  $scope.taskListToAdd.id;
                $scope.instancia.taskList.sprintName = $scope.actualSprint;
                $scope.addTask();
            },function(e){

            });
    };

    //AGREGAR TAREA
    $scope.addTask=function(){
        tasksService.addTask($scope.instancia).then(
            function(resp){
                //scope.dataSource.push(resp.data);
                $scope.instancia={};
                $scope.getAllTaskLists();
            },
            function(err){}
        );
    };

    $scope.getTasksByTaskList = function(taskListId){
        tasksService.getTasksByTaskList(taskListId).then(
            function (resp) {
                $scope.taskLists = resp.data;

            },
            function (reason) {  }
        );
    };

    //MOVER TAREA
    $scope.moveTask = function(task,taskListName){

        taskListName === 'InProgress' ? taskListName='In Progress' : taskListName;

        taskListService.getAllTaskListsByTaskListNameAndSprintName(taskListName, $scope.actualSprint).then(
            function (resp) {
                task.taskList = resp.data;
                tasksService.moveTask(task.taskId, task).then(function () {
                    $scope.getAllTaskLists();
                });
            },
            function(reason){}
        ); {
                task.taskList = resp.data;
                tasksService.moveTask(task.taskId, task).then(function () {
                    $scope.getAllTaskLists();
                });
            }

    };

    $scope.deleteTask = function(taskId){
        if(!confirm("Desea eliminar la tarea seleccionada?"))
            return;
        tasksService.deleteTask(taskId).then(function (resp) {
            if (resp.status===200){
                $scope.getAllTaskLists();
            } else if (resp.status===403){

                alert("No tiene permitido realizar esa accion");
            }

        }, function (reason) {

        });
    }





    // Model to JSON for demo purpose
    $scope.$watch('models', function(model) {
        $scope.modelAsJson = angular.toJson(model, true);
    }, true);

    $('#sprintSelect').on("click", function() {
        $scope.getAllTaskLists();
    } );

    $('#orderSelect').on("click", function() {
        $scope.getAllTaskLists();
    } );

    $rootScope.authInfo();
});