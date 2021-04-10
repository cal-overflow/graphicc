const connect = require('connect');
var static = require('serve-static');
const PORT = process.env.PORT || 3000;
connect().use(static('views/')).listen(PORT, () => console.log('Server running on port ${PORT}'));
