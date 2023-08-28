L2:
sll $8, $9, 3
beq $t1, $t0, L1
sll $8, $9, 4
beq $9, $8, L2
L1: add $t0, $s1, $s2