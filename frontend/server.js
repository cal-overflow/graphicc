const connect = require('connect');
var static = require('serve-static');

connect().use(static('views/')).listen(8080, () => console.log('Server running on port 8080'));
