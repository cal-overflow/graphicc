const connect = require('connect');
var static = require('serve-static');
const PORT = process.env.PORT || 3000;
const fs = require('fs');

fs.readdir(directory, (err, files) => {
    files.forEach(file => {
        console.log(file);
    });
});

connect().use(static('views/')).listen(PORT, () => console.log('Server running on port ${PORT}'));

/*const PORT = process.env.PORT || 3000;
require('http').createServer(require('serve-static')('.')).listen(PORT);*/
