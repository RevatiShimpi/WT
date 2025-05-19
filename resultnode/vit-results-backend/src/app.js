// src/app.js
const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const db = require('./db');
require('dotenv').config();


const app = express();
app.use(cors());
app.use(bodyParser.json());

// Helper: calculate total result for one student
// src/app.js (inside getResult)
async function getResult(studentId) {
  const [mses] = await db.query(
    'SELECT subject, marks FROM MSE WHERE student_id = ?',
    [studentId]
  );
  const [eses] = await db.query(
    'SELECT subject, marks FROM ESE WHERE student_id = ?',
    [studentId]
  );

  // build a map: subject → { mse: Number, ese: Number }
  const result = {};

  mses.forEach(m => {
    result[m.subject] = { mse: parseFloat(m.marks) };
  });
  eses.forEach(e => {
    if (!result[e.subject]) result[e.subject] = {};
    result[e.subject].ese = parseFloat(e.marks);
  });

  // compute weighted total per subject and overall
  let totalSum = 0, count = 0;
  for (let subj in result) {
    const mse = result[subj].mse || 0;  // now definitely a Number
    const ese = result[subj].ese || 0;  // definitely a Number
    const weighted = mse * 0.3 + ese * 0.7;
    // toFixed returns a string, so wrap in parseFloat
    result[subj].total = parseFloat(weighted.toFixed(2));
    totalSum += weighted;
    count++;
  }

  return {
    subjects: result,
    overall: count ? parseFloat((totalSum / count).toFixed(2)) : 0
  };
}

// Create a student with marks
app.post('/api/students', async (req, res) => {
  try {
    const { roll_no, name, mse, ese } = req.body;
    // mse and ese are arrays of { subject, marks }
    const [studentResult] = await db.query(
      'INSERT INTO Students (roll_no, name) VALUES (?, ?)',
      [roll_no, name]
    );
    const studentId = studentResult.insertId;

    for (let m of mse) {
      await db.query(
        'INSERT INTO MSE (student_id, subject, marks) VALUES (?, ?, ?)',
        [studentId, m.subject, m.marks]
      );
    }
    for (let e of ese) {
      await db.query(
        'INSERT INTO ESE (student_id, subject, marks) VALUES (?, ?, ?)',
        [studentId, e.subject, e.marks]
      );
    }

    const result = await getResult(studentId);
    res.status(201).json({ id: studentId, roll_no, name, result });

  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Database error' });
  }
});

// Get all students with computed results
app.get('/api/students', async (req, res) => {
  try {
    const [students] = await db.query('SELECT * FROM Students');
    const data = [];
    for (let s of students) {
      const r = await getResult(s.id);
      data.push({ id: s.id, roll_no: s.roll_no, name: s.name, result: r });
    }
    res.json(data);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Database error' });
  }
});

// Delete a student (and cascade delete marks)
app.delete('/api/students/:id', async (req, res) => {
  try {
    const { id } = req.params;
    await db.query('DELETE FROM Students WHERE id = ?', [id]);
    res.json({ success: true });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Database error' });
  }
});

// Start server
const PORT = process.env.PORT || 4000;
app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});
