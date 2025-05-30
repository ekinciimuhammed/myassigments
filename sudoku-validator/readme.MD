# 🔢 Sudoku Validator & Helper

This Python script provides helper functions to manage and validate Sudoku puzzles. It is designed for verifying correctness and building substructures like columns and 3x3 boxes from a given flat 9x9 Sudoku grid.

## 🎯 Objective
- Represent a Sudoku board as a flat list.
- Extract columns and subgrids programmatically.
- Prepare a structure to verify Sudoku rules (no repetition in rows, columns, and boxes).

## 🧰 Technologies Used
- Python (standard library)
- `sys` module for command-line interaction

## 🧠 How It Works

The core functions:
- `column_maker(table)`: Converts the 1D Sudoku grid into 9 columns.
- `square_maker(table)`: Extracts 3x3 subgrids (regions) from the same flat list.

Each function returns a list of lists representing the respective sections.

## ✅ Sudoku Rules Recap
- Each row must contain numbers 1–9 with no repetition.
- Each column must contain numbers 1–9 with no repetition.
- Each 3x3 subgrid must contain numbers 1–9 with no repetition.

## 🚀 Usage
This file is meant to be a backend logic module. To validate a Sudoku grid, pass a flat list of 81 integers as input.

## 🔍 Example (Planned Usage)
```python
from sudoku import column_maker, square_maker

# Example grid (flattened 9x9 Sudoku board)
grid = [5, 3, 0, 0, 7, 0, 0, 0, 0, ...]  # Total 81 elements

columns = column_maker(grid)
boxes = square_maker(grid)
