#PANTELIDIS IPPOKRATIS p3210150
#MITSAKIS NIKOS p3210122
#PANAGIOTIS MOSCHOS p3210127	

	.text
	.globl main
#main
main:									
	li $t0,0 							#result
	li $t2,0							#values in stack
  								
    li $v0,4							#print mhnyma 1
    la $a0, input_prompt
    syscall
	
read_token: 
    li $v0, 12							#read ch(char) 
    syscall
	
	sh $v0,ch							#load ch in $t1
	lb $t1,ch
	
	beq $t1,' ',read_token				#if(ch == ' ') read next ch
	li $t5,0							#number = 0
digit:									#while ((ch >='0') && (ch<='9'))
	blt $t1,48,checkop					#if ch < ascii code of 0 go to label checkop
	bgt $t1,57,checkop					#if ch > ascii code of 9 go to label checkop
	addi $t1, $t1, -48 					#int value of ch
	mul $t5,$t5,10						#10*number
	add $t5,$t5,$t1						#number = 10*number + (ch-48)
	
	li $v0,12							#read next ch
    syscall
	
	sh $v0,ch							#load ch in $t1
	lb $t1,ch
	
	j digit								#jump to the start of the while loop
	
checkop:								#ch not ' ', not value,check if operator,true goto to operator
	beq $t1,'+',operator
	beq $t1,'-',operator
	beq $t1,'*',operator
	beq $t1,'/',operator
	j not_equal							#if not +,-,*,/ goto label notequal
	
operator:
	jal pop								#call pop function
    move $t6, $v0						#x2= pop()
    jal pop								#call pop function
    move $t7, $v0						#x1= pop()
	move $a0, $t6 # x2					#calc arguments
    move $a1, $t1 # ch
    move $a2, $t7 # x1
    jal calc							#call calc function
	move $a0,$v0						#return value of calc->argument for push
	jal push							#call push function
	j read_token						#go to label read_token

not_equal: 
	beq $t1,'=',final					#if ch == '=' go to final
	move $a0,$t5						#number->argument for push
	jal push							#call push function
	j read_token						#go to label read_token
	
final:
	bne $t2,1,invalid					#if stack has one value print else goto label invalid
	li $v0, 4							#print mynhma 5
    la $a0, output_msg
    syscall
	
    lw $a0,0($sp)						#load and print value at the top of the stack
    li $v0,1							
    syscall
	
	j exit 								#goto exit

#subprogramms
pop:									#pop function
    beq $t2,0,invalid					#check if stack is empty
	sub $t2,$t2,1						#decrease values in stack by 1
    lw $v0,0($sp)						#load value at the top of the stack
	add $sp,$sp,4						#increase stack pointer by 4
    jr $ra								#return where called

calc:									#calc function
    beq $a1, '+', plus					#check value of ch and perform calculation
    beq $a1, '-', minus
    beq $a1, '*', times
    beq $a1, '/', divide
    j end

plus:									
    add $v0, $a2, $a0					#x1 + x2
    j end

minus:
    sub $v0, $a2, $a0					#x1 - x2
    j end

times:
    mul $v0, $a2, $a0					# x1 * x2
    j end

divide:
    beq $a0, 0, divide_by_zero			#if x2 != 0 else goto label divide_by_zero
    div $v0, $a2, $a0					#x1 / x2
    j end

divide_by_zero:
    li $v0, 4							#print mynhma 4
    la $a0, error_div
    syscall
	
    j exit								#goto exit

end:
    jr $ra								#return where called
	
push:									#push function
    beq $t2,20,full						#check if stack is full
	add $t2,$t2,1						#increase values in stack by 1
    add $sp, $sp,-4						#decrease stack pointer by 4
	sw $a0,0($sp)						#store value at the top of the stack
    jr $ra								#return where called

invalid:
    li $v0, 4							#print mynhma 2
    la $a0, error_msg
    syscall 
	
	j exit								#goto exit
	
full:
    li $v0, 4							#print mynhma 3
    la $a0, full_stack
    syscall
	
	j exit								#goto exit
   
exit: 
	li $v0,10							#exit program
	syscall
	
	.data
ch:			  .space 1							#character(1 byte)
input_prompt: .asciiz "Postfix (input):"		#mhnyma 1
error_msg: .asciiz "\nInvalid Postfix"			#mhnyma 2
full_stack: .asciiz "\nThe stack is full"		#mhnyma 3
error_div: .asciiz "\nDivide by zero"			#mhnyma 4
output_msg: .asciiz "\nPostfix Evaluation: "	#mhnyma 5
