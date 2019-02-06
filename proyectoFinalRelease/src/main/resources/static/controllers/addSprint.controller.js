angular.module('iw3').controller(
    'AddSprintController', function ($scope,$uibModalInstance,$rootScope) {

        var $sprintModalCtrl=this;
        $sprintModalCtrl.sprint ='';

        $sprintModalCtrl.cancel=function(){
            $uibModalInstance.dismiss();
        };
        $sprintModalCtrl.ok=function(){
             $uibModalInstance.close($sprintModalCtrl.sprint);

        };
        $sprintModalCtrl.showSaveButton=function(){

            return true;
        };

        $rootScope.authInfo();

    });