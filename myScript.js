// myScript.js

// Calculate the new Total

function calcTotal(var x) {
   var total = document.getElementById("Total").value;
   total = total * 1;
   x = x * 1;
   if (total.cheked == true) {
      total += x;
   }
   else {
      total -= x  
   }
   total = total.toFixed(2);
   
   document.getElementById("Total").value = "Heere";
}

// Make sure values have been entered

function checkValues() {

}

function validPhone() {
   
}

function validCard() {
   
}

// Change the picture to be a new one

function changeLuke() {
   document.getElementById("pic").src = "Luke.jpg";
}

function changeVader() {
   document.getElementById("pic").src = "Vader.jpg";
}

function changeObi() {
   document.getElementById("pic").src = "Obi.jpg";
}

function changeSidious() {
   document.getElementById("pic").src = "Sidious.jpg";
}

function changeBack() {
   document.getElementById("pic").src = "lightsaber.jpe";  
}

// On reset set the focus to the First Name

function reset() {
   document.getElementById("First").focus();
}
