function x(a, b, c) {
  function y(d, e) {
    return d + e;
  }

  var s = a + b + c;
  
  function w(a) {
	return a + b;
  }
  
  var z = function() {
	  return a + b;
  }
  
  return s;
}