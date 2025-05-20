-- mysql -u root -p < db.sql


DROP DATABASE IF EXISTS vit_results;
CREATE DATABASE vit_results;
USE vit_results;

-- Students table
CREATE TABLE Students (
  id INT AUTO_INCREMENT PRIMARY KEY,
  roll_no VARCHAR(20) NOT NULL UNIQUE,
  name VARCHAR(100) NOT NULL
);

-- MSE Marks (30%)
CREATE TABLE MSE (
  id INT AUTO_INCREMENT PRIMARY KEY,
  student_id INT NOT NULL,
  subject VARCHAR(50) NOT NULL,
  marks DECIMAL(5,2) NOT NULL,
  FOREIGN KEY (student_id) REFERENCES Students(id) ON DELETE CASCADE
);

-- ESE Marks (70%)
CREATE TABLE ESE (
  id INT AUTO_INCREMENT PRIMARY KEY,
  student_id INT NOT NULL,
  subject VARCHAR(50) NOT NULL,
  marks DECIMAL(5,2) NOT NULL,
  FOREIGN KEY (student_id) REFERENCES Students(id) ON DELETE CASCADE
);
