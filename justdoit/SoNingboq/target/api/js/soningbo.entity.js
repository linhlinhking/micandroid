// The soningbo namespace.
var soningbo = soningbo || {};

/**
 * @constructor
 * @class The base entity class to store the data that is common to all soningbo
 *        entities whether it be location, events, users, etc.
 * 
 * @param {object}
 *            object The entity object.
 * @param {function}
 *            callback The callback function to get the object.
 */
soningbo.entity = function(object, callback) {

	// Only continue if the object is valid.
	if (object) {

		/** The unique identifier for this entity. */
		this.id = this.id || '';

		/** The API for this entity */
		this.api = this.api || null;

		// If object is a string, assume it is a UUID and get it.
		this.update(object);

		// If they provide a callback, call it now.
		if (callback) {

			// Get the object from the server.
			this.get(callback);
		}
	}
};

/**
 * Get's an object from the soningbo API.
 * 
 * @param {function}
 *            callback The callback function when the object is retrieved.
 */
soningbo.entity.prototype.get = function(callback) {

	// If the API exists, then we need to get the object.
	if (this.api) {

		// Call the API.
		var _this = this;
		this.api.get(this.getObject(), this.getQuery(), function(object) {

			if (!object) {
				callback(null);
			} else if (object[0]) {

				var i = object.length;
				while (i--) {
					object[i] = new _this.constructor(object[i]);
				}

				// Callback a list of objects.
				callback(object);
			} else {
				// Update the object, then call the callback.
				_this.update(object);

				if (callback) {
					callback(_this);
				}
			}
		});
	}
};

/**
 * Saves this entity.
 * 
 * @param {function}
 *            callback The function called once entity is saved.
 */
soningbo.entity.prototype.save = function(callback) {

	// If the API exists, then we can save this object.
	if (this.api) {

		// Call the API.
		var _this = this;
		this.api.save(this.getObject(), function(object) {

			// Update the object, then call the callback.
			_this.update(object);

			if (callback) {
				callback(_this);
			}
		});
	}
};

/**
 * Removes an entity
 * 
 * @param {function}
 *            callback The function called once entity is removed.
 */
soningbo.entity.prototype.remove = function(callback) {

	// Only remove if they have an ID.
	if (this.id) {

		// Call the API.
		this.api.remove(this.getObject(), callback);
	}
};

/**
 * Adds a key value pair to the query object.
 * 
 * @param {object}
 *            query The query object.
 * @param {string}
 *            field The field to set.
 * @param {string}
 *            value The value of the field to set.
 */
soningbo.entity.prototype.setQuery = function(query, field, value) {

	// Set the value of this query.
	query[field] = value;
};

/**
 * Returns the search query.
 * 
 * @return {object} The query to pass to the server.
 */
soningbo.entity.prototype.getQuery = function() {

	var query = {};

	// We only need to provide a search query if there is no ID.
	if (!this.id) {

		// Iterate through all of our fields.
		for ( var field in this) {

			// Make sure that this property exists, is set, and is not an
			// object.
			if (this.hasOwnProperty(field) && this[field]
					&& (typeof this[field] != 'object')) {

				// Add this as a query parameter.
				this.setQuery(query, field, this[field]);
			}
		}
	}

	// Return the params.
	return query;
};

/**
 * Update the entity data.
 * 
 * @param {object}
 *            object The entity information.
 */
soningbo.entity.prototype.update = function(object) {

	// Update the object.
	if (object) {

		// Update the params.
		for ( var param in object) {

			// Check to make sure that this param is within object scope.
			if (object.hasOwnProperty(param) && this.hasOwnProperty(param)) {

				// Check to see if this object has an update function.
				if (this[param].update) {
					this[param].update(object[param]);
				} else {
					this[param] = object[param];
				}
			}
		}
	}
};

/**
 * Returns the object to send during PUT's and POST's during a save or add.
 * 
 * @return {object} The JSON object to send to the Services endpoint.
 */
soningbo.entity.prototype.getObject = function() {
	return {
		id : this.id
	};
};
