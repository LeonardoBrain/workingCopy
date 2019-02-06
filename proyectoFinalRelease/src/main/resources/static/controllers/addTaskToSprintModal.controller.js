angular.module('iw3').controller(
    'AddTaskToSprintController', function ($scope,$uibModalInstance,$rootScope,instancia) {

        var $taskToSprintModalCtrl=this;
        $taskToSprintModalCtrl.sprintsActuales=angular.copy(instancia);
        $taskToSprintModalCtrl.sprint ='';

        $taskToSprintModalCtrl.cancel=function(){
            $uibModalInstance.dismiss();
        };
        $taskToSprintModalCtrl.ok=function(){
             $uibModalInstance.close($taskToSprintModalCtrl.sprint);

        };
        $taskToSprintModalCtrl.showSaveButton=function(){

            return true;
        };

        $rootScope.authInfo();

    });