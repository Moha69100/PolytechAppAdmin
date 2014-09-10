/**
 * Permet de formatter un date avant l'envoie au serveur param: date de type
 * Date return: String
 */
var transformDateToString = function(date, format) {
	var str = null;
	if (format == "DD/MM/YYYY") {

		var dd = date.getDate() + 1;
		if (dd.toString().length == 1) {
			dd = "0" + dd;
		}

		var mm = date.getMonth() + 1;
		if (mm.toString().length == 1) {
			mm = "0" + mm;
		}

		str = dd + "/" + mm + "/" + date.getFullYear();

	} else { // YYYY-MM-DD HH:mm:ss
		str = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
	}
	return str;
};

/**
 * Permet de formatter un date avant l'envoie au serveur param: date de type
 * String return: Date
 */
var transformStringToDate = function(date) {
	var match = date.match(/^(\d+)-(\d+)-(\d+) (\d+)\:(\d+)\:(\d+)$/);
	var date = new Date(match[1], match[2] - 1, match[3], match[4], match[5], match[6]);
	return date;
};

// YYYY-MM-DD HH:mm:ss to DD/MM/YYYY
var transformStringToString = function(date) {
	var match = date.match(/^(\d+)-(\d+)-(\d+) (\d+)\:(\d+)\:(\d+)$/);
	var dateString = match[3] + '/' + match[2] + '/' + match[1];
	return dateString;
};


//YYYY-MM-DD HH:mm:ss to DD-MM-YYYY
var transformStringToStringWithTiret = function(date) {
	var match = date.match(/^(\d+)-(\d+)-(\d+) (\d+)\:(\d+)\:(\d+)$/);
	var dateString = match[3] + '-' + match[2] + '-' + match[1];
	return dateString;
};

var compareMaxDate = function(a, b) {
	// Compare two dates (could be of any type
	// supported by the convertDate
	// function above) and returns:
	// false : if a < b
	// true : if a = b
	// true : if a > b
	// NaN : if a or b is an illegal date
	// NOTE: The code inside isFinite does an
	// assignment (=).
	if (isFinite(a = convertDate(a)) && isFinite(b = convertDate(b))) {
		if (a >= b || a == b)
			return (true);
		return false;
	}
	return (NaN);
};

var convertDate = function(d) {
	// Converts the date in d to a date-object. The
	// input can be:
	// a date object: returned without modification
	// an array : Interpreted as [year,month,day].
	// NOTE: month is 0-11.
	// a number : Interpreted as number of
	// milliseconds
	// since 1 Jan 1970 (a timestamp)
	// a string : Any format supported by the
	// javascript engine, like
	// "YYYY/MM/DD", "MM/DD/YYYY", "Jan 31 2009"
	// etc.
	// an object : Interpreted as an object with
	// year, month and date
	// attributes. **NOTE** month is 0-11.
	return (d.constructor === Date ? d : d.constructor === Array ? new Date(d[0], d[1], d[2]) : d.constructor === Number ? new Date(d) : d.constructor === String ? new Date(d) : typeof d === "object" ? new Date(d.year, d.month, d.date) : NaN);
};

var compareMinDate = function(a, b) {
	// Compare two dates (could be of any type
	// supported by the convertDate
	// function above) and returns:
	// true : if a < b
	// true : if a = b
	// false : if a > b
	// NaN : if a or b is an illegal date
	// NOTE: The code inside isFinite does an
	// assignment (=).
	if (isFinite(a = convertDate(a)) && isFinite(b = convertDate(b))) {
		// FIX des pb de comparaison au niveau des heures
		// ex : a = 01/01/2014 01:00:00 & b = 01/01/2014 00:00:00
		b = new Date(b.getFullYear(), (b.getMonth()), b.getDate(), a.getHours(), a.getMinutes(), a.getSeconds())
		// => b= 01/01/2014 01:00:00 
		if (a <= b || a == b) {
			return (true);
		}
		return false;
	}
	return (NaN);
};
var inRangeDate = function(d, start, end) {
	// Checks if date in d is between dates in start and end.
	// Returns a boolean or NaN:
	// true : if d is between start and end (inclusive)
	// false : if d is before start or after end
	// NaN : if one or more of the dates is illegal.
	// NOTE: The code inside isFinite does an assignment (=).
	return (isFinite(d = convertDate(d).valueOf()) && isFinite(start = convertDate(start).valueOf()) && isFinite(end = convertDate(end).valueOf()) ? start <= d && d <= end : NaN);
};
