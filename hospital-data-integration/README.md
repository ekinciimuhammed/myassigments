# 🏥 Hospital Data Integration System

This project demonstrates how to manage and integrate patient and doctor data using Python and SQLite.

## 🎯 Project Goals
- Store and manage patient billing records and doctor information in a relational database.
- Ensure data integrity using conditional insert/update queries.
- Lay the foundation for a basic hospital information system.

## 🧰 Technologies Used
- Python
- SQLite (via `sqlite3`)
- Pandas
- Matplotlib (possibly for visualizations)

## 📁 Dataset
- `Patient_billing.csv`: Contains patient IDs, names, emails, invoice details, and billing amounts.
- `Doctors.csv`: Includes doctor IDs, names, specializations, and contact info.

## 🔧 What It Does
- Creates `Patients` and `Doctors` tables if they don't exist.
- Reads CSV files with `pandas`, transforms them into list format.
- Checks if a record already exists using `PatientID` or `DoctorID`.
  - If not, inserts a new row.
  - If yes, updates the existing row.

## 📌 Notes
This project is ideal for beginners interested in combining **relational database design** and **data wrangling** in a healthcare context.

---

🩺 A practical example of how data is stored, queried, and updated in a real-world hospital-like setup.
