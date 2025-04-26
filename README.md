# Cash_Flow_Minimizer_System

A Java application to minimize the number of transactions between multiple banks using different payment modes.

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

4. **Follow the prompts** on the console to input the number of banks, payment modes, and transactions.

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

---

## 🤝 Contributing

Feel free to fork the repository and submit a pull request for any improvements!  
Issues and suggestions are also welcome.

---

## 👨‍💻 Author

- **Your Name** (Replace with your actual name or GitHub username)
- GitHub: [your-username](https://github.com/your-username)

---

## 📢 Acknowledgements

Inspired by classical algorithms for minimizing cash flows among multiple parties using greedy techniques.

---
