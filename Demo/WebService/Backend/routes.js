const express = require('express')

const router = express.Router()

router.post('/', (req, res) => {
    const { num1, num2 } = req.body;
    const result = parseInt(num1) + parseInt(num2);
    console.log(result)
    res.json( {result} );
    // res.send('asdlkfjsal;dkjf')
})

module.exports = router;