angular.module('iw3').controller(
    'EditTaskModalController', function ($scope,$uibModalInstance,$rootScope,instancia) {

        var $editTaskModalCtrl=this;
        $editTaskModalCtrl.instancia=angular.copy(instancia);
        $editTaskModalCtrl.cancel=function(){
            $uibModalInstance.dismiss();
        };
        $editTaskModalCtrl.ok=function(){

            $uibModalInstance.close($editTaskModalCtrl.instancia);

        };
        $editTaskModalCtrl.showSaveButton=function(){

            return true;
        };

        $rootScope.authInfo();

    });