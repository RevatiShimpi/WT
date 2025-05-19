// server.js
const express = require('express');
const mysql   = require('mysql2/promise');
const cors    = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

// 1. MySQL connection pool (adjust user/password as needed)
const pool = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: '12345',
  database: 'electricity_billing',
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

// 2. Bill calculation function
function calculateBill(units) {
  let bill = 0;
  if (units <= 50) {
    bill = units * 3.5;
  } else if (units <= 150) {
    bill = 50 * 3.5 + (units - 50) * 4.0;
  } else if (units <= 250) {
    bill = 50 * 3.5 + 100 * 4.0 + (units - 150) * 5.2;
  } else {
    bill = 50 * 3.5 + 100 * 4.0 + 100 * 5.2 + (units - 250) * 6.5;
  }
  return bill;
}

// 3. POST /api/calculate
app.post('/api/calculate', async (req, res) => {
  const { name, email, address, units } = req.body;
  if (!name || !units) {
    return res.status(400).json({ error: 'Name and units are required.' });
  }

  const conn = await pool.getConnection();
  try {
    await conn.beginTransaction();

    // a) insert consumer
    const [consResult] = await conn.execute(
      'INSERT INTO consumer (name, email, address) VALUES (?, ?, ?)',
      [name, email || null, address || null]
    );
    const consumerId = consResult.insertId;

    // b) calculate amount
    const amount = calculateBill(Number(units));

    // c) insert billing
    await conn.execute(
      'INSERT INTO billing (consumer_id, units, amount) VALUES (?, ?, ?)',
      [consumerId, units, amount]
    );

    await conn.commit();
    res.json({ amount });
  } catch (err) {
    await conn.rollback();
    console.error(err);
    res.status(500).json({ error: 'Database error.' });
  } finally {
    conn.release();
  }
});

// 4. Start server
const PORT = 3000;
app.listen(PORT, () => {
  console.log(`⚡ Server listening on http://localhost:${PORT}`);
});
