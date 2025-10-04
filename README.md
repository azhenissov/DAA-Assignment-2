# Kadane's Algorithm Project

This project implements **Kadane's Algorithm** for finding the **maximum subarray sum** with **position tracking** and **performance metrics**.

It includes:
- A **Demo** (`Demo.java`) showcasing typical test cases
- A **Benchmark Runner** (`BenchmarkRunner.java`) for performance testing on configurable input sizes with CSV export
- Full **metrics tracking** (comparisons, array accesses, memory allocations, execution time)

---

## Usage

### 1. Build the project

```bash
mvn clean package
```

### 2. Run the Demo

Showcases Kadane's Algorithm on several fixed arrays.

```bash
java -jar target/kadane-algorithm-1.0-SNAPSHOT.jar
```

Or with Maven:

```bash
mvn exec:java -Dexec.mainClass="com.example.kadane.Demo"
```

### 3. Run the Benchmark

Benchmark Kadane's Algorithm on large random arrays with configurable input sizes and runs.
Results can be exported to CSV.

```bash
mvn exec:java -Dexec.mainClass="com.example.kadane.BenchmarkRunner" \
    -Dexec.args="--size=10000 --runs=5 --out=bench.csv"
```

**Arguments:**
- `--size=N` → size of the input array (default: 1000)
- `--runs=N` → number of benchmark runs (default: 3)
- `--out=FILE` → optional CSV file to save metrics

---

## Example Output (Demo)

```yaml
 Kadane's Algorithm - Maximum Subarray Sum Demo
===============================================

Test 1: Classic example
Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Result: MaxSum=6, Start=3, End=6
Subarray: [4, -1, 2, 1]
Execution time: 0.045 ms
Array accesses: 9
Comparisons: 16
Memory allocations: 1
```

---

##  Complexity Analysis

Kadane's Algorithm runs in linear time:

**Time Complexity:**
- Best case: O(n)
- Worst case: O(n)
- Average case: O(n)

(where n = number of array elements)

**Space Complexity:**
- Uses O(1) extra memory (only stores running sums and indices).
- Metrics collection adds negligible overhead.

**Optimizations Implemented:**
- Cached current element to reduce redundant array accesses
- Early exit for all-negative arrays (returns max element directly)

---

## Features

- Clean, readable Java implementation
- Comprehensive test suite with edge cases:
  - Empty arrays
  - Single elements
  - All-negative arrays
  - Mixed positive/negative
- CLI interface for testing and benchmarking
- Performance metrics tracking
- CSV export for benchmarks
- Error handling for invalid inputs
