var XeroStorage = {};
var DOM = {};

XeroStorage = function() {

	if (typeof localStorage !== "object") {
		alert("Your current OS isn't support the localStorage.");
		return;
	}

	var storage = localStorage;
	return {
		set : function(key, value) {
			if (key && value) {
				storage.setItem(key, value);
			}
			console.log("SET: key=" + key + " value=" + value);
		},
		get : function(key) {
			console.log("GET: key=" + key + " value=" + storage.getItem(key));
			return storage.getItem(key);
		},
		getAll : function() {
			var idx, nLength, array = [];
			for (idx = 0, nLength = storage.length; idx < nLength; idx++) {
				array[storage.key(idx)] = storage.getItem(storage.key(idx));
				console.log("GETALL: " + idx + " key=" + storage.key(idx)
						+ " value=" + storage.getItem(storage.key(idx)));
			}
			if (!nLength) {
				console.log("GETALL: no data.");
			}
			return array;
		},
		remove : function(key) {
			storage.removeItem(key);
			console
					.log("REMOVE: key=" + key + " value="
							+ storage.getItem(key));
			return;
		},
		removeAll : function() {
			storage.clear();
			console.log("REMOVEALL: clear.");
			return;
		}
	}
}

DOM = function() {
	return {
		get : function(id) {
			var obj = document.getElementById(id), value = null;
			if (obj) {
				value = obj.value;
			}
			return value;
		}
	}
}