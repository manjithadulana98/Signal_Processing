y1 = linspace (0,l1,20);
y21 = linspace (l1,l21,20);
y22 = linspace (l1,l22,20);
v1 = x(1)*exp(-y1)+x(2)*exp(y1);
v21 = x(3)*exp(-y21)+x(4)*exp(y21);
v22 = x(5)*exp(-y22)+x(6)*exp(y22);
plot(y1,v1,'r-',y21,v21,'b-',y22,v22,'g--');
hold on;
xlabel('X (dimensionless)');
ylabel('V (volts)');
title('Steady-state voltage - E5');
legend('1','21','22');