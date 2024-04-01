const express = require('express')
const app = express()
const bodyParser = require('body-parser');
const cors = require('cors')
const morgan = require('morgan')

app.use(bodyParser.json());
app.use(cors());
app.use(morgan('tiny'))

const router = require('./routes')

app.use('/api/v1', router)

const start = async () => {
    try {
        app.listen(3000, () => {
            console.log("Listening on 3000...")
        })
    } catch (err) {
        console.log(err)
    }
}

start() 