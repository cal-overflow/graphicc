const serverURL = "https://8256c7bf-2e3d-4402-9260-69d9f4ec1717.mock.pstmn.io";

function submit()
{
  postRequest(serverURL + "/test");
}
function getRequest(url)
{
  fetch(url,{
    method: "GET"
  }).then(
    response => response.text()
  ).then(
    html => console.log(html)
  );
}
function postRequest(url)
{
  fetch(url,{
    method: "POST",
    body : JSON.stringify({
        equation : "x^2"
      })
  }).then(
    response => response.text()
  ).then(
    html => parseCoords(html)
  );
}
function parseCoords(html){
  var lines = html.split(/\n/);
  var xCords = new Array();
  var yCords = new Array();
  var temp;
  for(i = 1; lines[i] != '}' && i < lines.length; i++)
  {
    lines[i] = lines[i].replaceAll('\"', '');
    lines[i] = lines[i].replaceAll(',', '');
    lines[i] = lines[i].replaceAll(' ', '');
    temp = lines[i].split(':');
    xCords.push(parseInt(temp[0]));
    yCords.push(parseInt(temp[1]));
  }
  console.log(xCords);
  console.log(yCords);
}
