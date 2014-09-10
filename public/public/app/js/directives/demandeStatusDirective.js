
'use strict';


app.directive('demandestatus', function() {
    var directive = {};

    directive.restrict = 'A'; /* restrict this directive to elements */
    directive.template = "";

   
    directive.compile = function(element, attributes) {

        var linkFunction = function($scope, element, attributes) {

        	var content = '';
        	var demandeStatusValue = attributes.value;
        	var demandeStatusKey = attributes.demandestatus;
        	
        	var template = function(demandeStatusValue, imgSrc) {
        		return  "<div class='demandestatus-div'>" +
						"		<span class='demandestatus-span'>" + demandeStatusValue+ "</span>" +
						"		<span class='demandestatus-span'>" +
						"			<img src='"+imgSrc+"'>" +
						"		</span>" +
						"</div>";
        	};
        	
        	if(demandeStatusKey) {
        		switch (demandeStatusKey) {
					
				case CONST_DEMAND_STATUS_CANDIDATE:
					content += template(demandeStatusValue, 'app/img/demand-status/demandStatus-1.png');
					break;
					
				case CONST_DEMAND_STATUS_PRIORISEE:
					content += template(demandeStatusValue, 'app/img/demand-status/demandStatus-2.png');
					break;
					
				case  CONST_DEMAND_STATUS_INPROGRESS:
					content += template(demandeStatusValue, 'app/img/demand-status/demandStatus-3.png');
					break;
					
				case CONST_DEMAND_STATUS_INSTRUITE:
					content += template(demandeStatusValue, 'app/img/demand-status/demandStatus-4.png');
					break;
					
				case CONST_DEMAND_STATUS_ARCHIVE:
					content += template(demandeStatusValue, 'app/img/demand-status/demandStatus-5.png');
					break;

				default:
					//DEMAND_STATUS_CREATE ou DEMAND_STATUS_ABANDONNEE
					content += template(demandeStatusValue, 'app/img/demand-status/demandStatus-0.png');
					break;
				}
        	}
        	else {
        		content += 'vide';
        	}
//            element.html("test directive: " + $scope.e.demandeLibDet);
        	
        	element.html(content);
        	
        	$('.demandestatus-div').css({
        		'vertical-align':'middle',
        		'text-align': 'center',
        		'width':'90px'
        	});

        	$('.demandestatus-span').css({
        		'position':'relative',
        		'width':'100%'
        	});

        };

        return linkFunction;
    };
    
    return directive;
});