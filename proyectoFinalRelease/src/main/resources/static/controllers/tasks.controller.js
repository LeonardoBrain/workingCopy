angular.module('iw3')
.controller('TaskController', function ($rootScope, $scope, $http, tasksService,$uibModal, taskListService) {

    $scope.title = "Backlog";

    $scope.dataSource = [];
    $scope.dataList = ['TODO', 'Done', 'In Progress', 'Waiting', 'Backlog'];
    $scope.instancia = {};
    $scope.taskListToAdd={};
    $scope.actualSprint = $rootScope.sprints[0] || 'Sprint1';
    $scope.actualOrder = ' ';
    $scope.taskLists = {};
    $scope.taskListsToCreate = [];
    $scope.backLogIdCurrentSprint=0;
    $scope.sprintToAdd ='';
    $scope.sprint ='';
    $scope.taskListId ='';
    $scope.sprintsActuales =[];
    $scope.listaToAdd ={};


//INICIALIZAR LAS LISTAS
    $scope.models = {
        selected: null,
        lists: {}
    };


    // CARGAR LAS LISTAS
    $scope.getAllTaskLists = function () {
        $rootScope.getAllSprints();
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
                $scope.loadBacklog();

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
                if(taskListId ===100){
                    $scope.instancia.taskList.sprintName = 'Backlog';
                }else{
                    $scope.instancia.taskList.sprintName = $scope.actualSprint;

                }
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

    //MODAL PARA SPRINT NUEVA
    $scope.newSprint = function(){

        var mi=$uibModal.open({
            animation : true,
            backdrop : 'static',
            keyboard : false,
            templateUrl : 'views/addSprint.html',
            controller : 'AddSprintController',
            controllerAs: '$sprintModalCtrl',
            size : 'large',
            resolve : {
               // instancia : $scope.instancia
            }
        });

        mi.result.then(
            function(r){
                console.log(r);
                $scope.sprint=r;
                $scope.listaToAdd.name ='Backlog';
                $scope.listaToAdd.sprintName=$scope.sprint;

                $scope.addTaskList();
            },function(e){

            });
    };
    //MODAL PARA AGREGAR TASK a Un SPRINT
    $scope.includeTaskInSprint = function(taskId){

        var mi=$uibModal.open({
            animation : true,
            backdrop : 'static',
            keyboard : false,
            templateUrl : 'views/addTaskToSprint.html',
            controller : 'AddTaskToSprintController',
            controllerAs: '$taskToSprintModalCtrl',
            size : 'large',
            resolve : {
                instancia: function() {
                    return $rootScope.sprints;
                }
            }
        });

        mi.result.then(
            function(r){
                console.log(r);
                $scope.sprint=r;

                $scope.getTaskById(taskId);
            },function(e){

            });
    };

    $scope.getTaskById= function(taskId){
        tasksService.getTaskByID(taskId).then(
            function (resp) {
                console.log(resp.data.name);
                $scope.instancia=resp.data;
                $scope.getTaskListById();
                
        });
    }
    
    $scope.getTaskListById = function(){
        taskListService.getTaskListIdByNameAndSprintName('Backlog',$scope.sprint).then(
            function (resp) {
                console.log(resp.data);
                $scope.instancia.taskList.taskListId = resp.data;
                $scope.addTask();
                $scope.loadBacklog();
            });


    }
    
    // $scope.moveTaskToBacklog = function(){
    //     tasksService.moveTask($scope.taskListId,$scope.instancia).then(
    //         function (resp) {
    //             $scope.loadBacklog();
    //         },function (err) {
    //             console.log("Hubo un error");
    //         });
    //
    // }
    //MODAL PARA LISTA
    $scope.newTaskListModal = function(){
        $scope.sprintToAdd = $scope.actualSprint;

        var k = 0;
        for(var j=0 ; j<$scope.dataList.length ; j++){
            var presente = false;

            for(var i=0 ; i<$scope.taskLists.length ; i++){
                if($scope.dataList[j]===($scope.taskLists[i].name)){
                    presente=true;
                    break;
                }
            }
            if(presente==false){
                $scope.taskListsToCreate[k] = angular.copy($scope.dataList[j]);
                k++;
            }
        }


        // for(var i=0 ; i<$scope.taskListsToCreate.length ; i++){
        //
        //     console.log("Lista: "+$scope.taskListsToCreate[i]);
        // }

            var mi2=$uibModal.open({
            animation : true,
            backdrop : 'static',
            keyboard : false,
            templateUrl : 'views/addTaskListModal.html',
            controller : 'AddTaskListModalController',
            controllerAs: '$taskListModalCtrl',
            size : 'large',
            resolve : {
                instancia: function() {
                    return $scope.taskListsToCreate

                }
            }
        });

        mi2.result.then(
            function(r){

                $scope.listaToAdd=r;
                $scope.listaToAdd.sprintName=$scope.sprintToAdd;
                $scope.taskListsToCreate=[];
                $scope.addTaskList();
            },function(e){

            });
    };
    //AGREGAR LISTA
    $scope.addTaskList=function(){
        taskListService.addTaskList($scope.listaToAdd).then(
            function(resp){
                $scope.listaToAdd={};
                $rootScope.getAllSprints();
                $scope.getAllTaskLists();
            },
            function(err){
                console.log(err);
            }
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

        if(validateMoveTask(task, taskListName)) {
            taskListName === 'InProgress' ? taskListName = 'In Progress' : taskListName;

            taskListService.getAllTaskListsByTaskListNameAndSprintName(taskListName, $scope.actualSprint).then(
                function (resp) {
                    task.taskList = resp.data;
                    tasksService.moveTask(task.taskId, task).then(function () {
                        $scope.getAllTaskLists();
                    });
                },
                function (reason) {
                }
            );
            {
                task.taskList = resp.data;
                tasksService.moveTask(task.taskId, task).then(function () {
                    $scope.getAllTaskLists();
                });
            }
        }

    };

    function validateMoveTask(task, taskListName){

        //NO PASAR UNA TAREA DE DONE A CUALQUIER OTRA LISTA
        if(task.taskList.name === 'Done'){
            bootbox.alert("It is not possible to move a task from 'Done' to '"+taskListName+"'");
            return false;
        }

        //QUE UNA TAREA QUE ESTA EN BACKLOG SOLO PUEDA PASAR A TODO
        if(task.taskList.name === 'Backlog' && taskListName !=='TODO'){
            bootbox.alert("It is not possible to move a task from 'Backlog' to '"+taskListName+"'. The task is only allowed to move to 'TODO'");
            return false;
        }

        //QUE UNA TAREA QUE PASA A TODOO DEBA TENER TIEMPO ESTIMADO
        if(taskListName === 'TODO' && (!task.estimatedTime || task.estimatedTime === 0)){
            bootbox.alert("Estimated Time is required to move a task to 'TODO' list");
            return false;
        }

        switch (task.taskList.name) {
            case "In Progress":
            case "TODO":
            case "Waiting":
                if(taskListName === "Backlog"){
                    bootbox.alert("It is not possible to move a task from '"+task.taskList.name+"' to 'Backlog'. A task can not be moved back to 'Backlog'");
                    return false;
                }

        }

        /*switch(task.taskList.name){

            case 'TODO':
            case 'In Progress':
            case 'Waiting':
                if(taskListName === 'Backlog') {
                    bootbox.alert("It is not possible to move a task from '"+task.taskList.name+"' to 'Backlog'");
                    return false;
                }
        }*/

        return true;

    }

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

    $scope.editTask = function(task){

        var mi=$uibModal.open({
            animation : true,
            backdrop : 'static',
            keyboard : false,
            templateUrl : 'views/editTaskModal.html',
            controller : 'EditTaskModalController',
            controllerAs: '$editTaskModalCtrl',
            size : 'large',
            resolve : {
                instancia : task
            }
        });

        mi.result.then(
            function(r){
                $scope.instancia=r;
                $scope.instancia.taskId = task.taskId;
                $scope.instancia.taskList={};
                $scope.instancia.taskList.name = task.taskList.name;
                $scope.instancia.taskList.taskListId =  task.taskList.taskListId;
                $scope.instancia.taskList.sprintName = task.taskList.sprintName;
                $scope.updateTaskById();
            },function(e){

            });
    }

    $scope.updateTaskById = function(){
        tasksService.updateTask($scope.instancia).then(
            function (resp) {
                $scope.getAllTaskLists();
            }
        );
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