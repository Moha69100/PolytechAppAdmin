'use strict';

/**
 * Service 'AuthService'
 */
app.factory('AuthService', ['$rootScope', '$http', '$compile', function($rootScope, $http, $compile) {
	/*return {
		
		user : user,
		
		authorize : function authorize(poidsAutorisation, poidsRole) {
			if (poidsAutorisation == undefined) {
				return true;
			}
			if (poidsRole == undefined) {
				poidsRole = user.role.poids;
			}
			return poidsRole & poidsAutorisation;
		},
		
		logout : function logout(success, error) {
			// console.log('>>> logout auth service');
			$http.get('/auth-logout').success(function() {
				user = {};
				success();
			}).error(error);
		},
		
		replaceSelectRoadmap : function(elem, val, scope) {
			var html = '<p class="form-control-static" >' + val + '</p>';
			var e = $compile(html)(scope);
			elem.replaceWith(e);
		},
		
		disableButton : function(element) {
			element.off('click');
			element.css('cursor', 'default');
			element.addClass('btn-read disabled');
		},
		
		manageOnglets : function(attrs) {
			if (attrs.drAuthShow == "offre_tab_description") {
				$(".offre-description").removeClass("active");
				$(".offre-vente").addClass("active");
			}
			if (attrs.drAuthShow == "menu_demande") {
				$(".demande-list-rech").removeClass("active");
				$(".offre-list-rech").addClass("active");
			}
		},
		
		disableEdit : function(element) {
			element.attr("readonly", "readonly");
			if (element[0].type && (element[0].type == "select-one" || element[0].type == "select-multiple"))
				element.attr("disabled", "disabled");
		}
	};*/

}]);
/*
 * LECTURE SEULE ECRANS TRAITEES/FIN DE COM
 */
app.directive('drStatus', ['AuthService', "$compile", function InjectingFunction(AuthService, $compile) {
	return {
		scope : {}, // acces au scope $parent pour les données locales

		link : function LinkingFunction(scope, elem, attrs) {
			var manageAll = function(element) {
				if (element[0].nodeName == "INPUT" || element[0].nodeName == "TEXTAREA") {
					element.attr("readonly", "readonly");
				} else if (element[0].nodeName == "BUTTON") {
					if (element.hasClass("checkedoff") || element.hasClass("checkedon")) {
						AuthService.disableButton(element);
					} else {
						element.hide();
					}
				} else if (element[0].nodeName == "SELECT") {
					if (attrs.replacedValue)
						AuthService.replaceSelectRoadmap(element, attrs.replacedValue, scope);

					element.attr("disabled", "disabled");
				} else if (element[0].nodeName == "I" || element[0].nodeName == "A") {
					element.hide();
				} else {
					AuthService.disableButton(element);
				}
			};
			/* EN FONCTION DU STATUS */
			scope.$watch('$parent.demandeParent.demandeStatus.constantKey', function(newVal, oldVal) {
				if (newVal == "DEMAND_STATUS_ARCHIVE" || newVal == "DEMAND_STATUS_ABANDONNEE") {
					manageAll(elem);
				}
			});
			/* POPUP details roadmap => BUG
			scope.$watch('$parent.demandeDetail.demandeStatus.constantKey', function(newVal, oldVal) {
				console.log(newVal);
				if (newVal == "DEMAND_STATUS_ARCHIVE") {
					manageAll(elem);
				}
			});*/

			/** ROADMAP */
			if (attrs.demandeStatus && attrs.demandeStatus == "DEMAND_STATUS_ARCHIVE") {
				manageAll(elem);
			}

			scope.$watch('$parent.offreParent.offreStatus.constantKey', function(newVal, oldVal) {
				if (newVal == "OFFRE_STATUS_FIN_COMMERCIALISATION") {
					manageAll(elem);
				}
			});
		}
	};
}]);

app.directive('drAuthShowStatut', ['AuthService', "$filter", function InjectingFunction(AuthService, $filter) {
	return {
		link : function LinkingFunction(scope, element, attrs) {
			scope.$watch('demandes', function(newVal, oldVal) {
				if (newVal) {
					// liste demandes pour consultation => on cache les create
					if (!AuthService.authorize(Autorisations[attrs.drAuthShowStatut].poids)) {
						scope.demandesFiltered = $filter('filter')(newVal, '!' + "DEMAND_STATUS_CREATE", false);
						scope.totalItems = scope.demandesFiltered.length;
					} else {
						scope.demandesFiltered = newVal;
						scope.totalItems = newVal.length;
					}
					if (scope.$parent && scope.$parent.$parent)
						scope.$parent.$parent.demandesFiltered = scope.demandesFiltered;// ecran
					// recherche
				}
			});
			scope.$watch('offres', function(newVal, oldVal) {
				// liste offres pour consultation => on cache les brouillon
				if (newVal) {
					if (!AuthService.authorize(Autorisations[attrs.drAuthShowStatut].poids)) {
						scope.offresFiltered = $filter('filter')(newVal, '!' + "OFFRE_STATUS_BROUILLON", false);
						scope.totalItems = scope.offresFiltered.length;

					} else {
						scope.offresFiltered = newVal;
						scope.totalItems = newVal.length;
					}
					if (scope.$parent && scope.$parent.$parent)
						scope.$parent.$parent.offresFiltered = scope.offresFiltered;// ecran
					// recherche

				}
			});
		}
	};
}]);

/**
 * Directive 'drAuthShow'
 */
app.directive('drAuthShow', ['AuthService', function InjectingFunction(AuthService) {
	return {

		link : function LinkingFunction(scope, element, attrs) {
			var prevDisp = element.css('display');

			if (!AuthService.authorize(Autorisations[attrs.drAuthShow].poids)) {
				element.css('display', 'none');
				AuthService.manageOnglets(attrs);
			} else {
				element.css('display', prevDisp);
			}

		}
	};
}]);

/**
 * Directive 'drAuthEdit' Tout est en lecture : s'il y a l'autorisation alors
 * ecriture
 */
app.directive('drAuthEdit', ['$compile', '$parse', 'AuthService', function InjectingFunction($compile, $parse, AuthService) {
	return {
		require : 'ngModel',
		link : function LinkingFunction(scope, element, attrs, ngModel) {
			if (!ngModel) {
				return;
			}
			
			var params = $parse(attrs['drAuthEdit'])(scope);

			if (!AuthService.authorize(Autorisations[params.auth].poids)) {
				if (attrs.replacedValue) {
					AuthService.replaceSelectRoadmap(element, attrs.replacedValue, scope);
				} else {
					AuthService.disableEdit(element);

				}
			}
		}
	};
}]);
/**
 * Directive 'drAuthClick'
 */
app.directive('drAuthEnhancedCom', ['$parse', 'AuthService', "$compile", function InjectingFunction($parse, AuthService, $compile) {
	return {
		link : function LinkingFunction(scope, element, attrs) {
			var params = $parse(attrs['drAuthEnhancedCom'])(scope);
			scope.$watch('$parent.offreParent.offreStatus.constantKey', function(newVal, oldVal) {
				if (newVal && newVal == "OFFRE_STATUS_FIN_COMMERCIALISATION") {
					scope.tinymceOptions = {
						resize : false,
						toolbar : false,
						statusbar : false,
						menubar : false,
						readonly : 1
					};
					var html = '<textarea  ui-tinymce="tinymceOptions" ng-model=' + attrs.ngModel + '></textarea>';
					var e = $compile(html)(scope);
					element.replaceWith(e);
				} else if (newVal) {
					if (AuthService.authorize(Autorisations[params.auth].poids)) {
						scope.tinymceOptions = {
							resize : false,
							toolbar : ["undo redo | styleselect | bold italic | alignleft aligncenter alignright"],
							statusbar : false,
							menubar : "tools table format insert edit",
							readonly : 0
						};
					} else {
						scope.tinymceOptions = {
							resize : false,
							toolbar : false,
							statusbar : false,
							menubar : false,
							readonly : 1
						};
					}
					var html = '<textarea  ui-tinymce="tinymceOptions" ng-model=' + attrs.ngModel + '></textarea>';
					var e = $compile(html)(scope);
					element.replaceWith(e);
				}
			});
		}
	};
}]);
/**
 * Directive 'drAuthClick'
 */
app.directive('drAuthClick', ['$parse', 'AuthService', function InjectingFunction($parse, AuthService) {
	return {
		link : function LinkingFunction(scope, element, attrs) {
			var params = $parse(attrs['drAuthClick'])(scope);

			if (AuthService.authorize(Autorisations[params.auth].poids)) {
				element.on('click', function(event) {
					scope.$apply(function() {
						scope.$eval(params.fn);
					});
				});
				element.css('cursor', 'pointer');
				element.removeClass('btn-read disabled');

			}
			else {
				AuthService.disableButton(element);
			}

		}
	};
}]);
