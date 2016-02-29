onload = function() {
	console.log("LOADED");
	init();
}

function getData(url, callback) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status < 400) {
			callback(JSON.parse(xhr.responseText));
			console.log(JSON.parse(xhr.responseText));
		}
	};
	xhr.send(null);
}

function updateData(method, url, object, callback) {
	var xhr = new XMLHttpRequest();
	xhr.open(method, url);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function() {
		console.log(xhr.status);
		console.log(xhr.readyState);
		console.log(xhr.responseText);
		console.log(xhr.getAllResponseHeaders());
	};
	if (object) {
		xhr.send(JSON.stringify(object));
	} else {
		xhr.send(null);
	}
}

function init() {
	var form = document.allButton;
	form.allEvents.addEventListener("click", listEventsClicked);
	var form1 = document.idButton;
	form1.eventById.addEventListener("click", eventByIdClicked);
}

function deleteEvent(e) {
	e.preventDefault();
	var id = e.target.getAttribute("eventId");
	//use this path locally:http://localhost:8080/EventTracker/
	updateData("DELETE","rest/event/" + id);
}

function listEventsClicked(e) {
	e.preventDefault();
	clearData();
	//use this path locally:http://localhost:8080/EventTracker/
	getData("rest/events", listEvents);
}
function listEvents(events) {
	// var body = document.querySelector("body");
		var div = document.getElementById("buttonDiv");
		div.innerHTML="";
		for (i=0; i<events.length; i++) {
		var p = document.createElement("p");
		var display = "ID: " + events[i].id + " " + "Date: " + events[i].eventDate + " " + "weight: " +events[i].weight + " ";

		p.innerHTML = display;
		div.appendChild(p);
		var dbutton = document.createElement("Button");
		dbutton.setAttribute("eventId", events[i].id.toString());
		var t = document.createTextNode("Delete Event");
		dbutton.appendChild(t);
		div.appendChild(dbutton);
		dbutton.addEventListener("click", deleteEvent);
	}
}
function eventByIdClicked(e) {
	e.preventDefault();
	var input;
	input = document.getElementById("eventId");
	var div = document.getElementById("buttonDiv");
	div.innerHTML="";
	if (parseInt(input.value)) {
	clearData();
	//use this path locally:http://localhost:8080/EventTracker/
	getData("rest/event/" + input.value, getEventById);
	}
 else {
	 clearData();
	 flashError("Please enter a valid id number");
 }
}

function getEventById(event) {
	//clearing delete buttons:
	var div = document.getElementById("buttonDiv");
	div.innerHTML="";
	var body = document.querySelector("body");
	var p = document.createElement("p");
	var display = "ID: " + event.id + " "+ "Date: " + event.eventDate + " " + "weight: " +event.weight;
	p.innerHTML = display;
	div.appendChild(p);
	var dbutton = document.createElement("Button");
	dbutton.setAttribute("eventId", event.id.toString());
	var t = document.createTextNode("Delete Event");
	dbutton.appendChild(t);
	div.appendChild(dbutton);
	dbutton.addEventListener("click", deleteEvent);
}

function flashError(err) {
var body = document.querySelector("body");
var p = document.createElement("p");
p.innerHTML = err;
body.appendChild(p);
body = document.idButton.parentNode;
body.insertBefore(p, document.idButton);
}

function clearData() {
	var data = document.querySelectorAll("p");
	for (var i = 0; i < data.length; i++){
		data[i].parentNode.removeChild(data[i]);
	}
}
