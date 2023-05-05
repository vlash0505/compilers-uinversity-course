function areEqualCaseInsensitive(str1, str2) {
    return str1.toUpperCase() === str2.toUpperCase();
}

var floatNum = 3.125e7;    //equal to 31250000
var anotherFloatNum = 5.11;

const num = 44;

const strPrim = "Sample text"; // A literal is a string primitive
const strPrimSingleQuote = 'Sample text but in single quote'; // A literal is a string primitive
const strPrim2 = String(1);
const strPrim3 = String(true);

// String with new returns a string wrapper object.
const strObj = new String(strPrim);

console.log(typeof strPrim);

const s1 = "2 + 2"; // creates a string primitive
const s2 = new String("2 + 2"); // creates a String object
console.log(eval(s1)); // returns the number 4

/*
Multiline
comment
*/

const a = 5;
const b = 10;
console.log("Fifteen is " + (a + b) + " and\nnot " + (2 * a + b) + ".");

function myFunc(theObject) {
    theObject.make = "Toyota";
}

const mycar = {
    make: "Honda",
    model: "Accord",
    year: 1998,
};

console.log(mycar.make); // "Honda"
myFunc(mycar);
console.log(mycar.make); // "Toyota"

/*
The code below will change
the heading with id = "myH"
and the paragraph with id = "myP"
in my web page:
*/
document.getElementById("myH").innerHTML = "My Page";
document.getElementById("myP").innerHTML = "My paragraph.";

(a) => {
    return a + 100;
};

