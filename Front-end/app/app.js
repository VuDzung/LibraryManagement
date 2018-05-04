var app= angular.module('myApp',['ngRoute', 'ngCookies', 'ui.bootstrap']).config(config); //.run(run)
	config.$inject = ['$routeProvider', '$locationProvider'];
	function config($routeProvider, $locationProvider){
		$routeProvider
			.when('/home',{
				controller: 'LoginController',
				templateUrl: '../views/login.html'
			})
			.when('/',{
				controller: 'HomeController',
				templateUrl: '../views/home.html'
			})			
			.when('/login',{
				controller: 'LoginController',
				templateUrl: '../views/login.html'
			})
			.when('/register',{
				controller: 'RegisterController',
				templateUrl: '../views/register.html'
			})
            .when('/admin',{
                controller: 'BookManagementController',
                templateUrl: '../views/bookmanagement.html'
            })
			.otherwise({
				redirectTo: '/home'
			});
	}
    // run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    // function run($rootScope, $location, $cookieStore, $http) {
    //     // keep user logged in after page refresh
    //     $rootScope.globals = $cookieStore.get('globals') || {};
    //     if ($rootScope.globals.currentUser) {
    //         $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    //     }
    //
    //     $rootScope.$on('$locationChangeStart', function (event, next, current) {
    //         // redirect to login page if not logged in and trying to access a restricted page
    //         var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
    //         var loggedIn = $rootScope.globals.currentUser;
    //         if (restrictedPage && !loggedIn) {
    //             $location.path('/login');
    //         }
    //     });
    // }
var books =[
	{
		title: 'Nhật Ký Trong Tù',
		ISBN: 'ISBN code',
		Status: 'Available',
        Author: 'Ho Chi Minh',
        Description: 'some things in here',
		img: '../resources/images/nhatkytrongtu.jpg'
	},
    {
        title: 'Nhật Ký Trong Tù',
        ISBN: 'ISBN code',
        Status: 'Available',
        Author: 'Ho Chi Minh',
        Description: 'some things in here',
        img: '../resources/images/nhatkytrongtu.jpg'
    },
    {
        title: 'Nhật Ký Trong Tù',
        ISBN: 'ISBN code',
        Status: 'Available',
        Author: 'Ho Chi Minh',
        Description: 'some things in here',
        img: '../resources/images/nhatkytrongtu.jpg'
    },
    {
        title: 'Nhật Ký Trong Tù',
        ISBN: 'ISBN code',
        Status: 'Available',
        Author: 'Ho Chi Minh',
        Description: 'some things in here',
        img: '../resources/images/nhatkytrongtu.jpg'
    },
    {
        title: 'Nhật Ký Trong Tù',
        ISBN: 'ISBN code',
        Status: 'Available',
        Author: 'Ho Chi Minh',
        Description: 'some things in here',
        img: '../resources/images/nhatkytrongtu.jpg'
    },
    {
        title: 'Nhật Ký Trong Tù',
        ISBN: 'ISBN code',
        Status: 'Available',
        Author: 'Ho Chi Minh',
        Description: 'some things in here',
        img: '../resources/images/nhatkytrongtu.jpg'
    },
    {
        title: 'Nhật Ký Trong Tù',
        ISBN: 'ISBN code',
        Status: 'Available',
        Author: 'Ho Chi Minh',
        Description: 'some things in here',
        img: '../resources/images/nhatkytrongtu.jpg'
    },
    {
        title: 'Nhật Ký Trong Tù',
        ISBN: 'ISBN code',
        Status: 'Available',
        Author: 'Ho Chi Minh',
        Description: 'some things in here',
        img: '../resources/images/nhatkytrongtu.jpg'
    },
    {
        title: 'Nhật Ký Trong Tù',
        ISBN: 'ISBN code',
        Status: 'Available',
        Author: 'Ho Chi Minh',
        Description: 'some things in here',
        img: '../resources/images/nhatkytrongtu.jpg'
    }
];
