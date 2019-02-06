angular.module('iw3').controller(
    'AddTaskListModalController', function ($scope,$uibModalInstance,$rootScope,instancia) {

        var $taskListModalCtrl=this;
        $taskListModalCtrl.taskListsToCreate=angular.copy(instancia);
        $taskListModalCtrl.listaToAdd ={};

        $taskListModalCtrl.cancel=function(){
            $uibModalInstance.dismiss();
        };
        $taskListModalCtrl.ok=function(){
             $uibModalInstance.close($taskListModalCtrl.listaToAdd);

        };
        $taskListModalCtrl.showSaveButton=function(){

            return true;
        };

        $rootScope.authInfo();

    });