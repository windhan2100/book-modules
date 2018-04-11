/**
 * 
 */
(function($) {
	/* 自定义函数 
	 * var allVars = $.getUrlVars();
	 * */
	$.extend({
		 	settings: {
		      test      : 0,
		      overlay      : true
		    },
			getUrlVars: function(){
			    var vars = [], hash;
			    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			    for(var i = 0; i < hashes.length; i++)
			    {
			      hash = hashes[i].split('=');
			      vars.push(hash[0]);
			      vars[hash[0]] = hash[1];
			    }
			    return vars;
			  },
			getUrlVar: function(name){
			    return $.getUrlVars()[name];
			  },
			getUri: function(){
				    var pageURI='http://'+window.location.hostname; 
					if (window.location.port!=''){
					       pageURI=pageURI+':'+window.location.port;
					}
					pageURI=pageURI+window.location.pathname;
					//console.log(pageURI);
					return pageURI;
			  },
			getQueryString: function(){
				    var args=""+window.location.search; 
				    args=args.replace(/^\s*|\s*$/g, "");
				    args=args.replace(/^\?/, "");
					return args;
			  },
			toUrlString: function(){
				var pageURI='http://'+window.location.hostname;
				var args=""+window.location.search;
				args=args.replace(/^\s*|\s*$/g, "");
				if (window.location.port!=''){
				       pageURI=pageURI+':'+window.location.port;
				}
				pageURI=pageURI+window.location.pathname+args;
				return pageURI;
			}
	});
	/**
	 * $(this).isinteger('1');
	 */
	$.fn.extend({
		isinteger: function(num)	{ 
			var rt=false;
			var re=new RegExp("^-?[\\d]*$"); 
			if(re.test(num)) 
				rt=!isNaN(parseInt(num)); 
			else 
				rt=false; 
			return rt;
		}
	}); 
	 

	
})(jQuery); 
