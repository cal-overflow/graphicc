const http = require('http');
const express = require('express');
const PORT = process.env.PORT || 3000;

const app = express();
const server = http.createServer(app);

server.listen(port, () => {
  console.log('Server running on port: ${port}');
});
