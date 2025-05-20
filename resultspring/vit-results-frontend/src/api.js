import axios from 'axios';

const API = axios.create({
  baseURL: 'http://localhost:4000/api',
  headers: { 'Content-Type': 'application/json' }
});

export const fetchStudents = () => API.get('/students');
// export const createStudent = ({ roll, name, mse, ese }) =>
//   API.post('/students', {
//     rollNo: roll,
//     name:   name,
//     mse:    mse,
//     ese:    ese
//   });
export const createStudent = data => API.post('/students', data);
export const deleteStudent = id => API.delete(`/students/${id}`);
