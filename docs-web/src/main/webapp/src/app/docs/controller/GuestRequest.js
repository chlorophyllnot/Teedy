'use strict';

/**
 * Guest register controller.
 */
angular.module('docs').controller('GuestRequest', function($scope, Restangular) {
    $scope.request = {};
    $scope.success = false;
    $scope.error = false;

    $scope.submitRequest = function() {
        var requestData = {
            username: $scope.request.username,
            password: $scope.request.password,
            email: $scope.request.email,
            storage_quota: 1073741824  // 1GB
        };

        Restangular.one('user').customPUT(
            $.param(requestData), // 把JSON变成key=value&key2=value2的form字符串
            '', {},
            { 'Content-Type': 'application/x-www-form-urlencoded' }
        ).then(function() {
            $scope.success = true;
            $scope.error = false;
            $scope.request = {}; // 清空表单
        }, function() {
            $scope.success = false;
            $scope.error = true;
        });
    };
});
