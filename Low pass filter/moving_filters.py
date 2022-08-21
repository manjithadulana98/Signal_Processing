import csv
import matplotlib.pyplot as plt


# read CSV file as data and time
Original=[]   
Time= []

with open('sensor_data.csv') as File:
    Read = csv.reader(File)
    for line in Read:
        Original.append(float(line[1]))
        Time.append(float(line[0]))
        

# define the moving average filter

def moving_average(arr , window_size):
    i = 0
    # Initialize an empty list to store moving averages
    moving_averages = []
    
    # Loop through the array to consider
    # every window of size 3
    while i < len(arr) - window_size + 1:
        
        # Store elements from i to i+window_size
        # in list to get the current window
        window = arr[i : i + window_size]
    
        # Calculate the average of current window
        window_average = round(sum(window) / window_size, 2)
        
        # Store the average of current
        # window in moving average list
        moving_averages.append(window_average)
        
        # Shift window to right by one position
        i += 1
    return moving_averages





        
filtered = moving_average(Original , 50)


# plot data

plt.figure(figsize=(10, 10), dpi=80)
plt.subplot(211)
plt.plot(Original)
plt.title("input(Unfiltered)")
plt.subplot(212)
plt.plot(filtered)
plt.title("moving average filter | windows size = 50")



plt.subplots_adjust(left=0.1,
                    bottom=0.1, 
                    right=0.9, 
                    top=0.9, 
                    wspace=0.4, 
                    hspace=0.4)

plt.show()