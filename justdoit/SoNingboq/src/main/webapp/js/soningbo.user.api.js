/** The drupal namespace. */

var soningbo = soningbo || {};

/** The soningbo.user namespace */
soningbo.user = soningbo.user || {};

/**
 * @constructor
 * @extends drupal.api
 * @class The Drupal User Services class.
 */
soningbo.user.api = function() {

  // Set the resource
  this.resource = 'user';

  // Call the drupal.api constructor.
  soningbo.api.call(this);
};

/** Derive from drupal.api. */
soningbo.user.api.prototype = new soningbo.api();

/** Reset the constructor. */
soningbo.user.api.prototype.constructor = soningbo.user.api;
