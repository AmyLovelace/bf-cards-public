const fs = require('fs');
const path = require('path');

const filePath = path.join(__dirname, 'myFile.txt');
let content = fs.existsSync(filePath) ? fs.readFileSync(filePath, 'utf8') : '';

content += '\nThis is a comment added by the cron job.';

fs.writeFileSync(filePath, content);
