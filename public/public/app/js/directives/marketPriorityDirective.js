'use strict';

app.directive('marketpriority', function() {
	var directive = {};

	directive.restrict = 'A'; /* restrict this directive to elements */
	directive.template = "";
//	directive.replace = true;
//	directive.scope = { div: '@marketpriority' };
	
//	directive.scope = {
//        eventHandler: '&ngClick'
//    };

	directive.compile = function(element, attributes) {

		var linkFunction = function(scope, element, attributes) {

			attributes.$observe('marketpriority', function(marketpriority) {

//				console.log('marketpriority has changed value to ' + marketpriority);

				var content = '';
				content = changeValue(scope, marketpriority, attributes.market);
				element.html(content);

				$('.marketpriority-div').css({
					'text-align':'center',
					'width':'60px',
					'position':'relative',
					'float':'left'
				});
				
				$('.marketpriority-img').css({
					'cursor':'pointer'
				});

			});

		};

		return linkFunction;
	};

	var template = function(scope, marketpriority, labelMarket, imgSrcPriority, imgSrcMarket) {

		return  '<div class="marketpriority-div">' +
		'	<img src="' + imgSrcMarket + '"/>' +
		'	<div>' + labelMarket + '</div>' +
		'	<img class="marketpriority-img" src="' + imgSrcPriority +'"/>' +
		'</div>';
	};


	var changeValue = function(scope, marketpriority, market) {

		var imgSrcPriority = '';
		var imgSrcMarket = '';
		
		if(marketpriority) {

			switch (marketpriority) {

			case CONST_DEMAND_MARKET_PRIORITY_ZERO:
				imgSrcPriority = 'app/img/market/market-priority-0.png';
				break;

			case CONST_DEMAND_MARKET_PRIORITY_ONE:
				imgSrcPriority = 'app/img/market/market-priority-1.png';
				break;

			case CONST_DEMAND_MARKET_PRIORITY_TWO:
				imgSrcPriority = 'app/img/market/market-priority-2.png';
				break;

			default:
				imgSrcPriority = 'app/img/market/market-priority-0.png';
			break;
			}

		}
		else {
			imgSrcPriority = 'app/img/market/market-priority-0.png';
		}

		if(market) {

			switch (market) {

			case CONST_DEMAND_MARKET_ONE:
				imgSrcMarket = 'app/img/market/market-1.png';
				break;

			case CONST_DEMAND_MARKET_TWO:
				imgSrcMarket = 'app/img/market/market-2.png';
				break;

			case CONST_DEMAND_MARKET_THREE:
				imgSrcMarket = 'app/img/market/market-3.png';
				break;
				
			case CONST_DEMAND_MARKET_FOUR:
				imgSrcMarket = 'app/img/market/market-4.png';
				break;
				
			case CONST_DEMAND_MARKET_FIVE:
				imgSrcMarket = 'app/img/market/market-5.png';
				break;

			default:
				imgSrcMarket = '';
			break;
			}

		}
		else {
//			console.log('market error');
			content = 'ERROR';
		}

		return template(scope, marketpriority, market, imgSrcPriority, imgSrcMarket);
	};


	
	return directive;
});