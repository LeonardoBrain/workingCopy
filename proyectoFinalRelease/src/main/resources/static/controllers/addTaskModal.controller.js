angular.module('iw3').controller(
    'AddTaskModalController', function ($scope,$uibModalInstance,$rootScope,instancia) {

        var $taskModalCtrl=this;
        $taskModalCtrl.instancia=angular.copy(instancia);

        $taskModalCtrl.cancel=function(){
            $uibModalInstance.dismiss();
        };

        $taskModalCtrl.mostrarBotonGuardar=function(){
            var i=$taskModalCtrl.instancia;
            return i.name && i.name.length>0  && i.priority && i.priority.length>0;
        };

        $taskModalCtrl.ok=function(){

            $uibModalInstance.close($taskModalCtrl.instancia);

        };
        $taskModalCtrl.showSaveButton=function(){

            return true;
        };

        $rootScope.authInfo();

    });