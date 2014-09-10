'use strict';

app.directive('iconedomaine', function() {
    var directive = {};

    directive.restrict = 'A'; /* restrict this directive to elements */
    directive.template = "";

    // complile : pour intervenir sur le template avant sa compilation !
    directive.compile = function(element, attributes) {

    	// paramétrage du lien entre la directive et le scope :
        var linkFunction = function($scope, element, attributes) {

        	var content = '';
        	var template = function(iconedomaine, imgSrc, libDisplay) {
        		
        		var libelle = "";
        		if (libDisplay) {
        			libelle = "<span class='iconedomaine-span'>" + libDisplay + "</span>";
        		}
        		
        		return  "<div class='iconedomaine-div'>" +
						"		<span style=\"float : left\" class='iconedomaine-span'>" +
						"			<img src='"+imgSrc+"'>" +
						"		</span>" +
						libelle +
						"</div>";
        	};
        	
//        	console.log(attributes.iconedomaine);
//        	console.log(attributes.afflib);
        	
        	if(attributes.iconedomaine) {
        		switch (attributes.iconedomaine) {
        		
				case CONST_DOM_DET_MOB:
				case CONST_DOM_GRP_MOB:
					content += template(attributes.iconedomaine, 'app/img/domains/mobilite.png',attributes.afflib);
					break;
				case CONST_DOM_DET_RES_DATA:
				case CONST_DOM_GRP_RES:
					content += template(attributes.iconedomaine, 'app/img/domains/reseau.png',attributes.afflib);
					break;
				case CONST_DOM_DET_RES_SVA:
				case CONST_DOM_GRP_RES:
					content += template(attributes.iconedomaine, 'app/img/domains/reseau.png',attributes.afflib);
					break;
				case CONST_DOM_DET_RES_IMG :
				case CONST_DOM_GRP_RES:
					content += template(attributes.iconedomaine, 'app/img/domains/reseau.png',attributes.afflib);
					break;
				case CONST_DOM_DET_UCC_INFRA:
				case CONST_DOM_GRP_UCC:
					content += template(attributes.iconedomaine, 'app/img/domains/ucc.png',attributes.afflib);
					break;
				case CONST_DOM_DET_UCC_MESS:
				case CONST_DOM_GRP_UCC:
					content += template(attributes.iconedomaine, 'app/img/domains/ucc.png',attributes.afflib);
					break;
				case CONST_DOM_DET_UCC_SECU:
				case CONST_DOM_GRP_UCC:
					content += template(attributes.iconedomaine, 'app/img/domains/ucc.png',attributes.afflib);
					break;
				case CONST_DOM_DET_UCC_VERTI:
				case CONST_DOM_GRP_UCC:
					content += template(attributes.iconedomaine, 'app/img/domains/ucc.png',attributes.afflib);
					break;
				case CONST_DOM_DET_VOIX_VOIX:
				case CONST_DOM_GRP_VOIX:
					content += template(attributes.iconedomaine, 'app/img/domains/voix.png',attributes.afflib);
					break;
					
				default:
//					content += template(attributes.iconedomaine, 'app/img/icon_searchbar.png',attributes.afflib);
					content += 'vide';
					break;
				}
        	}
        	else {
        		content += 'vide';
        	}
        	
            //element.html("test directive: " + $scope.e.domaineDet);
        	
        	element.html(content);

        	$('.iconedomaine-div').css({
        		'vertical-align':'middle',
        		'text-align': 'center',
        		'width':'90px'
        	});

        	$('.iconedomaine-span').css({
        		'position':'relative',
        		'width':'100%'
        	});
        };

        return linkFunction;
    };
    
    return directive;
});