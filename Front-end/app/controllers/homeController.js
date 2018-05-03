app.controller('HomeController', function ($scope) {
    $scope.curPage = 1,
        $scope.itemsPerPage = 5,
        $scope.maxSize = 5;

    this.items = books;


    $scope.numOfPages = function () {
        return Math.ceil(books.length / $scope.itemsPerPage);

    };

    $scope.$watch('curPage + numPerPage', function() {
        var begin = (($scope.curPage - 1) * $scope.itemsPerPage),
            end = begin + $scope.itemsPerPage;

        $scope.filteredItems = books.slice(begin, end);
    });

});