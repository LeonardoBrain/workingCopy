angular.module('iw3').controller(
    'AddTaskModalController', function ($scope,$uibModalInstance,instancia) {

        var $taskModalCtrl=this;
        $taskModalCtrl.instancia=angular.copy(instancia);
        $taskModalCtrl.cancel=function(){
            $uibModalInstance.dismiss();
        };
        $taskModalCtrl.ok=function(){
            $uibModalInstance.close($taskModalCtrl.instancia);

        };
        $taskModalCtrl.showSaveButton=function(){

            return true;
        };

    });