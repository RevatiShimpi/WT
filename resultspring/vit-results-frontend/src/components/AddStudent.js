// src/components/AddStudent.js
import React, { useState } from 'react';
import { Form, Button, Alert, Card } from 'react-bootstrap';
import { createStudent } from '../api';
import { useNavigate } from 'react-router-dom';

const SUBJECTS = ['Mathematics','DS','OS','DB'];

export default function AddStudent() {
  const nav = useNavigate();
  const [roll, setRoll] = useState('');
  const [name, setName] = useState('');
  const [mse, setMse]   = useState(Array(4).fill(''));
  const [ese, setEse]   = useState(Array(4).fill(''));
  const [error, setError] = useState('');

  const onSubmit = async e => {
    e.preventDefault();
    // basic validation
    if (!roll || !name || mse.some(m=>!m) || ese.some(e=>!e)) {
      setError('All fields are required');
      return;
    }
    const mseArr = SUBJECTS.map((subj, i) => ({ subject: subj, marks: parseFloat(mse[i]) }));
    const eseArr = SUBJECTS.map((subj, i) => ({ subject: subj, marks: parseFloat(ese[i]) }));

    await createStudent({
      rollNo: roll,   // ← must match your DTO’s getRollNo()
      name:   name,   // ← matches getName()
      mse:    mseArr, // array of { subject, marks }
      ese:    eseArr  // array of { subject, marks }
    });
    nav('/');
  };

  const onChangeArr = (setter, idx, value) => {
    setter(arr => arr.map((v,i)=> i===idx ? value : v));
  };

  return (
    <Card>
      <Card.Body>
        <Card.Title>Add New Student</Card.Title>
        {error && <Alert variant="danger">{error}</Alert>}
        <Form onSubmit={onSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Roll Number</Form.Label>
            <Form.Control value={roll} onChange={e=>setRoll(e.target.value)} />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Name</Form.Label>
            <Form.Control value={name} onChange={e=>setName(e.target.value)} />
          </Form.Group>

          {SUBJECTS.map((subj,i) => (
            <div key={i} className="row">
              <div className="col-md-4 mb-3">
                <Form.Label>{subj} MSE (30%)</Form.Label>
                <Form.Control
                  type="number" min="0" max="30"
                  value={mse[i]}
                  onChange={e => onChangeArr(setMse, i, e.target.value)}
                />
              </div>
              <div className="col-md-4 mb-3">
                <Form.Label>{subj} ESE (70%)</Form.Label>
                <Form.Control
                  type="number" min="0" max="70"
                  value={ese[i]}
                  onChange={e => onChangeArr(setEse, i, e.target.value)}
                />
              </div>
            </div>
          ))}

          <Button type="submit">Save</Button>
        </Form>
      </Card.Body>
    </Card>
  );
}
