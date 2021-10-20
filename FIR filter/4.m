A(2,:) = [0 0 -exp(-l21) exp(l21) 0 0];
A(3,:) = [0 0 0 0 -exp(-l22) exp(l22)];
b(1)=0; b(2)=rl21*iapp;
b(3) = rl22*iapp;