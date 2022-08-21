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
        


# define the lowpass filter

def lowpass(Time,data ,g):
    dt=Time[2]-Time[1]
    filtered_val=[]

    y_prev=0
    y_new=0

    for i in range(0,len(data)):
        c=g*(data[i]-y_prev)*dt
        y_new+=c
        y_prev=y_new
        filtered_val.append(y_new)
    
    return filtered_val
        

low_pass_data = lowpass(Time ,Original,400)


# plot data

plt.figure(figsize=(10, 10), dpi=80)
plt.subplot(211)
plt.plot(Original)
plt.title("input(Unfiltered)")
plt.subplot(212)
plt.plot(low_pass_data)
plt.title("low pass filter | g =400")


plt.subplots_adjust(left=0.1,
                    bottom=0.1, 
                    right=0.9, 
                    top=0.9, 
                    wspace=0.4, 
                    hspace=0.4)

plt.show()