//const serverURL = "https://8256c7bf-2e3d-4402-9260-69d9f4ec1717.mock.pstmn.io";//Change me to the localhost/springboot server address
const serverURL = "https://secret-beyond-18338.herokuapp.com/http://97.125.225.123:8080";

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
  /*for(i = 0; i < lines.length; i++)
  {
    lines[i] = lines[i].replaceAll('\"', '');
    lines[i] = lines[i].replaceAll(',', '');
    lines[i] = lines[i].replaceAll(' ', '');
    temp = lines[i].split(':');
    console.log(temp);
    xCords.push(parseInt(temp[0]));
    yCords.push(parseInt(temp[1]));
  }*/
  setCoords(xCords, yCords);
  drawPlot();
}
