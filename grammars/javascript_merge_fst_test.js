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
  
  a = z(function(x) { return (x) + 10; });
  
  a = a * w(a);
  a = a / (function() { return b; })();
  
  var ternary = a ? b : c;
  ternary = ternary || s;
  ternary = ternary && a;
  
  return function() { return a };
}

function Car (type, color) {
    this.type = type;
    this.color = color;
}

var ferrari = new Car("sport", "red");

var mustang = { type: 'sport', color: 'yellow' };

var cars = [ferrari, mustang];

{
	ferrari.color = "blue";
}