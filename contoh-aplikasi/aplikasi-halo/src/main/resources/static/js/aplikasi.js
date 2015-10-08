var aplikasi = angular.module('oauthClient', []);


aplikasi.controller('DemoController', function($http, $scope, $window, $location){
    var resourceServerUrl = "http://localhost:8080/api/halo";
    var authorizationServerUrl = "http://localhost:10000/oauth/authorize?client_id=jsclient&response_type=token"
    
    $scope.bukaLoginPage = function(){
        $window.location.href = authorizationServerUrl;
    };
    
    $scope.ambilTokenDariUrl = function(){
        var location = $location.url();
        console.log("Location : "+location);
        var params = location.split("&");
        console.log(params);
        var tokenParam = params[0];
        var token = tokenParam.split("=")[1];
        console.log("Token : "+token);
        $window.sessionStorage.setItem('token', token);
    };
    
    $scope.requestKeResourceServer = function(){
        
        var token = $window.sessionStorage.getItem('token');
        if(!token){
            alert('Belum Login, silahkan klik login dulu. Kemudian klik ambil token dari URL');
            return;
        }
        
        $http.get(resourceServerUrl+"?access_token="+token).then(
        function(response){
            $scope.responseDariServer = response.data;
        }, 
        function(response){
            alert('Error Code : '+response.status+', Error Text : '+response.statusText);
        });
    };
});