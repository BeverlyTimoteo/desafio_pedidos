;(function() {
    angular
        .module('app')
        .service('PedidoService', ['$http', function($http) {
            return {
                get: function() {
                    return $http.get('/api/pedidos/listar');
                },
                save: function(data) {
                    return $http.post('/api/pedidos/novo', data);
                }
            };
        }]);
})();
