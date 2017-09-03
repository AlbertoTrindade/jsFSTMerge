function f1(a, b, c) {
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

var a, x, y;
var r = 10;

with (Math) {
  a = PI * r * r;
  x = r * cos(PI);
  y = r * sin(PI / 2);
}

var foo = 0;
switch (foo) {
  case -1:
    console.log('negative 1');
    break;
  case 0: // foo is 0 so criteria met here so this block will run
    console.log(0);
    // NOTE: the forgotten break would have been here
  case 1: // no break statement in 'case 0:' so this case will run as well
    console.log(1);
    break; // it encounters this break so will not continue into 'case 2:'
  case 2:
    console.log(2);
    break;
  default:
    console.log('default');
}

function errorFunction() {
	throw new Error('An error');
}

try {
	errorFunction();
}
catch (err) {
	console.log(err.message);
}
finally {
	console.log('Whatever');
}

// function as array element
var functions = [function() { return 2; }, function() {  return 3; }];

// function expression in assignment expression
this.changePriority = function (page, priority) {
    if (!pages[page] || pages[page].priority === priority) {
      return;
    }

    pages[page].priority = priority;
    $timeout.cancel(scheduleTimeout);
    scheduleTimeout = $timeout(schedule, 1000);
};

// function expression in or expression
this.changePriority = f1 || function anotherFunction() { };