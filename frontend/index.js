function submit()
{
  postRequest("https://8256c7bf-2e3d-4402-9260-69d9f4ec1717.mock.pstmn.io/test");
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
    method: "POST"
  }).then(
    response => response.text()
  ).then(
    html => console.log(html)
  );
}
