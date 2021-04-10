var xCord, yCord;
var gotCoords;
function drawPlot() {

  const colors = {'grid': '#C0C0C0', 'axis': '#000000', 'line': '#FF0000'}
  const graph = document.getElementById('graphArea');
  const width = graph.width;
  const height = graph.height;
  const size = document.getElementById('range-slider').value;
  const gridSpace = width / size;
  const ctx = graph.getContext('2d');
  ctx.clearRect(0, 0, width, height);

  // FIll grid and axis lines
  ctx.strokeStyle = colors.grid;
  for (var i = 0; i <= size; i++) {
    ctx.beginPath();
    ctx.moveTo(i*gridSpace, 0);
    ctx.lineTo(i*gridSpace, height);
    ctx.stroke();
    ctx.closePath();

    ctx.beginPath();
    ctx.moveTo(0, i*gridSpace);
    ctx.lineTo(width, i*gridSpace);
    ctx.stroke();
    ctx.closePath();
  }

  // Fill Axis lines (this needs to be done seperately or the axis is "burried" behind gridlines)
  ctx.strokeStyle = colors.axis;
  ctx.beginPath();
  ctx.moveTo(width/2, 0);
  ctx.lineTo(width/2, height);
  ctx.stroke();
  ctx.closePath();

  ctx.beginPath();
  ctx.moveTo(0, height/2);
  ctx.lineTo(width, height/2);
  ctx.stroke();
  ctx.closePath();
  printCoords();
}
function setCoords(xCords, yCords)
{
  xCord = xCords;
  yCord = yCords;
  gotCoords = true;
}
function printCoords()
{
  if(gotCoords)
  {
    console.log(xCord);
    console.log(yCord);
    var scale = (((110 - document.getElementById('range-slider').value) / document.getElementById('range-slider').value) * 5); // NOT EXACTLY CORRECT
    const color = {'grid': '#C0C0C0', 'axis': '#000000', 'line': '#FF0000'}
    const graph = document.getElementById('graphArea');
    const ctz = graph.getContext('2d');
    midW = graph.width / 2;
    midH = graph.height / 2;
    for(i = 0; i < xCord.length; i++)
    {
      ctz.strokeStyle = color.line;
      ctz.beginPath();
      ctz.moveTo((scale * xCord[i]) + midW, (scale * - yCord[i]) + midH);
      if(i != 0)
      {
        ctz.lineTo((scale * xCord[i-1]) + midW, (scale * - yCord[i-1]) + midH);
      }
      ctz.stroke();
      ctz.closePath();
    }
    console.log("DONE!");
  }
  else {
    //console.log("NO CORDS!");
  }
}
