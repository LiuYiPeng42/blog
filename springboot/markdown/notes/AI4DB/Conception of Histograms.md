## Aspect of Histograms

### Partition Rule

**Partition Class**: Indicates restrictions on partitioning, 

- Serial: (V，F) pairs in each bucket and (V，F) pairs in different bucket should be sort by sort parameter 
- End-biased: only a pair of (V, F) in each bucket

**Partition Constraint**: The mathematical constraint that uniquely identifies the histogram within its partition class. We can use partition constraint to divide values into different bucket  

- Equi-sum (Equi-depth): sum of frequency in a bucket should be equal

**Sort Parameter**: It is used to sort the (V, F) pairs (Serial Histograms), e.g. value, frequency, area

**Source Parameter**: This captures the property of the data distribution, It can be understood as the meaning of the y-axis of the histogram. e.g. Spread (S), frequency (F) and area (A).

**Spread**: $S_i=V_{i+1}-V_i$

**Areas**：$A_i=S_i \times F_i$

<img src=".\Conception of Histograms\1.png" alt="1" />

<center><p>equi-width histogram:spread(maxvalue-minvalue) of each bucket is the same</p></center>

### Construction Algorithm

​		Given a particular partition rule, this is the algorithm that constructs histograms that satisfy the rule.

### Value Approximation

​		This captures how attribute values are approximated within a bucket, which is independent of the partition rule of a histogram.

### Frequency Approximation

​		This captures how frequencies are approximated within a bucket.
