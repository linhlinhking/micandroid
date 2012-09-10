/**
 * @namespace dm
 * @name dm
 * @version 1.0.1
 */
var dm = {
		base : {
			Class : function() {
				
			},
			
			/**
			 * 加载js
			 * 
			 * @name dm.base.loadJs
			 * @function
			 * @grammar dm.base.loadJs(url[, callback])
			 * @param url js的url地址
			 * @param callback
			 *            回调函数
			 */
			losdJs : function(url, callback) {
				var _script = document.createElement('script');
				_script.setAttribute('type', 'text/javascript');
				_script.setAttribute('src', url);
				document.getElementsByTagName('head')[0].appendChild(_script);
				if (!callback)
					return;
				if (document.all) {
					_script.onreadystatechange = function() {
						if (this.readyState == 'loaded' || this.readyState == 'complete') {
							callback();
						}
					};
				} else {
					_script.onload = function() {
						callback();
					};
				}
			}
		},
		
		/**
		 * @namespace dm.object 操作原生对象的方法。
		 */
		object : {
			/**
			 * 判断对象是否为空，等同于php中的empty()方法
			 * 
			 * @name dm.isEmpty dm.object.isEmpty
			 * @function
			 * @grammar dm.isEmpty(mixed_var) dm.object.isEmpty(mixed_var)
			 * @param {Object} mixed_var
			 * @return {Boolean}
			 */
			isEmpty : function(mixed_var) {
				// * example 1: empty(null);
				// * returns 1: true
				// * example 2: empty(undefined);
				// * returns 2: true
				// * example 3: empty([]);
				// * returns 3: true
				// * example 4: empty({});
				// * returns 4: true
				// * example 5: empty({'aFunc' : function () { alert('humpty'); } });
				// * returns 5: false
				var key;
				if (mixed_var === "" || mixed_var === 0 || mixed_var === "0"
						|| mixed_var === null || mixed_var === false
						|| typeof mixed_var === 'undefined') {
					return true;
				}
				if (typeof mixed_var == 'object') {
					for (key in mixed_var) {
						return false;
					}
					return true;
				}
				return false;
			},
			
			isFunction : function(source) {
				// chrome下,'function' == typeof /a/ 为true.
				return '[object Function]' == Object.prototype.toString.call(source);
			},
			
			object : function(o) {
				function F(){};
				F.prototype = o;
				return new F();
			},
			
			inheritPrototype : function(subType, superType) {
				var prototype = this.object(superType.prototype);
				prototype.constructor = subType;
				subType.prototype = prototype;
			}
		},
		
		/**
		 * @namespace dm.string 字符串处理封装
		 */
		string : {
			/**
			 * 对目标字符串进行html解码
			 * 
			 * @name dm.string.HTMLdecode
			 * @function
			 * @grammar dm.string.HTMLdecode(source)
			 * @param {string}  source 目标字符串
			 * @shortcut decodeHTML
			 * @meta standard
			 * @see dm.string.encodeHTML
			 * 
			 * @returns {string} html解码后的字符串
			 */
			HTMLdecode : function(source) {
				var str = String(source).replace(/&quot;/g, '"').replace(/&lt;/g, '<')
						.replace(/&gt;/g, '>').replace(/&amp;/g, "&");
				// 处理转义的中文和实体字符
				return str.replace(/&#([\d]+);/g, function(_0, _1) {
					return String.fromCharCode(parseInt(_1, 10));
				});
			},
			
			/**
			 * 对目标字符串进行html编码
			 * 
			 * @name dm.string.HTMLencode
			 * @function
			 * @grammar dm.string.HTMLencode(source)
			 * @param {string}  source 目标字符串
			 * @remark 编码字符有5个：&<>"'
			 * @shortcut encodeHTML
			 * @meta standard
			 * @see dm.string.decodeHTML
			 * 
			 * @returns {string} html编码后的字符串
			 */
			HTMLencode : function(source) {
				return String(source).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(
						/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#39;");
			},
			

			
			urlEncode : function(source) {
				source = (source + '').toString();
				return encodeURIComponent(source).replace(/!/g, '%21').replace(/'/g, '%27')
						.replace(/\(/g, '%28').replace(/\)/g, '%29').replace(/\*/g, '%2A')
						.replace(/%20/g, '+');
			},
			
			urlDecode : function(source) {
				return decodeURIComponent((source + '').replace(/\+/g, '%20'));
			},
			
			/**
			 * 删除目标字符串两端的字符（默认为空白字符）
			 * 
			 * @name dm.string.trim
			 * @function
			 * @grammar dm.string.trim(source [, char])
			 * @param {string} str 目标字符串
			 * @param {string} charlist 要删除的字符
			 * @remark 不支持删除单侧字符
			 * @shortcut trim
			 * @meta standard
			 * 
			 * @returns {string} 删除两端字符后的字符串
			 */
			trim : function(str, charlist) {
			    // Strips whitespace from the beginning and end of a string  
			    // 
			    // version: 1109.2015
			    // discuss at: http://phpjs.org/functions/trim
			    // +   original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
			    // +   improved by: mdsjack (http://www.mdsjack.bo.it)
			    // +   improved by: Alexander Ermolaev (http://snippets.dzone.com/user/AlexanderErmolaev)
			    // +      input by: Erkekjetter
			    // +   improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
			    // +      input by: DxGx
			    // +   improved by: Steven Levithan (http://blog.stevenlevithan.com)
			    // +    tweaked by: Jack
			    // +   bugfixed by: Onno Marsman
			    // *     example 1: trim('    Kevin van Zonneveld    ');
			    // *     returns 1: 'Kevin van Zonneveld'
			    // *     example 2: trim('Hello World', 'Hdle');
			    // *     returns 2: 'o Wor'
			    // *     example 3: trim(16, 1);
			    // *     returns 3: 6
			    var whitespace, l = 0,
			        i = 0;
			    str += '';
			 
			    if (!charlist) {
			        // default list
			        whitespace = " \n\r\t\f\x0b\xa0\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u200b\u2028\u2029\u3000";
			    } else {
			        // preg_quote custom list
			        charlist += '';
			        whitespace = charlist.replace(/([\[\]\(\)\.\?\/\*\{\}\+\$\^\:])/g, '$1');
			    }
			 
			    l = str.length;
			    for (i = 0; i < l; i++) {
			        if (whitespace.indexOf(str.charAt(i)) === -1) {
			            str = str.substring(i);
			            break;
			        }
			    }
			 
			    l = str.length;
			    for (i = l - 1; i >= 0; i--) {
			        if (whitespace.indexOf(str.charAt(i)) === -1) {
			            str = str.substring(0, i + 1);
			            break;
			        }
			    }
			 
			    return whitespace.indexOf(str.charAt(0)) === -1 ? str : '';
			},
			
			ltrim : function(str, charlist) {
			    // Strips whitespace from the beginning of a string  
			    // 
			    // version: 1109.2015
			    // discuss at: http://phpjs.org/functions/ltrim
			    // +   original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
			    // +      input by: Erkekjetter
			    // +   improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
			    // +   bugfixed by: Onno Marsman
			    // *     example 1: ltrim('    Kevin van Zonneveld    ');
			    // *     returns 1: 'Kevin van Zonneveld    '
			    charlist = !charlist ? ' \\s\u00A0' : (charlist + '').replace(/([\[\]\(\)\.\?\/\*\{\}\+\$\^\:])/g, '$1');
			    var re = new RegExp('^[' + charlist + ']+', 'g');
			    return (str + '').replace(re, '');
			},
			
			rtrim : function(str, charlist) {
			    // Removes trailing whitespace  
			    // 
			    // version: 1109.2015
			    // discuss at: http://phpjs.org/functions/rtrim
			    // +   original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
			    // +      input by: Erkekjetter
			    // +   improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
			    // +   bugfixed by: Onno Marsman
			    // +   input by: rem
			    // +   bugfixed by: Brett Zamir (http://brett-zamir.me)
			    // *     example 1: rtrim('    Kevin van Zonneveld    ');
			    // *     returns 1: '    Kevin van Zonneveld'
			    charlist = !charlist ? ' \\s\u00A0' : (charlist + '').replace(/([\[\]\(\)\.\?\/\*\{\}\+\$\^\:])/g, '\\$1');
			    var re = new RegExp('[' + charlist + ']+$', 'g');
			    return (str + '').replace(re, '');
			},
			
			/**
			 * 获取字符串长度（普通默认中文计算为一个字符）
			 * 
			 * @author bx
			 * @name dm.string.nLength
			 * @function
			 * @grammar dm.string.nLength(string [, wideWrod])
			 * @param {String} string 目标字符串
			 * @param {Boolean} wideWord 为false时中文占两个字符,默认为true
			 * @return {Number} 字符串长度
			 */
			nLength : function(string) {
				var wideWord = arguments[1] === false?false:true;
				if (string == undefined || string == "") {
					return 0;
				}
				
				var val = string;
				var len = 0;
				if (!wideWord) {
					for ( var i = 0; i < val.length; i++) {
						if (val.charCodeAt(i) >= 0x4e00 && val.charCodeAt(i) <= 0x9fa5) {
							len += 2;
						} else {
							len++;
						}
					}
				} else {
					len = val.length;
				}
				return len;
			},
			
			/**
			 * 截取字符串（普通），中文占两个字符
			 * 
			 * @author bx
			 * @name dm.string.nSubString
			 * @function
			 * @grammar dm.string.nSubString(string , len)
			 * @param {String} string 要截取的字符串
			 * @param {Number} size 截取长度
			 * @return {String} 截取后的字符串
			 */
			nSubString : function(string, size) {
				if (!string || !size) {
					return '';
				}
				// 预期计数：中文2字节，英文1字节
				var a = 0;
				// 循环计数
				var i = 0;
				// 临时字串
				var temp = '';
				for (i = 0; i < string.length; i++) {
					if (string.charCodeAt(i) > 255) {
						// 按照预期计数增加2
						a += 2;
					} else {
						a++;
					}
					// 如果增加计数后长度大于限定长度，就直接返回临时字符串
					if (a > size) {
						return temp;
					}
					// 将当前内容加到临时字符串
					temp += string.charAt(i);
				}
				// 如果全部是单字节字符，就直接返回源字符串
				return string;
			},
			
			/**
			 * 截取字符串（普通，带结束符）,中文占两个字符
			 * 
			 * @author bx
			 * @name dm.string.nSubStringTer
			 * @function
			 * @grammar dm.string.nSubStringTer(string,size,terminator)
			 * @param {String} string 要截取的字符串
			 * @param {Number} size 截取长度
			 * @param {String} terminator 结束符
			 * @return {String} 截取后的字符串
			 */
			nSubStringTer : function(string, size, terminator) {
				if (string == undefined || string == "" || size <= 0) {
					return "";
				}
				if (dm.string.nLength(string, false) <= size) {
					return string;
				}
				size = size - dm.string.nLength(terminator, false);
				return dm.string.nSubString(string, size) + terminator;
			},
			
			/**
			 * 按权重截取字符串（普通）
			 * 
			 * @author bx
			 * @name dm.string.nSubStringByWeight
			 * @function
			 * @garmmar dm.string.nSubStringByWeight(totalStrLimit,firstStr,firstStrLimit,secondStr,secondStrLimit,terminator)
			 * @param {Number} totalStrLimit 字符串总长度
			 * @param {String} firstStr 高权重字符串
			 * @param {Number} firstStrLimit 高权重字符串最小长度
			 * @param {String} secondStr 低权重字符串
			 * @param {Number} secondStrLimit 低权重字符串最大长度
			 * @returns {Array} array['firstStr'] 截取后的高权重字符串 array['secondStr'] 截取后的低权重字符串
			 */
			nSubStringByWeight : function(totalStrLimit, firstStr, firstStrLimit, secondStr, secondStrLimit) {
				var firstStrLength = Math.ceil(dm.string.nLength(firstStr) / 2);
				var secondStrLength = Math.ceil(dm.string.nLength(secondStr) / 2);
				if (firstStrLength + secondStrLength > totalStrLimit) {
					if (secondStrLength > secondStrLimit) {
						if (totalStrLimit - firstStrLength < secondStrLimit) {
							firstStrLength = totalStrLimit - secondStrLimit;
							secondStrLength = secondStrLimit;
						} else {
							secondStrLength = totalStrLimit - firstStrLength;
						}
					} else {
						firstStrLength = totalStrLimit - secondStrLength;
					}
				}
				var result = new Array();
				result['firstStr'] = dm.string.nSubString(firstStr, firstStrLength * 2);
				result['secondStr'] = dm.string.nSubString(secondStr, secondStrLength * 2);
				return result;
			},
			
			/**
			 * 按权重截取字符串（普通），带结束符
			 * 
			 * @author bx
			 * @name dm.string.nSubStringTerByWeight
			 * @function
			 * @garmmar dm.string.nSubStringTerByWeight(totalStrLimit,firstStr,firstStrLimit,secondStr,secondStrLimit,terminator)
			 * @param {Number} totalStrLimit 字符串总长度
			 * @param {String} firstStr 高权重字符串
			 * @param {Number} firstStrLimit 高权重字符串最小长度
			 * @param {String} secondStr 低权重字符串
			 * @param {Number} secondStrLimit 低权重字符串最大长度
			 * @param {String} terminator 结束字符（一个中文字符宽度）
			 * @returns {Array} array['firstStr'] 截取后的高权重字符串 array['secondStr'] 截取后的低权重字符串
			 */
			nSubStringTerByWeight : function(totalStrLimit, firstStr, firstStrLimit, econdStr, secondStrLimit, terminator) {
				var firstStrLength = Math.ceil(dm.string.nLength(firstStr) / 2);
				var secondStrLength = Math.ceil(dm.string.nLength(secondStr) / 2);
				if (firstStrLength + secondStrLength > totalStrLimit) {
					if (secondStrLength > secondStrLimit) {
						if (totalStrLimit - firstStrLength < secondStrLimit) {
							firstStrLength = totalStrLimit - secondStrLimit;
							secondStrLength = secondStrLimit;
						} else {
							secondStrLength = totalStrLimit - firstStrLength;
						}
					} else {
						firstStrLength = totalStrLimit - secondStrLength;
					}
				}
				var result = new Array();
				result['firstStr'] = dm.string.nSubStringTer(firstStr, firstStrLength * 2, terminator);
				result['secondStr'] = dm.string.nSubStringTer(secondStr, secondStrLength * 2, terminator);
				return result;
			},
			
			/**
			 * 按Arial字体宽度获取字符串长度。
			 * 
			 * @author bx
			 * @name dm.string.aLength
			 * @function
			 * @grammar dm.string.aLength(string)
			 * @param {String} string
			 * @return {Number} 字符串长度，中文长度为1
			 */
			aLength : function(string) {
				if (string == undefined || string == "") {
					return 0;
				}
				var val = string;
				var len = 0;
				for ( var i = 0; i < val.length; i++) {
					if ((val.charCodeAt(i) >= 0x2e80 && val.charCodeAt(i) <= 0x9fbf)// 中日韩文
							|| (val.charCodeAt(i) >= 0xff00 && val.charCodeAt(i) <= 0xffef)// 全角标点、数字、字母
							|| (val.charCodeAt(i) >= 0x2f800 && val.charCodeAt(i) <= 0x2fa1f)// 增补中日韩相容汉字
							|| (val.charCodeAt(i) >= 0x20000 && val.charCodeAt(i) <= 0x2a6df)// 中日韩文扩展集b
							|| val.charAt(i) == "W") {
						len += 6;
					} else if (val.charAt(i) == "G" || val.charAt(i) == "O"
							|| val.charAt(i) == "M" || val.charAt(i) == "m"
							|| val.charAt(i) == "Q") {
						len += 5;
					} else if (val.charAt(i) == "i" || val.charAt(i) == "j"
							|| val.charAt(i) == "l" || val.charAt(i) == "'"
							|| val.charAt(i) == "," || val.charAt(i) == "."
							|| val.charAt(i) == ":" || val.charAt(i) == ";") {
						len += 1;
					} else if (val.charAt(i) == "f" || val.charAt(i) == "r"
							|| val.charAt(i) == "t" || val.charAt(i) == "I"
							|| val.charAt(i) == '"' || val.charAt(i) == " ") {
						len += 2;
					} else if ((val.charCodeAt(i) >= 0x0061 && val.charCodeAt(i) <= 0x007a)
							|| val.charAt(i) == "J" || val.charAt(i) == "L") {
						len += 3;
					} else if (val.charCodeAt(i) >= 0x0041 && val.charCodeAt(i) <= 0x005a) {
						len += 4;
					} else {
						len += 5;
					}
				}
				return Math.ceil(len / 6);
			},
			
			/**
			 * 按Arial字体宽度截取字符串
			 * 
			 * @author bx
			 * @name dm.string.aSubString
			 * @function
			 * @grammar dm.string.aSubString(string,size)
			 * @param {String} string 要截取的字符串
			 * @param {Number} size 截取长度
			 * @return {String} 截取后的字符串
			 */
			aSubString : function(string, size) {
				if (string == undefined || string == "" || size <= 0) {
					return "";
				}
				var sizeTmp = size * 6;
				var val = string;
				var temp = "";
				var len = 0;
				for ( var i = 0; i < val.length; i++) {
					if ((val.charCodeAt(i) >= 0x2e80 && val.charCodeAt(i) <= 0x9fbf)// 中日韩文
							|| (val.charCodeAt(i) >= 0xff00 && val.charCodeAt(i) <= 0xffef)// 全角标点、数字、字母
							|| (val.charCodeAt(i) >= 0x2f800 && val.charCodeAt(i) <= 0x2fa1f)// 增补中日韩相容汉字
							|| (val.charCodeAt(i) >= 0x20000 && val.charCodeAt(i) <= 0x2a6df)// 中日韩文扩展集b
							|| val.charAt(i) == "W") {
						len += 6;
					} else if (val.charAt(i) == "G" || val.charAt(i) == "O"
							|| val.charAt(i) == "M" || val.charAt(i) == "m"
							|| val.charAt(i) == "Q") {
						len += 5;
					} else if (val.charAt(i) == "i" || val.charAt(i) == "j"
							|| val.charAt(i) == "l" || val.charAt(i) == "'"
							|| val.charAt(i) == "," || val.charAt(i) == "."
							|| val.charAt(i) == ":" || val.charAt(i) == ";") {
						len += 1;
					} else if (val.charAt(i) == "f" || val.charAt(i) == "r"
							|| val.charAt(i) == "t" || val.charAt(i) == "I"
							|| val.charAt(i) == '"' || val.charAt(i) == " ") {
						len += 2;
					} else if ((val.charCodeAt(i) >= 0x0061 && val.charCodeAt(i) <= 0x007a)
							|| val.charAt(i) == "J" || val.charAt(i) == "L") {
						len += 3;
					} else if (val.charCodeAt(i) >= 0x0041 && val.charCodeAt(i) <= 0x005a) {
						len += 4;
					} else {
						len += 5;
					}
					if (len <= sizeTmp) {
						temp += val.charAt(i);
					} else {
						return temp;
					}
				}
				return temp;
			},
			
			/**
			 * 截取字符串，带结束符
			 * 
			 * @author bx
			 * @name dm.string.aSubStringTer
			 * @function
			 * @grammar dm.string.aSubStringTer(string,size,terminator)
			 * @param {String} string 要截取的字符串
			 * @param {Number} size 截取长度
			 * @param {String} terminator 结束符（一个中文字符宽度）
			 * @return {String} 截取后的字符串
			 */
			aSubStringTer : function(string, size, terminator) {
				if (string == undefined || string == "" || size <= 0) {
					return "";
				}
				if (dm.string.aLength(string) <= size) {
					return string;
				}
				size = size - dm.string.aLength(terminator);
				return dm.string.aSubString(string, size) + terminator;
			},
			
			/**
			 * 按Arial字体宽度，按权重截取字符串
			 * 
			 * @name dm.string.aSubStringByWeight
			 * @function
			 * @grammar dm.string.aSubStringByWeight(totalStrLimit,firstStr,firstStrLimit,secondStr,secondStrLimit)
			 * @param {Number} totalStrLimit 字符串总长度
			 * @param {String} firstStr 高权重字符串
			 * @param {Number} firstStrLimit 高权重字符串最小长度
			 * @param {String} secondStr 低权重字符串
			 * @param {Number} secondStrLimit 低权重字符串最大长度
			 * @returns {Array} array['firstStr'] 截取后的高权重字符串 array['secondStr'] 截取后的低权重字符串
			 */
			aSubStringByWeight : function(totalStrLimit, firstStr, firstStrLimit, secondStr, secondStrLimit) {
				var firstStrLength = dm.string.aLength(firstStr);
				var secondStrLength = dm.string.aLength(secondStr);
				if (firstStrLength + secondStrLength > totalStrLimit) {
					if (secondStrLength > secondStrLimit) {
						if (totalStrLimit - firstStrLength < secondStrLimit) {
							firstStrLength = totalStrLimit - secondStrLimit;
							secondStrLength = secondStrLimit;
						} else {
							secondStrLength = totalStrLimit - firstStrLength;
						}
					} else {
						firstStrLength = totalStrLimit - secondStrLength;
					}
				}
				var result = new Array();
				result['firstStr'] = dm.string.aSubString(firstStr, firstStrLength);
				result['secondStr'] = dm.string.aSubString(secondStr, secondStrLength);
				return result;
			},
			
			/**
			 * 按Arial字体宽度，按权重截取字符串，带结束符
			 * 
			 * @name dm.string.aSubStringTerByWeight
			 * @function
			 * @grammar dm.string.aSubStringTerByWeight(totalStrLimit,firstStr,firstStrLimit,secondStr,secondStrLimit,terminator)
			 * @param {Number} totalStrLimit 字符串总长度
			 * @param {String} firstStr 高权重字符串
			 * @param {Number} firstStrLimit 高权重字符串最小长度
			 * @param {String} secondStr 低权重字符串
			 * @param {Number} secondStrLimit 低权重字符串最大长度
			 * @param {String} terminator 结束字符（一个中文字符宽度）
			 * @returns {Array} array['firstStr'] 截取后的高权重字符串 array['secondStr'] 截取后的低权重字符串
			 */
			aSubStringTerByWeight : function(totalStrLimit, firstStr, firstStrLimit, secondStr, secondStrLimit, terminator) {
				var firstStrLength = dm.string.aLength(firstStr);
				var secondStrLength = dm.string.aLength(secondStr);
				if (firstStrLength + secondStrLength > totalStrLimit) {
					if (secondStrLength > secondStrLimit) {
						if (totalStrLimit - firstStrLength < secondStrLimit) {
							firstStrLength = totalStrLimit - secondStrLimit;
							secondStrLength = secondStrLimit;
						} else {
							secondStrLength = totalStrLimit - firstStrLength;
						}
					} else {
						firstStrLength = totalStrLimit - secondStrLength;
					}
				}
				var result = new Array();
				result['firstStr'] = dm.string.aSubStringTer(firstStr, firstStrLength, terminator);
				result['secondStr'] = dm.string.aSubStringTer(secondStr, secondStrLength, terminator);
				return result;
			}
		},
		
		date : {
			/**
			 * 获取当前时间戳
			 * 
			 * @name dm.date.time
			 * @function
			 * @grammar dm.date.time()
			 * @return {Number} 时间戳
			 */
			time : function() {
			    return Math.floor(new Date().getTime() / 1000);
			},

			/**
			 * 将时间字符串转换为时间戳
			 * 
			 * @name dm.date.toTime
			 * @function
			 * @grammar dm.date.toTime()
			 * @param {String} time 时间字符串
			 * @return {Number} 时间戳
			 */
			toTime : function(time) {
				// 时间格式也可以是这样的"2009-10-1 13:0:0";
				var times_str = time.replace(/:/g, '-');
				var new_str = times_str.replace(/ /g, '-');
				var arr = new_str.split("-");
				var datum = new Date(Date.UTC(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]));
				var timeint = Math.ceil(datum.getTime() / 1000);
				return timeint;
			},

			/**
			 * 获取当前时间的毫秒数
			 * 
			 * @name dm.date.getTime
			 * @function
			 * @grammar dm.date.getTime
			 * @returns {Number} 毫秒数
			 */
			getTime : function() {
				return (new Date()).getTime();
			},
			
			/**
			 * 格式化时间戳
			 * 
			 * @name dm.date.toDate
			 * @function
			 * @grammar dm.date.toDate(format, timestamp)
			 * @param {String} format 规则参见php时间戳格式化方法
			 * @param {Number} timestamp
			 * @return {String} 格式化后的字符串
			 */
			toDate : function(format, timestamp) {
			    var a, jsdate=((timestamp) ? new Date(timestamp*1000) : new Date());
			    var pad = function(n, c){
			        if( (n = n + "").length < c ) {
			            return new Array(++c - n.length).join("0") + n;
			        } else {
			            return n;
			        }
			    };
			    var txt_weekdays = ["Sunday","Monday","Tuesday","Wednesday",
			        "Thursday","Friday","Saturday"];
			    var txt_ordin = {1:"st",2:"nd",3:"rd",21:"st",22:"nd",23:"rd",31:"st"};
			    var txt_months = ["", "January", "February", "March", "April",
			        "May", "June", "July", "August", "September", "October", "November",
			        "December"]; 
			    var f = {
			        // Day
			            d: function(){
			                return pad(f.j(), 2);
			            },
			            D: function(){
			                t = f.l(); return t.substr(0,3);
			            },
			            j: function(){
			                return jsdate.getDate();
			            },
			            l: function(){
			                return txt_weekdays[f.w()];
			            },
			            N: function(){
			                return f.w() + 1;
			            },
			            S: function(){
			                return txt_ordin[f.j()] ? txt_ordin[f.j()] : 'th';
			            },
			            w: function(){
			                return jsdate.getDay();
			            },
			            z: function(){
			                return (jsdate - new Date(jsdate.getFullYear() + "/1/1")) / 864e5 >> 0;
			            },

			        // Week
			            W: function(){
			                var a = f.z(), b = 364 + f.L() - a;
			                var nd2, nd = (new Date(jsdate.getFullYear() + "/1/1").getDay() || 7) - 1;

			                if(b <= 2 && ((jsdate.getDay() || 7) - 1) <= 2 - b){
			                    return 1;
			                } else{

			                    if(a <= 2 && nd >= 4 && a >= (6 - nd)){
			                        nd2 = new Date(jsdate.getFullYear() - 1 + "/12/31");
			                        return date("W", Math.round(nd2.getTime()/1000));
			                    } else{
			                        return (1 + (nd <= 3 ? ((a + nd) / 7) : (a - (7 - nd)) / 7) >> 0);
			                    }
			                }
			            },

			        // Month
			            F: function(){
			                return txt_months[f.n()];
			            },
			            m: function(){
			                return pad(f.n(), 2);
			            },
			            M: function(){
			                t = f.F(); return t.substr(0,3);
			            },
			            n: function(){
			                return jsdate.getMonth() + 1;
			            },
			            t: function(){
			                var n;
			                if( (n = jsdate.getMonth() + 1) == 2 ){
			                    return 28 + f.L();
			                } else{
			                    if( n & 1 && n < 8 || !(n & 1) && n > 7 ){
			                        return 31;
			                    } else{
			                        return 30;
			                    }
			                }
			            },

			        // Year
			            L: function(){
			                var y = f.Y();
			                return (!(y & 3) && (y % 1e2 || !(y % 4e2))) ? 1 : 0;
			            },
			            //o not supported yet
			            Y: function(){
			                return jsdate.getFullYear();
			            },
			            y: function(){
			                return (jsdate.getFullYear() + "").slice(2);
			            },

			        // Time
			            a: function(){
			                return jsdate.getHours() > 11 ? "pm" : "am";
			            },
			            A: function(){
			                return f.a().toUpperCase();
			            },
			            B: function(){
			                // peter paul koch:
			                var off = (jsdate.getTimezoneOffset() + 60)*60;
			                var theSeconds = (jsdate.getHours() * 3600) +
			                                 (jsdate.getMinutes() * 60) +
			                                  jsdate.getSeconds() + off;
			                var beat = Math.floor(theSeconds/86.4);
			                if (beat > 1000) beat -= 1000;
			                if (beat < 0) beat += 1000;
			                if ((String(beat)).length == 1) beat = "00"+beat;
			                if ((String(beat)).length == 2) beat = "0"+beat;
			                return beat;
			            },
			            g: function(){
			                return jsdate.getHours() % 12 || 12;
			            },
			            G: function(){
			                return jsdate.getHours();
			            },
			            h: function(){
			                return pad(f.g(), 2);
			            },
			            H: function(){
			                return pad(jsdate.getHours(), 2);
			            },
			            i: function(){
			                return pad(jsdate.getMinutes(), 2);
			            },
			            s: function(){
			                return pad(jsdate.getSeconds(), 2);
			            },
			            //u not supported yet

			        // Timezone
			            //e not supported yet
			            //I not supported yet
			            O: function(){
			               var t = pad(Math.abs(jsdate.getTimezoneOffset()/60*100), 4);
			               if (jsdate.getTimezoneOffset() > 0) t = "-" + t; else t = "+" + t;
			               return t;
			            },
			            P: function(){
			                var O = f.O();
			                return (O.substr(0, 3) + ":" + O.substr(3, 2));
			            },
			            //T not supported yet
			            //Z not supported yet

			        // Full Date/Time
			            c: function(){
			                return f.Y() + "-" + f.m() + "-" + f.d() + "T" + f.h() + ":" + f.i() + ":" + f.s() + f.P();
			            },
			            //r not supported yet
			            U: function(){
			                return Math.round(jsdate.getTime()/1000);
			            }
			    };

			    return format.replace(/[\\]?([a-zA-Z])/g, function(t, s){
			        if( t!=s ){
			            // escaped
			            ret = s;
			        } else if( f[s] ){
			            // a date function exists
			            ret = f[s]();
			        } else{
			            // nothing special
			            ret = s;
			        }
			        return ret;
			    });
			},

			/**
			 * 返回当前时间
			 * 
			 * @name dm.date.getDateString
			 * @function
			 * @grammar dm.date.getDateString()
			 * @returns 2009-10-1 13:59:59
			 */
			getDateString : function() {
				var s = dm.date.toDate("Y-m-d H:i:s",(new Date()).getTime()/1000);
				return s;
			},

			/**
			 * 友好化格式化时间戳
			 * 
			 * @name dm.date.toFriendlyDate
			 * @function
			 * @grammar dm.date.toFriendlyDate(timestamp)
			 * @param {Number} timestamp
			 * @returns {String} 一分钟内，返回“n秒前”，一小时内返回“n分钟前”，当天之内返回“今天H：m” 本年内返回“n月j日
			 *          G:i”，超过本年返回“Y年n月j日 G:i”
			 */
			toFriendlyDate : function(timestamp) {
				var s = dm.date.time() - 2;
				var i = dm.date.time() - 60;
				var h = dm.date.time() - (60 * 60);
				var date = new Date();
				var d = parseInt(dm.date.toTime(date.getFullYear() + "-" + date.getMonth()
						+ "-" + date.getDate() + " 0:0:0"));
				// alert("d:"+d+";h:"+h+";i:"+i+";t:"+timestamp);
				if (dm.date.toDate("Y") > dm.date.toDate("Y", timestamp)) {
					return dm.date.toDate("Y年n月j日 G:i", parseInt(timestamp));
				} else if (d > timestamp) {
					return dm.date.toDate("n月j日 G:i", parseInt(timestamp));
				} else if (h > timestamp) {
					return "今天" + dm.date.toDate("G:i", parseInt(timestamp));
				} else if (i > timestamp) {
					return dm.date.toDate("i", h - parseInt(timestamp)).replace(/(^0*)/g,
							'')
							+ "分钟前";
				} else if (s >= timestamp) {
					return dm.date.toDate("s", i - parseInt(timestamp)).replace(/(^0*)/g,
							'')
							+ "秒前";
				} else {
					return "2秒前";
				}
			},
			
			/**
			 * 友好化格式化时间戳(短模式)
			 * 
			 * @name dm.date.toShortFriendlyDate
			 * @function
			 * @grammar dm.date.toShortFriendlyDate(timestamp)
			 * @param {Number} timestamp
			 * @returns {String} 一分钟内，返回“n秒前”，一小时内返回“n分钟前”，当天之内返回“H：m” 本年内返回“n-j”，超过本年返回“Y-n-j”
			 */
			toShortFriendlyDate : function(timestamp) {
				var s = dm.date.time() - 2;
				var i = dm.date.time() - 60;
				var h = dm.date.time() - (60 * 60);
				var date = new Date();
				var d = parseInt(dm.date.toTime(date.getFullYear() + "-" + date.getMonth()
						+ "-" + date.getDate() + " 0:0:0"));
				// alert("d:"+d+";h:"+h+";i:"+i+";t:"+timestamp);
				if (dm.date.toDate("Y") > dm.date.toDate("Y", timestamp)) {
					return dm.date.toDate("Y-n-j", parseInt(timestamp));
				} else if (d > timestamp) {
					return dm.date.toDate("n-j", parseInt(timestamp));
				} else if (h > timestamp) {
					return dm.date.toDate("G:i", parseInt(timestamp));
				} else if (i > timestamp) {
					return dm.date.toDate("i", h - parseInt(timestamp)).replace(/(^0*)/g,
							'')
							+ "分钟前";
				} else if (s >= timestamp) {
					return dm.date.toDate("s", i - parseInt(timestamp)).replace(/(^0*)/g,
							'')
							+ "秒前";
				} else {
					return "2秒前";
				}
			}
		},
		
		array : {
			/**
			 * 打乱数组(或英文逗号分隔的字符串)
			 * 
			 * @name dm.array.randArray
			 * @function
			 * @grammar dm.array.randArray(arr)
			 * @param arr
			 *            数组（或英文逗号分隔的字符串）
			 * @returns 打乱后的数组（或英文逗号分隔的字符串）
			 */
			randArray : function(arr) {
				if (typeof arr == 'Array') {
					return arr.sort(function() {
						return 0.5 - Math.random();
					});
				}
				arr = arr.split(',');
				arr.sort(function() {
					return 0.5 - Math.random();
				});
				return arr.join();
			},

			/**
			 * 将数组转换为json字符串
			 * 
			 * @name dm.array.toJsonString
			 * @function
			 * @grammar dm.array.toJsonString(arr)
			 * @param arr
			 * @returns {String}
			 */
			toJsonString : function(arr) {
				var jsonSrt = "{";
				for ( var key in arr) {
					jsonSrt += key + ":" + "\"" + arr[key] + "\",";
				}
				jsonSrt = jsonSrt.substring(0, jsonSrt.lastIndexOf(','));
				jsonSrt += "}";
				return jsonSrt;
			}
		},
		
		validate : {
			regxUserName : function(userName) {
				var regx = /^[A-Za-z0-9.@_-]*[A-Za-z.@_-]+[A-Za-z0-9.@_-]*$/i;		//英文、数字及“@” “_” “.”等符号，不能全为数字
				return regx.test(userName);
			},

			regxNick : function(nick) {
				var regx = /^[0-9a-zA-Z\u4e00-\u9fa5]+[0-9a-zA-Z\u4e00-\u9fa5\u2669-\u266f_-]*$/i;		//昵称限制中文、英文、数字、_、-组合,♫♬♪♭♮♯ 
				return regx.test(nick);
			},

			regxPassword : function(password) {
				var regx = /^[A-Za-z0-9]+$/i;		//用来用户注册。匹配由英文、数字组成的字符串
				return regx.test(password);
			},

			regxEmail : function(email) {
				var regx = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/i;
				return regx.test(email);
			},

			regxPhone : function(phone) {
				var regx = /^1[3458][0-9]{9}$/i;
				return regx.test(phone);
			},

			regxIDCard : function(idCard) {
				//身份证正则表达式(15位) 
				var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/g; 
				//身份证正则表达式(18位) 
				var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/g;
				if(isIDCard1.test(idCard)) {
					return true;
				}
				return isIDCard2.test(idCard);
			},

			regxInt : function(num) {
				var regx = /^\d*$/i;
				return regx.test(num);
			}
		},
		
		tool : {
			
			formatFace : function (face) {
			    var isSmall = arguments[1] === false? false : true;
			    
			    if (dm.isEmpty(face)) {
			        if (isSmall) {
			            return "/app/web/public/templates/images/default/face_s.jpg";
			        }
			        else {
			            return "/app/web/public/templates/images/default/face_b.jpg";
			        }
			    }
			    if (face.indexOf("http:") == -1) {
			        if (isSmall) {
			            return "http://img1.sns.duomi.com/default/small/head" + face;
			        }
			        else {
			            return "http://img1.sns.duomi.com/default/big/head" + face;
			        }
			    }
			    else {
			        return face;
			    }
			},
			
			/**
			* 分页构建方法
			* @param pageData
			* pageData = {
			* 	curpg:1,//当前为第几页
			* 	count:20,//共有多少条数据
			* 	pgsize:10,//每页多少条
			* 	url:'http://host/listtest.shtml?page=$pagenum$&cid=12&key=add',//分页跳转地址
			* 	click:'page(1,$pagenum$,30)',//分页点击事件（设置此项url，parameter不可用）
			* 	start:'<a href="$url$" $click$>首页</a>',//首页链接模板，不需要则设置为空
			* 	end:'<a href="$url$" $click$>尾页</a>',//尾页链接模板，不需要则设置为空
			* 	pre:'<a href="$url$" $click$>上一页</a>',//上一页链接模板
			* 	next:'<a href="$url$" $click$>下一页</a>',//下一页链接模板
			* 	current:'<a class="current" href="$url$" $click$>$pagenum$</a>',//当前页链接模板
			* 	normal:'<a href="$url$" $click$>$pagenum$</a>',//普通页链接模板
			* 	totals:'<span>共$count$条数据</span>',//共多少条数据模板
			* }
			* @return html 分页html代码
			*/
			makepage : function(pageData) {
			    var pageurl = pageData.url;
			    var click = '';
			    if(!dm.isEmpty(pageData.click)) {
			    	pageurl = '#';
			    	click = 'onclick="'+pageData.click+';"';
			    }
			    var curPage = Math.ceil(pageData.curpg);
			    var pageSize = pageData.pgsize;
			    var pageCount = pageData.count;
			    var pageLast = Math.ceil(pageCount / pageSize);
			    var pageBtnNum = 5; //按钮数目
			    var pageTemplate = "";
			    if(pageCount<=0) {
			    	return '';
			    }
			    if (pageLast <= 1) {
			    	if(!dm.isEmpty(pageData.totals)){
				    	pageTemplate = pageData.totals.replace(/\$count\$/g, pageCount);
				    	return pageTemplate;
				    }
			        return '';
			    }
			    var pageCorrection = Math.floor(pageBtnNum / 2);
			    var pageCorrectionPro = Math.floor(pageBtnNum / 2);
			    var pageCorrectionBfo = Math.floor(pageBtnNum / 2);
			    if (pageBtnNum % 2 == 0) {
			        pageCorrectionPro -= 1;
			    }

			    var pageBeginNum = Math.floor((curPage - pageCorrection) / pageBtnNum) * pageBtnNum;
			    var pageEndNum = Math.floor(curPage / pageBtnNum) * pageBtnNum + pageBtnNum;

			    pageBeginNum = curPage - pageCorrectionPro - 2 <= 0 ? 1 : curPage - pageCorrectionPro;

			    if (pageLast - curPage + pageCorrectionPro < pageBtnNum) {
			        pageBeginNum = pageLast - pageBtnNum + 1;
			    }
			    if (pageBeginNum <= 0) {
			        pageBeginNum = 1;
			    }

			    pageEndNum = pageLast - curPage - 1 <= pageCorrection ? pageLast : curPage + pageCorrection;

			    if (curPage - pageBtnNum + 1 < 0 && pageLast > pageBtnNum) {
			        pageEndNum = pageBtnNum;
			    }
			    if (pageLast <= pageBtnNum + 1) {
			        pageBeginNum = 1;
			        pageEndNum = pageLast;
			    }
			    
			    if(!dm.isEmpty(pageData.start)) {
			    	pageTemplate += pageData.start.replace(/\$url\$/g, pageurl).replace(/\$click\$/g,click).replace(/\$pagenum\$/g,'1');
			    }

			    if (curPage > 1 && !dm.isEmpty(pageData.pre)) {
			        //pageTemplate += "<a hidefocus='true' href='" + pageurl + parameter + "&p=" + (curPage - 1) + "'>上一页</a>";
			        pageTemplate += pageData.pre.replace(/\$url\$/g, pageurl).replace(/\$click\$/g,click).replace(/\$pagenum\$/g,''+(curPage - 1));
			    }
			    /*if (pageBeginNum > 2 && pageLast != pageBtnNum && pageLast != pageBtnNum + 1) {
			        pageTemplate += "<a hidefocus='true' href='" + pageurl + parameter + "&p=" + "'>1</a>";
			        pageTemplate += "<a hidefocus='true' href='" + pageurl + parameter + "&p=" + (pageBeginNum - 1) + "'>...</a>";
			    }*/


			    for (var i = pageBeginNum; i <= pageEndNum; i++) {
			        if (i == curPage) {
			        	pageTemplate += pageData.current.replace(/\$url\$/g, pageurl).replace(/\$click\$/g,click).replace(/\$pagenum\$/g,curPage);
			        }
			        else{
			        	pageTemplate += pageData.normal.replace(/\$url\$/g, pageurl).replace(/\$click\$/g,click).replace(/\$pagenum\$/g,i);
			        }
			    }

			    /*if (pageEndNum < pageLast - 1 && pageLast != pageBtnNum && pageLast != pageBtnNum + 1) {
			        pageTemplate += "<a hidefocus='true' href='" + pageurl + parameter + "&p=" + (pageEndNum + 1) + "'>...</a>";
			        pageTemplate += "<a hidefocus='true' href='" + pageurl + parameter + "&p=" + pageLast + "'>" + pageLast + "</a>";
			    }*/
			    if (curPage != pageLast) {
			        pageTemplate += pageData.next.replace(/\$url\$/g, pageurl).replace(/\$click\$/g,click).replace(/\$pagenum\$/g,(curPage + 1));
			    }
			    
			    if(!dm.isEmpty(pageData.end)) {
			    	pageTemplate += pageData.end.replace(/\$url\$/g, pageurl).replace(/\$click\$/g,click).replace(/\$pagenum\$/g,pageLast);
			    }
			    
			    if(!dm.isEmpty(pageData.totals)){
			    	pageTemplate = pageData.totals.replace(/\$count\$/g, pageCount) +pageTemplate;
			    }
			    
			    return pageTemplate;
			}
		}
};

dm.isEmpty = dm.object.isEmpty;
dm.isFunction = dm.object.isFunction;

dm.HTMLdecode = dm.string.HTMLdecode;
dm.HTMLencode = dm.string.HTMLencode;
dm.urlDecode = dm.string.urlDecode;
dm.urlEncode = dm.string.urlEncode;
dm.trim = dm.string.trim;

function LOG(b) {
//	return;
    try {
        console.log(b);
    } catch(a) {
    }
}
function TRACE(b){try{console.trace(b);}catch(a){}}