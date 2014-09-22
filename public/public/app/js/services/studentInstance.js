'use strict';

app.service('studentInstance', [function() {
        var root = this;
        this.student = {};
        this.getStudent = function(){
            console.log(root.student);
            return root.student;
        }
        
        this.setStudent = function (student) {
            root.student = student;
        }
        
        this.reset = function () {
            root.student = {};
        }
    
}]);