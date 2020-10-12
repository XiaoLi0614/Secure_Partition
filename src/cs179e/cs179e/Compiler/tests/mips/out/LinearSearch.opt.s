.data
empty_LS:

.text
	jal Main
	li $v0 10
	syscall
Main:
	sw $fp -8($sp)
	move $fp $sp
	subu $sp $sp 8
	sw $ra -4($fp)
	li $a0 8
	jal _heapAlloc
	move $t0 $v0
	bnez $t0 null1
	la $a0 _str0
	j _error
null1:
	move $a0 $t0
	li $a1 10
	jal LS.Start
	move $t0 $v0
	move $a0 $t0
	jal _print
	lw $ra -4($fp)
	lw $fp -8($fp)
	addu $sp $sp 8
	jr $ra
LS.Start:
	sw $fp -8($sp)
	move $fp $sp
	subu $sp $sp 12
	sw $ra -4($fp)
	sw $s0 0($sp)
	move $s0 $a0
	move $t0 $a1
	move $a0 $s0
	move $a1 $t0
	jal LS.Init
	move $a0 $s0
	jal LS.Print
	li $a0 9999
	jal _print
	move $a0 $s0
	li $a1 8
	jal LS.Search
	move $t0 $v0
	move $a0 $t0
	jal _print
	move $a0 $s0
	li $a1 12
	jal LS.Search
	move $t0 $v0
	move $a0 $t0
	jal _print
	move $a0 $s0
	li $a1 17
	jal LS.Search
	move $t0 $v0
	move $a0 $t0
	jal _print
	move $a0 $s0
	li $a1 50
	jal LS.Search
	move $t0 $v0
	move $a0 $t0
	jal _print
	li $v0 55
	lw $s0 0($sp)
	lw $ra -4($fp)
	lw $fp -8($fp)
	addu $sp $sp 12
	jr $ra
LS.Print:
	sw $fp -8($sp)
	move $fp $sp
	subu $sp $sp 8
	sw $ra -4($fp)
	move $t0 $a0
	li $t1 1
while1_top:
	lw $t2 4($t0)
	slt $t2 $t1 $t2
	beqz $t2 while1_end
	lw $t2 0($t0)
	bnez $t2 null2
	la $a0 _str0
	j _error
null2:
	lw $t3 0($t2)
	slt $t3 $t1 $t3
	bnez $t3 bounds1
	la $a0 _str0
	j _error
bounds1:
	li $t9 4
	mul $t3 $t1 $t9
	addu $t3 $t3 $t2
	lw $t3 4($t3)
	move $a0 $t3
	jal _print
	addiu $t1 $t1 1
	j while1_top
while1_end:
	li $v0 0
	lw $ra -4($fp)
	lw $fp -8($fp)
	addu $sp $sp 8
	jr $ra
LS.Search:
	sw $fp -8($sp)
	move $fp $sp
	subu $sp $sp 8
	sw $ra -4($fp)
	move $t0 $a0
	move $t1 $a1
	li $t2 1
	li $t3 0
while2_top:
	lw $t4 4($t0)
	slt $t4 $t2 $t4
	beqz $t4 while2_end
	lw $t4 0($t0)
	bnez $t4 null3
	la $a0 _str0
	j _error
null3:
	lw $t5 0($t4)
	slt $t5 $t2 $t5
	bnez $t5 bounds2
	la $a0 _str0
	j _error
bounds2:
	li $t9 4
	mul $t5 $t2 $t9
	addu $t5 $t5 $t4
	lw $t5 4($t5)
	addiu $t4 $t1 1
	slt $t6 $t5 $t1
	beqz $t6 if1_else
	j if1_end
if1_else:
	slt $t4 $t5 $t4
	bnez $t4 if2_else
	j if2_end
if2_else:
	li $t3 1
	lw $t2 4($t0)
if2_end:
if1_end:
	addiu $t2 $t2 1
	j while2_top
while2_end:
	move $v0 $t3
	lw $ra -4($fp)
	lw $fp -8($fp)
	addu $sp $sp 8
	jr $ra
LS.Init:
	sw $fp -8($sp)
	move $fp $sp
	subu $sp $sp 12
	sw $ra -4($fp)
	sw $s0 0($sp)
	move $s0 $a0
	move $t0 $a1
	sw $t0 4($s0)
	move $a0 $t0
	jal AllocArray
	move $t0 $v0
	sw $t0 0($s0)
	li $t0 1
	lw $t1 4($s0)
	addiu $t1 $t1 1
while3_top:
	lw $t2 4($s0)
	slt $t2 $t0 $t2
	beqz $t2 while3_end
	li $t9 2
	mul $t2 $t9 $t0
	li $t9 3
	subu $t3 $t1 $t9
	lw $t4 0($s0)
	bnez $t4 null4
	la $a0 _str0
	j _error
null4:
	lw $t5 0($t4)
	slt $t5 $t0 $t5
	bnez $t5 bounds3
	la $a0 _str0
	j _error
bounds3:
	li $t9 4
	mul $t5 $t0 $t9
	addu $t5 $t5 $t4
	addu $t3 $t2 $t3
	sw $t3 4($t5)
	addiu $t0 $t0 1
	li $t9 1
	subu $t1 $t1 $t9
	j while3_top
while3_end:
	li $v0 0
	lw $s0 0($sp)
	lw $ra -4($fp)
	lw $fp -8($fp)
	addu $sp $sp 12
	jr $ra
AllocArray:
	sw $fp -8($sp)
	move $fp $sp
	subu $sp $sp 8
	sw $ra -4($fp)
	move $t0 $a0
	li $t9 4
	mul $t1 $t0 $t9
	addiu $t1 $t1 4
	move $a0 $t1
	jal _heapAlloc
	move $t1 $v0
	sw $t0 0($t1)
	move $v0 $t1
	lw $ra -4($fp)
	lw $fp -8($fp)
	addu $sp $sp 8
	jr $ra
_print:
	li $v0 1   # syscall: print integer
	syscall
	la $a0 _newline
	li $v0 4   # syscall: print string
	syscall
	jr $ra
_error:
	li $v0 4   # syscall: print string
	syscall
	li $v0 10  # syscall: exit
	syscall
_heapAlloc:
	li $v0 9   # syscall: sbrk
	syscall
	jr $ra
.data
.align 0
	_newline: .asciiz "\n"
	_str0: .asciiz "null pointer\n"

