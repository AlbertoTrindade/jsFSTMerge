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

carDeclaration: var cars = [ferrari, mustang];

{
	ferrari.color = "blue";
}

if (ferrari.color === 'blue') {
	console.log('Ferrari is blue');
}
else {
	console.log('Ferrari is not blue');
}

var i = 0;

do {
	i++;
} while (i < 10);

while (i < 10) {
	i++;
}

for (i = 0; i < 10; i++) {
	console.log("Loop");
}

for (var j = 0; j < 5; j++) {
	console.log("Another loop");
}

for (var property in cars[0]) {
	console.log(property);
}

var list = [1, 2, 3, 4, 5];
for (i in list) {
	if (i === 2) continue;
	
	console.log(i);
	
	if (i == 3) break;
}