(function() {
	var d;
	var s = [];
	var a = "https://api.t.sina.com.cn/oauth2/query";
	var h = {};
	var j = function(B, A, z) {
		var C;
		if (typeof A != "undefined") {
			for (C in B) {
				if (A[C] != null) {
					if (z) {
						if (B.hasOwnProperty[C]) {
							B[C] = A[C]
						}
					} else {
						B[C] = A[C]
					}
				}
			}
		}
		return B
	};
	var y = function(D) {
		var B = {
			url : "",
			charset : "UTF-8",
			timeout : 30 * 1000,
			args : {},
			onComplete : null,
			onTimeout : null,
			responseName : null,
			varkey : "callback"
		};
		var E = -1;
		j(B, D);
		var C = B.responseName
				|| ("STK_" + Math.floor(Math.random() * 1000) + new Date()
						.getTime().toString());
		B.args[B.varkey] = C;
		var z = B.onComplete;
		var A = B.onTimeout;
		window[C] = function(F) {
			if (E != 2 && z != null) {
				E = 1;
				z(F)
			}
		};
		B.onComplete = null;
		B.onTimeout = function() {
			if (E != 1 && A != null) {
				E = 2;
				A()
			}
		};
		return e(B)
	};
	var e = function(E) {
		var D, z;
		var A = {
			url : "",
			charset : "UTF-8",
			timeout : 30 * 1000,
			args : {},
			onComplete : null,
			onTimeout : null,
			uniqueID : null
		};
		j(A, E);
		if (A.url == "") {
			throw "url is null"
		}
		D = document.createElement("script");
		D.charset = "UTF-8";
		var F = /msie/i.test(navigator.userAgent);
		if (A.onComplete != null) {
			if (F) {
				D.onreadystatechange = function() {
					if (D.readyState.toLowerCase() == "complete"
							|| D.readyState.toLowerCase() == "loaded") {
						clearTimeout(z);
						A.onComplete();
						D.onreadystatechange = null
					}
				}
			} else {
				D.onload = function() {
					clearTimeout(z);
					A.onComplete();
					D.onload = null
				}
			}
		}
		var C = function(H) {
			if (H) {
				var G = [];
				for ( var I in H) {
					G.push(I + "=" + encodeURIComponent(p(H[I])))
				}
				if (G.length) {
					return G.join("&")
				} else {
					return ""
				}
			}
		};
		var B = C(A.args);
		if (A.url.indexOf("?") == -1) {
			if (B != "") {
				B = "?" + B
			}
		} else {
			if (B != "") {
				B = "&" + B
			}
		}
		D.src = A.url + B;
		document.getElementsByTagName("head")[0].appendChild(D);
		if (A.timeout > 0 && A.onTimeout != null) {
			z = setTimeout(function() {
				A.onTimeout()
			}, A.timeout)
		}
		return D
	};
	var p = function(z) {
		if (typeof z !== "string") {
			throw "trim need a string as parameter"
		}
		if (typeof z.trim === "function") {
			return z.trim()
		} else {
			return z.replace(
					/^(\u3000|\s|\t|\u00A0)*|(\u3000|\s|\t|\u00A0)*$/g, "")
		}
	};
	var u = function(A, F) {
		var E = p(A).split("&");
		var B = {};
		var G = function(H) {
			if (F) {
				return decodeURIComponent(H)
			} else {
				return H
			}
		};
		var D = function(J, K) {
			if (K.indexOf) {
				return K.indexOf(J)
			}
			for ( var I = 0, H = K.length; I < H; I++) {
				if (K[I] === J) {
					return I
				}
			}
			return -1
		};
		for ( var C = 0, z = E.length; C < z; C++) {
			if (E[C]) {
				_hsh = E[C].split("=");
				_key = _hsh[0];
				_value = _hsh[1];
				if (_hsh.length < 2) {
					_value = _key;
					_key = "$nullName"
				}
				if (!B[_key]) {
					B[_key] = G(_value)
				} else {
					if (D(B[_key]) > -1) {
						B[_key] = [ B[_key] ]
					}
					B[_key].push(G(_value))
				}
			}
		}
		return B
	};
	var f = {
		set : function(C, F, E) {
			var z = [];
			var D, B;
			var A = {
				expire : null,
				path : null,
				domain : null,
				secure : null,
				encode : true
			};
			j(A, E);
			if (A.encode == true) {
				F = escape(F)
			}
			z.push(C + "=" + F);
			if (A.path != null) {
				z.push("path=" + A.path)
			}
			if (A.domain != null) {
				z.push("domain=" + A.domain)
			}
			if (A.secure != null) {
				z.push(A.secure)
			}
			if (A.expire != null) {
				D = new Date();
				B = D.getTime() + A.expire * 3600000;
				D.setTime(B);
				z.push("expires=" + D.toGMTString())
			}
			document.cookie = z.join(";")
		},
		get : function(B) {
			B = B.replace(/([\.\[\]\$])/g, "\\$1");
			var A = new RegExp(B + "=([^;]*)?;", "i");
			var C = document.cookie + ";";
			var z = C.match(A);
			if (z) {
				return z[1] || ""
			} else {
				return ""
			}
		},
		remove : function(z, A) {
			A = A || {};
			A.expire = -10;
			this.set(z, "", A)
		}
	};
	var t = function() {
		this.started = 1;
		this.taskList = [];
		this.setStatue = function(z) {
			this.started = z
		};
		this.start = function() {
			this.setStatue(0);
			var B, D, A, C;
			var z = this.taskList.shift();
			D = z[0];
			A = z[1];
			C = z[2];
			D.apply(C, A)
		};
		this.next = function() {
			this.setStatue(1);
			if (this.taskList.length > 0) {
				this.start()
			}
		};
		this.add = function(B, A) {
			var z = {
				args : [],
				pointer : window,
				top : false
			};
			j(z, A);
			if (z.top) {
				this.taskList.unshift([ B, z.args, z.pointer ])
			} else {
				this.taskList.push([ B, z.args, z.pointer ])
			}
			if (this.started) {
				this.start()
			}
		}
	};
	var o = new t();
	function r(A) {
		var z = WB2._config.version;
		var C = WB2.anyWhere._instances;
		var B;
		if (B = C[z]) {
			if (B.contentWindow._ready) {
				B.contentWindow.request(A)
			} else {
				WB2.addToCallbacks(B.contentWindow, A)
			}
		} else {
			WB2.delayCall(A)
		}
	}
	function k(A) {
		var z = {
			requestType : "anywhere",
			callback : A
		};
		b(z)
	}
	function b(z) {
		var B = z || {};
		var A = function() {
			r(B);
			o.next()
		};
		var C = function(D) {
			if (h.bundle) {
				D && D()
			} else {
				e({
					url : WB2._config.host
							+ "/open/api/js/api/bundle.js?version=20110602",
					onComplete : function() {
						h.bundle = 1;
						D && D()
					}
				})
			}
		};
		o.add(C, {
			args : [ A ]
		})
	}
	function m() {
		var C = document.getElementsByTagName("script");
		var E = C.length, D = 0, A, z, H, B, F;
		if (E > 0) {
			A = C[D++];
			while (A) {
				if (A.src.indexOf("api/js/wb.js") != -1) {
					z = A.src.split("?").pop();
					break
				}
				A = C[D++]
			}
		}
		z = z.toLowerCase();
		var G = u(z);
		H = G.appkey || "";
		B = G.secret || "";
		F = G.version || 1;
		if (H == "") {
			throw new Error("To initialize anywhere, please provide a App Key")
		}
		return {
			appkey : H,
			secret : B,
			version : F
		}
	}
	function l(C, B) {
		var A, z;
		if (C != null) {
			if (B == true) {
				s.unshift(C)
			} else {
				s.push(C)
			}
		}
		if (WB2.checkLogin()) {
			for (A = 0, z = s.length; A < z; A++) {
				s[A].call()
			}
			s = []
		}
	}
	function n(B) {
		l(B, true);
		if (!WB2.checkLogin()) {
			var A = function(C) {
				if (C.success == 1) {
					i.save(C);
					c(1);
					l()
				}
			};
			var z = {
				appkey : WB2._config.appkey,
				requestType : "login",
				callback : A
			};
			b(z)
		}
	}
	function q(A) {
		if (WB2._config.appkey != null) {
			i.del();
			c(-1);
			try {
				y({
					url : "https://api.t.sina.com.cn/account/end_session.json?source="
							+ WB2._config.appkey,
					onComplete : function() {
						A && A()
					}
				})
			} catch (z) {
				throw "JavaScript SDK: logout error"
			}
		}
	}
	function c(z) {
		if (z == null) {
			return
		}
		d = z
	}
	function x() {
		return d == 1
	}
	var i = {
		load : function() {
			var A = f.get("weibojs_" + WB2._config.appkey);
			A = unescape(A);
			var z = u(A);
			return z
		},
		save : function(z) {
			var A = "access_token=" + (z.access_token || "")
					+ "&refresh_token=" + (z.refresh_token || "")
					+ "&expires_in=" + (z.expires_in || 0) + "&status="
					+ (z.status || d || -1);
			f.set("weibojs_" + WB2._config.appkey, A, {
				path : "/",
				domain : document.domain
			})
		},
		del : function() {
			f.remove("weibojs_" + WB2._config.appkey, {
				path : "/",
				domain : document.domain
			})
		}
	};
	function g(A) {
		var B = A || i.load();
		var C = B.access_token || "";
		var z = B.expires_in || "";
		if (C != "") {
			d = 1
		}
		y({
			url : a,
			onComplete : function(D) {
				D = D || {};
				if (D.status == 1 && D.access_token) {
					i.save(D)
				}
				d = D.status;
				l()
			},
			args : {
				source : WB2._config.appkey
			}
		})
	}
	window.WB2 = window.WB2 || {};
	var w = m();
	WB2._config = {};
	WB2._config.version = w.version;
	WB2._config.appkey = w.appkey;
	WB2._config.secret = w.secret;
	WB2._config.host = "http://js.t.sinajs.cn";
	WB2._config.cssHost = "http://js.t.sinajs.cn";
	WB2.login = n;
	WB2.logout = q;
	WB2.checkLogin = x;
	WB2.anyWhere = k;
	WB2.anyWhere._instances = {};
	WB2.Cookie = i;
	WB2.regIframeRequest = b;
	var v = {
		loginButton : {
			versions : {
				"1.0" : {
					js : "loginButton{ver}.js?version=20111223"
				},
				latest : {
					js : "loginButton.js?version=20111223",
					css : "/style/css/common/card.css?version=20110824"
				}
			}
		},
		publish : {
			versions : {
				"1.0" : {
					js : "publish{ver}.js?version=20111223"
				},
				latest : {
					js : "publish.js?version=20111223",
					css : "/style/css/thirdpart/rlsbox.css?version=20110809"
				}
			}
		},
		hoverCard : {
			versions : {
				"1.0" : {
					js : "hoverCard{ver}.js?version=20110823"
				},
				latest : {
					js : "hoverCard.js?version=20110823",
					css : "/style/css/common/card.css?version=20110823"
				}
			}
		},
		recommend : {
			versions : {
				"1.0" : {
					js : "recommend{ver}.js"
				},
				latest : {
					js : "recommend.js",
					css : "/style/css/thirdpart/interested.css"
				}
			}
		},
		selector : {
			versions : {
				"1.0" : {
					js : "selector{ver}.js?version=20111223"
				},
				latest : {
					js : "selector.js?version=20111223",
					css : "/style/css/thirdpart/csuser.css"
				}
			}
		}
	};
	WB2.Module = v;
	g()
})();