function x(a, b, c) {
  function y(d, e) {
    return d + e;
  }

  var s = a + b + c;
  
  function w(a) {
	return a + b;
  };;; // empty statements
  
  var z = function(f) {
	  return f(a);
  };
  
  a = z(function(x) { return x + x; });
  
  a = a * w(a);
  a = a / (function() { return b; })();
  
  var ternary = a ? b : c;
  ternary = ternary || s;
  ternary = ternary && a;
  
  return function() { return a };
}