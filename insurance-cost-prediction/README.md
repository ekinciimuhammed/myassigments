# 🧾 Insurance Cost Prediction

This project applies data visualization and machine learning to predict medical insurance costs using demographic and lifestyle information.

## 🎯 Project Goals
- Analyze how different variables (like smoking status, BMI, age, etc.) affect insurance charges.
- Build regression models to predict the cost of insurance.
- Identify the most influential features.

## 🧰 Technologies Used
- Python
- Pandas, NumPy
- Seaborn, Matplotlib
- Scikit-learn (Linear Regression, KNN)
- Feature encoding & scaling

## 📁 Dataset
The dataset `insurance.csv` contains:
- Demographics: `age`, `sex`, `region`
- Health metrics: `bmi`, `children`, `smoker`
- Target variable: `charges`

## 📈 Key Steps
- Performed exploratory data analysis (EDA) with histograms, scatter plots, and box plots.
- Handled categorical variables using one-hot encoding.
- Evaluated correlation with the target (`charges`).
- Built models:
  - **Linear Regression**
  - **K-Nearest Neighbors (KNN)**
- Assessed model performance using `RMSE` and `R² Score`.

## 🔍 Insights
- Smoking has the highest positive correlation with insurance charges.
- Gender and region do not significantly affect the prediction target.

---

💡 This notebook is a practical example of how real-world health and demographic data can be used for predictive analytics.
