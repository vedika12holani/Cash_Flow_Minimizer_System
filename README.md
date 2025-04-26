# Cash_Flow_Minimizer_System

A Java application to minimize the number of transactions between multiple banks using different payment modes.
When multiple banks owe money to each other, instead of each bank making direct payments to every other bank, this system calculates the minimum number of transactions needed to settle all debts.

Banks can only transact if they share a common payment mode (like various UPI, Wire Transfer, SWIFT, etc.).
The World Bank (first bank entered) can support all payment modes.


---

## ✨ Features

- Minimize cash flow between multiple banks.
- Support for different payment modes (e.g., Google Pay, Alipay, Paytm).
- Handles restrictions where not all banks support all payment modes.
- Finds optimal set of transactions with minimum number of transfers.
- Simple and interactive console-based input system.

---

## 🚀 How It Works

1. Each bank has its supported payment modes.
2. Banks owe amounts to each other based on transactions.
3. The system computes:
    - Net amount for each bank (receivable - payable).
    - The maximum debtor and maximum creditor with a common payment mode.
    - Reduces the transactions step-by-step until everyone is settled.

---

## 📚 Example Scenario

Suppose you have 5 banks:

| Bank | Supported Payment Modes |
|:----|:-------------------------|
| World Bank | Google Pay, Alipay, Paytm |
| Bank A | Google Pay, Alipay |
| Bank B | Alipay |
| Bank C | Google Pay, Paytm |
| Bank D | Paytm |

And a set of transactions like:

- Bank A owes Bank B Rs 500
- Bank C owes Bank D Rs 200
- Bank A owes Bank C Rs 300

The program minimizes these debts into the least number of payment transactions, considering supported payment modes.

---

## 🛠️ How to Run

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/Cash_Flow_Minimizer_System.git
    ```

2. **Compile the Java files**:
    ```bash
    javac CashFlowMinimizer.java
    ```

3. **Run the program**:
    ```bash
    java CashFlowMinimizer
    ```

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

---



