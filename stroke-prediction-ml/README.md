# 🧠 Stroke Prediction using Logistic Regression

This machine learning project predicts the likelihood of a stroke using clinical and demographic data from patients.

## 🎯 Project Goals
- Preprocess medical data (handling missing values, outliers).
- Train a binary classification model to predict stroke occurrence.
- Evaluate model performance with appropriate metrics.

## 🧰 Technologies Used
- Python
- Pandas, NumPy
- Scikit-learn (Logistic Regression, Evaluation Metrics)
- Seaborn & Matplotlib (Visualization)

## 📁 Dataset
- `stroke.csv`: Includes features like `age`, `bmi`, `avg_glucose_level`, `smoking_status`, `hypertension`, and the target variable `stroke`.

## 🛠 Key Processes
- Filled missing values in `bmi` column using mean imputation.
- Applied outlier treatment using **IQR-based capping**.
- Scaled numerical values with `MinMaxScaler`.
- Split dataset into training and testing sets.
- Trained a **Logistic Regression** model.
- Evaluated performance using:
  - Accuracy
  - Confusion Matrix
  - Classification Report

## 📊 Findings
- Proper preprocessing significantly improved model performance.
- Balanced treatment of outliers and scaling made features comparable.
- Logistic Regression provided an interpretable and baseline classifier for medical prediction.

---

⚕️ This is a clean and simple implementation for beginners looking to apply classification algorithms to healthcare problems.
