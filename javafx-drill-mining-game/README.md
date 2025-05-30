# 🛠️ JavaFX Drill Mining Game

This project is a 2D mining game built using JavaFX, designed for BBM104 Project Assignment 3. The player controls a drill machine to dig through the earth, collect valuable resources, and avoid dangerous blocks.

## 🎯 Project Objectives
- Simulate a side-view mining experience.
- Integrate UI elements to reflect machine stats (fuel, bank, storage).
- Use JavaFX components for scene rendering and user interaction.

## 🧰 Technologies Used
- Java
- JavaFX
- Object-Oriented Programming (OOP)

## 🧱 Game Elements

### ✅ Visual Requirements (from checklist)
- Sky and earth separation
- Grass on the surface
- Boulders on borders (except top)
- Lava (causes game over)
- Gravity for drill machine
- Soil (more frequent than other blocks)
- Drill controlled by **arrow keys**
- Cannot drill **upwards**
- Fuel depletes with time and movement
- Fuel/Money/Storage shown on screen

### 💎 Valuables
- At least **3 types** of minerals/gems with:
  - Different **values**
  - Different **weights**

### 🔥 Game Over Scenarios
- Touching lava block (🔴 red screen)
- Running out of fuel (🟢 green screen + score)

## 👥 Class Breakdown

- `Main.java`: Launches the JavaFX application.
- `Drill.java`: Handles drill machine state, movement, and interactions.
- `ImageViews.java`: Controls visual rendering for game blocks and characters.
- `Texts.java`: Renders dynamic text on the screen (e.g., stats).
- `Minerals.java`: Defines mineral types with value and weight.
- `Images.java`: Loads and stores graphical resources.

## 🎮 Controls
- Arrow keys for movement
- Drill cannot move upwards
- Gravity pulls the drill down

## 📺 [Demo Video](https://www.youtube.com/watch?v=IEmpq7QzNCo)

---

🧠 This is a great example of combining game logic, JavaFX GUI, and OOP principles in an academic project.
