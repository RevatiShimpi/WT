// src/components/StudentList.js
import React, { useEffect, useState } from 'react';
import { Table, Button, Alert, Spinner } from 'react-bootstrap';
import { fetchStudents, deleteStudent } from '../api';

// Keep this in sync with AddStudent.js!
const SUBJECTS = ['Mathematics', 'DS', 'OS', 'DB'];

export default function StudentList() {
  const [students, setStudents] = useState([]);
  const [loading,  setLoading]   = useState(true);
  const [error,    setError]     = useState(null);

  const load = async () => {
    try {
      setLoading(true);
      const res = await fetchStudents();
      setStudents(res.data);
    } catch (err) {
      setError('Unable to load students');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async id => {
    if (!window.confirm('Delete this student?')) return;
    await deleteStudent(id);
    load();
  };

  useEffect(() => { load(); }, []);

  if (loading) return <Spinner animation="border" />;
  if (error)   return <Alert variant="danger">{error}</Alert>;

  if (students.length === 0) {
    return <Alert variant="info">No students yet. <a href="/add">Add one.</a></Alert>;
  }

  return (
    <Table responsive bordered hover>
      <thead>
        {/* Row 1: Roll, Name, then one header per subject (spanning 3 cols) */}
        <tr>
          <th rowSpan="2">Roll No</th>
          <th rowSpan="2">Name</th>
          {SUBJECTS.map(subj => (
            <th key={subj} colSpan="3" className="text-center">{subj}</th>
          ))}
          <th rowSpan="2">Overall</th>
          <th rowSpan="2">Action</th>
        </tr>

        {/* Row 2: for each subject, sub‐headers MSE / ESE / Total */}
        <tr>
          {SUBJECTS.map(subj => (
            <React.Fragment key={subj}>
              <th>MSE</th>
              <th>ESE</th>
              <th>Total</th>
            </React.Fragment>
          ))}
        </tr>
      </thead>

      <tbody>
        {students.map(st => (
          <tr key={st.id}>
            {/* updated field names */}
            <td>{st.rollNo}</td>
            <td>{st.name}</td>

            {/* render from the flat subjects array */}
            {SUBJECTS.map(subj => {
              const obj = st.subjects.find(s => s.subject === subj) || {};
              return (
                <React.Fragment key={subj}>
                  <td>{obj.mse   ?? '-'}</td>
                  <td>{obj.ese   ?? '-'}</td>
                  <td>{obj.total ?? '-'}</td>
                </React.Fragment>
              );
            })}

            <td>{st.overall}</td>
            <td>
              <Button variant="danger" size="sm" onClick={() => handleDelete(st.id)}>
                Delete
              </Button>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
}
