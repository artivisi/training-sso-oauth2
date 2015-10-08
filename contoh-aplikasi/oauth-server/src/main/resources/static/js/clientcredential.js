var aplikasi = angular.module('oauthClient', []);

aplikasi.controller('ClientCredentialController', function($http, $scope, $window, $location){
    var resourceServerUrl = "http://localhost:8080/api/waktu";
    var authServerUrl = "http://localhost:10000/oauth/token";
    
    var clientId = "clientcred";
    var clientSecret = "123456";
    var basicAuth = btoa(clientId + ':' + clientSecret);
    console.log("Auth : "+basicAuth);
    
    $scope.requestToken = function(){
        var postData = {
            client_id : clientId,
            grant_type : "client_credentials"
        };
        
        var postConfig = {
            headers: {
                Authorization: 'Basic ' + basicAuth,
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj)
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            }
        };
        
        $http.post(authServerUrl, postData, postConfig).then(
                function(response){
                    console.log("Token : "+response.data.access_token);
                    $scope.token = response.data.access_token;
                    $window.sessionStorage.setItem('token', $scope.token);
                }, 
                function(response){
                    alert('Error Code : '+response.status+', Error Text : '+response.statusText);
                }
        );
    };
    
    $scope.requestKeResourceServer = function(){
        
        var token = $window.sessionStorage.getItem('token');
        if(!token){
            alert('Belum Login, silahkan klik login dulu. Kemudian klik ambil token dari URL');
            return;
        }
        
        $http.get(resourceServerUrl+"?access_token="+token).then(
        function(response){
            console.log(response);
            $scope.responseDariServer = response.data;
        }, 
        function(response){
            console.log(response);
            alert('Error Code : '+response.status+', Error Text : '+response.statusText);
        });
    };
});