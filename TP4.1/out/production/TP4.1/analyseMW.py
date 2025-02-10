import matplotlib.pyplot as plt
import numpy as np
from collections import defaultdict

data_file = "MW_data.txt"
workers = []
times_dict = defaultdict(list)
errors = []

with open(data_file, "r") as file:
    for line in file:
        parts = line.strip().split(";")
        if len(parts) == 4:
            error = float(parts[0])
            total_throws = int(parts[1])
            num_workers = int(parts[2])
            time_n_workers = float(parts[3])
            
            workers.append(num_workers)
            times_dict[num_workers].append(time_n_workers)
            errors.append(error)

# Calculer la moyenne des temps pour chaque nombre de workers
workers = sorted(set(workers))
times = np.array([np.mean(times_dict[w]) for w in workers])

if len(times) > 0 and times[0] > 0:
    speedups = times[0] / times
else:
    speedups = np.ones_like(times)

plt.figure(figsize=(10, 5))
plt.plot(workers, speedups, marker='o', linestyle='-', color='b', label='Speedup')
plt.plot(workers, workers, linestyle='--', color='g', label='Scalabilité idéale (Sp = p)')
plt.xlabel("Nombre de workers")
plt.ylabel("Accélération (Speedup)")
plt.title("Scalabilité forte de l'approximation de Pi (Monte Carlo)")
plt.legend()
plt.grid(True, which='both', linestyle='--', linewidth=0.5)

plt.xlim(0, max(workers) * 1.1)
plt.ylim(0, max(workers) * 1.1)
plt.gca().set_aspect('equal', adjustable='box')

plt.show()
