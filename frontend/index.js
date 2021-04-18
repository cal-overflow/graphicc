const serverURL = "https://secret-beyond-18338.herokuapp.com/http://127.0.0.1:8080";
// http:127.0.0.1:8080 is placeholder for a spring (backend)server.
// This has been changed from the actual backend IP, since it is no longer being hosted.

function submit() {
  let equation = document.getElementById("equation_input").value;
  var encoded_eqation = '';
  for (var i = 0; i < equation.length; i++) {
    if (equation.charAt(i) == '/') encoded_eqation += '~';
    else if (equation.charAt(i) == '^') encoded_eqation += '@';
    else if (equation.charAt(i) == '%') encoded_eqation += '_';
    else encoded_eqation += equation.charAt(i);
  }
  postRequest(serverURL + "/equationIn/" + encoded_eqation);
}

function getRequest(url)  {
  fetch(url,{
    method: "GET"
  }).then(
    response => response.text()
  ).then(
    html => console.log(html)
  );
}

function postRequest(url) {
  fetch(url,{
    method: "POST"
  }).then(
    response => {
      if(!response.ok)
      {
        alert("Please check your equation!");
        throw new Error('Network response failed!');
      }
      return response.text();
    }
  ).then(
      html => parseCoords(html)
  );
}
function parseCoords(html){
  var lines = html.substring(1, html.length - 1).replace(/"/g, "");
  var points = lines.split(',');
  var xCords = new Array();
  var yCords = new Array();
  var temp;
  for (var i = 0; i < points.length; i++)  {
    let thisPoint = points[i];
    xCords.push(parseInt(points[i].split(':')[0]));
    yCords.push(parseInt(points[i].split(':')[1]));
  }
  setCoords(xCords, yCords);
  drawPlot();
}
