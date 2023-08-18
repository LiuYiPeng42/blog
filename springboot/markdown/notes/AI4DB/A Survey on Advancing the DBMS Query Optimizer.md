

## There parts in a cost-based optimizer

### cardinality estimation (CE)

$$
\left.\begin{matrix}\qquad\qquad\qquad\qquad\quad statistics\ of\ data\\ \left.\begin{matrix} data\ distribution \\ column\ correlation \\ join\ relationship\end{matrix}\right\}\ \ assumptions\end{matrix}\right \}\Rightarrow number\ of\ tuples
$$

### cost model (CM)

​		Cost-based optimizers use a cost model to generate the estimate of cost for a (sub)query. The cost of (sub)plan is the sum of costs of all operators in it.
$$
\left.
\begin{matrix}
current\ state\ of\ database 
\\  estimated\ cardinalities 
\end{matrix}
\right\}
\Rightarrow 
cost\ of\ executing\ a\ (sub)plan
$$

### plan enumeration (PE)

​		PE is an algorithm to explore the space of semantically equivalent join orders and find the optimal orders with minimal cost. There are two principal approaches to find an optimal join order
$$
optimal\ join\ order
\left\{
\begin{matrix}
bottom-up\ join\ enumeration:\ dynamic\ programming
\\top-down\ join\ enumeration\ through\ memorization
\\ randomized\ algorithms 
\end{matrix}
\right.
$$
<img src="A Survey on Advancing the DBMS Query Optimizer/1.png" />

## reasons why CE, CM and PE are not accurate

​		cost-based query optimizers still make mistakes on “difficulty ” queries due to the ==error in CE==, the difficulty in building an ==accurate CM==, and the pain in finding the ==optimal join orders== (PE) for complex queries. 

### Cardinality Estimation

1. Histograms are smaller than the original data. it cannot represent the true distribution entirely 

2. Wrong assumptions  are proposed.

3. Correlations possibly exist in columns from different tables. However, there is no efficient way to get synopses between two or more tables.

4. There is no general method to estimate how many tuples satisfying user-defined function.

### Cost Model

1. Errors in cardinality estimation

2. Complex environment where  cost model is deployed could make the complexity of cost model is increasing dramatically, which may lead to a suboptimal execution plan

### Plan Enumeration

1. The errors in cardinality estimation and cost model
2. The rules used to prune the search space
3. When dealing with the queries with large number of tables, optimizers have to sacrifice optimality to keep optimization time reasonable

## Cardinality Estimation

<img src="A Survey on Advancing the DBMS Query Optimizer\2.png" />





