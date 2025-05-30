# 🚌 Java Bus Ticket Booking System

This project simulates a full-featured ticket booking system for different types of buses using object-oriented programming in Java.

## 🎯 Project Goals
- Simulate voyage creation for different bus types.
- Allow reservations, cancellations, and revenue tracking.
- Support price calculation with premium fees and refund deductions.

## 🧰 Technologies Used
- Java (OOP, File I/O)
- CLI-based interaction

## 🚦 Supported Bus Types
### ✅ Minibus
- 2 seats per row

### ✅ Standard
- 4 seats per row
- Includes refund deduction logic

### ✅ Premium
- 3 seats per row
- Extra premium fee added
- Premium refund deduction calculated separately

## 📦 Project Structure

### `BookingSystem.java`
- Main controller
- Reads commands, processes `INIT_VOYAGE`, `RESERVE_SEAT`, `CANCEL`, etc.
- Manages a list of all bus objects (`ArrayList<Bus>`)

### `Bus.java`
- Abstract base class containing shared properties like `price`, `revenue`, and `seatsOfAnyBus`.

### `Minibus.java`, `Standard.java`, `Premium.java`
- Extend `Bus` class
- Implement bus-specific logic:
  - Seat capacity rules
  - Revenue calculations
  - Refund policies

## 🚀 How to Run

```bash
javac *.java
java BookingSystem input.txt output.txt
