import React, { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [students, setStudents] = useState([]);
  const [results,  setResults]  = useState([]);
  const [stud, setStud] = useState({ name:'', rollNo:'' });
  const [marks, setMarks] = useState({
    studentId: '', mse1:0,mse2:0,mse3:0,mse4:0,
    ese1:0,ese2:0,ese3:0,ese4:0
  });

  useEffect(() => {
    fetchStudents();
    fetchResults();
  }, []);

  const fetchStudents = async () => {
    const res = await axios.get('http://localhost:8080/api/results');
    setResults(res.data);
    setStudents(res.data.map(r => ({ id: r.studentId, name: `${r.name} (${r.rollNo})` })));
  };

  const fetchResults = async () => {
    const res = await axios.get('http://localhost:8080/api/results');
    setResults(res.data);
  };

  const addStudent = async e => {
    e.preventDefault();
    await axios.post('http://localhost:8080/api/students', stud);
    setStud({ name:'', rollNo:'' });
    fetchStudents();
  };

  const addMarks = async e => {
    e.preventDefault();
    await axios.post('http://localhost:8080/api/marks', marks);
    setMarks({ ...marks, mse1:0,mse2:0,mse3:0,mse4:0,ese1:0,ese2:0,ese3:0,ese4:0 });
    fetchResults();
  };

  return (
    <div className="container my-5">
      <h2 className="mb-4 text-center">VIT Semester Results</h2>
      <div className="row">
        <div className="col-md-4">
          <div className="card p-3 mb-4">
            <h5>Add Student</h5>
            <form onSubmit={addStudent}>
              <input className="form-control my-2" placeholder="Name" required
                     value={stud.name} onChange={e=>setStud({...stud,name:e.target.value})}/>
              <input className="form-control my-2" placeholder="Roll No" required
                     value={stud.rollNo} onChange={e=>setStud({...stud,rollNo:e.target.value})}/>
              <button className="btn btn-primary w-100">Save</button>
            </form>
          </div>
          <div className="card p-3">
            <h5>Add Marks</h5>
            <form onSubmit={addMarks}>
              <select className="form-select my-2" required
                      value={marks.studentId}
                      onChange={e=>setMarks({...marks,studentId:e.target.value})}>
                <option value="">-- pick student --</option>
                {students.map(s=> <option key={s.id} value={s.id}>{s.name}</option>)}
              </select>
              {['mse','ese'].map(type=>(
                <div key={type} className="mb-3">
                  <strong>{type.toUpperCase()}</strong>
                  {[1,2,3,4].map(i=>(
                    <input key={i} type="number" min="0" max="100"
                           className="form-control my-1"
                           placeholder={`${type}${i}`}
                           value={marks[`${type}${i}`]}
                           onChange={e=>setMarks({...marks,[`${type}${i}`]:Number(e.target.value)})}
                           required/>
                  ))}
                </div>
              ))}
              <button className="btn btn-success w-100">Submit Marks</button>
            </form>
          </div>
        </div>
        <div className="col-md-8">
          <table className="table table-striped table-bordered">
            <thead className="table-dark">
              <tr>
                <th>Student</th>
                {[1,2,3,4].map(i=><th key={'h'+i}>Sub{i} (Final)</th>)}
                <th>Total</th>
              </tr>
            </thead>
            <tbody>
              {results.map(r=>(
                <tr key={r.studentId}>
                  <td>{r.name} ({r.rollNo})</td>
                  {[r.final1, r.final2, r.final3, r.final4].map((v,i)=><td key={i}>{v.toFixed(1)}</td>)}
                  <td>{r.total.toFixed(1)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default App;
