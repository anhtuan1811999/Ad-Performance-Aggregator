# Ad Performance Aggregator

A CLI-based Java application for processing large CSV datasets containing advertising campaign performance data.

The application aggregates campaign statistics and generates analytics reports for:

- Top 10 campaigns with the highest CTR
- Top 10 campaigns with the lowest CPA

The solution is designed to handle large CSV files (~1GB) efficiently using streaming processing and memory-optimized aggregation.

---

# Features

- Stream-based CSV processing using `BufferedReader`
- Memory-efficient aggregation using `HashMap`
- Generates:
    - `top10_ctr.csv`
    - `top10_cpa.csv`
- Handles malformed rows safely
- CLI support with input/output arguments
- Maven-based project structure

---

# Technologies Used

- Java 21
- Maven
- Java Stream API
- BufferedReader / BufferedWriter
- HashMap aggregation

---

# Usage

## 1. Clone the repository

```bash
git clone https://github.com/anhtuan1811999/Ad-Performance-Aggregator.git
```
## 2. Build the project

```bash
mvn clean install -DskipTests
```
## 3. Run the application
**--input** is the path to the ad_data.csv file and
**--output** is where the results are saved after calculation
```bash
java -jar target/Ad-Performance-Aggregator-1.0-SNAPSHOT.jar --input C:\Users\Admin\Downloads\ad_data.csv --output C:\Users\Admin\Desktop\result_test
```