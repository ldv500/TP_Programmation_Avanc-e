import matplotlib.pyplot as plt
import numpy as np






# Lire les données du fichier
data_file = "XP_piJava.txt"
workers = []
times = []
speedups = []
errors = []

with open(data_file, "r") as file:
    for line in file:
        parts = line.strip().split(";")
        if len(parts) == 6:
            pi_approx = float(parts[0].replace(",", "."))
            error = float(parts[1].replace(",", "."))
            total_throws = int(parts[2])
            in_circle = int(parts[3])
            num_workers = int(parts[4])
            time_n_workers = int(parts[5])
            
            workers.append(num_workers)
            times.append(time_n_workers)
            errors.append(error)

# Convertir en numpy array pour faciliter le traitement
workers = np.array(workers)
times = np.array(times) 
errors = np.array(errors)

# Calculer l'accélération (Speedup) par rapport au temps avec 1 worker
if len(times) > 0 and times[0] > 0:
    speedups = times[0] / times
else:
    speedups = np.ones_like(times)

# Tracer le graphique de la scalabilité dans un repère orthonormé non responsive
plt.figure(figsize=(10, 5))
plt.plot(workers, speedups, marker='o', linestyle='-', color='b', label='Speedup')
plt.plot(workers, workers, linestyle='--', color='g', label='Scalabilité idéale (Sp = p)')
plt.xlabel("Nombre de workers")
plt.ylabel("Accélération (Speedup)")
plt.title("Scalabilité de l'approximation de Pi (Monte Carlo)")
plt.legend()
plt.grid(True, which='both', linestyle='--', linewidth=0.5)

# Fixer les limites des axes pour garantir un repère orthonormé non responsive
plt.xlim(0, max(workers) * 1.1)
plt.ylim(0, max(workers) * 1.1)

# Assurer que les axes sont égaux (orthonormés) et ne sont pas responsives
plt.gca().set_aspect('equal', adjustable='box')

plt.show()