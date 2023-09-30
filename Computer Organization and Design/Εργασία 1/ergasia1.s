# NIKOS MITSAKIS - A.M. 3210122

.text
.globl main	   
    		
main: # Outer while Loop
 
      # Asking the user to provide the first number
         li $v0, 4
         la $a0, prompt1
         syscall
            
      # Reading the first number provided
         li $v0, 5   
         syscall   
         move $s1, $v0
	
Loop: # Inner while Loop
            	
	 # Asking the user to provide a symbol to decide the arithmetic operation
            li $v0, 4       
            la $a0, symbol
            syscall
          
        # Reading the symbol provided by the user to decide the arithmetic operation
            li $v0, 12   
            syscall
            move $s0, $v0             
        
        # Checking if a valid operator has been entered
                
        beq $s0, '+', SecondNum    # Branch to 'SecondNum' if [ + ]
        
        beq $s0, '-', SecondNum    # Branch to 'SecondNum' if [ - ]
         
        beq $s0, '*', SecondNum    # Branch to 'SecondNum' if [ * ]
        
        beq $s0, '/', SecondNum    # Branch to 'SecondNum' if [ / ]
        
        beq $s0, '%', SecondNum    # Branch to 'SecondNum' if [ % ]  
        
        beq $s0, '=', SecondNum    # Branch to 'SecondNum' if [ = ]
	
	# Invalid Operator
	j errorProcess1	
	
	SecondNum: 
	 
	 # Checking the case that the user entered the "=" operator with only one number
	 
	 beq $s0,'=',showResult
	 
	 # The user did not enter "=", so we ask for a second number 
	 
	 # Asking the user to provide the second number
            li $v0, 4
            la $a0, prompt2
            syscall
             
        # Reading the second number provided
            li $v0, 5          
            syscall 
            move $s2, $v0 
	
	# Checking to see if the second number that the user provided is zero	
		bne $s2, 0, CarryOn
			
			bne $s0, '/', check
			beq $s0, '/', errorProcess2 # if divisor is equal to zero, show certain error
																						
		check: 
			bne $s0, '%', CarryOn
			beq $s0, '%', errorProcess2 # if divisor is equal to zero, show certain error																																																												
	CarryOn:
	
	# Now we execute the appropriate calculation according to the operand provided by the user
	
		beq $s0, '+', additionProcess # addition
		beq $s0, '-', subtractProcess # subtraction
		beq $s0, '*', multiplyProcess # multiplication
		beq $s0, '/', divideProcess   # division
		beq $s0, '%', moduloProcess   # modulo
																																																				
        	additionProcess:
            		add $s1, $s1, $s2     # This adds the values stored in $s1 and $s2 and assigns them to the temporary register $s3
            		j storeResult 		  # Request another operator
            
        	subtractProcess:
            		sub $s1, $s1, $s2         # This subtracts the values stored in $s1 and $s2 and assigns them to the temporary register $s3   	      
   	    		j storeResult 		      # Request another operator
   		
        	multiplyProcess:
            		mul $s1, $s1, $s2      # This multiplies the values stored in $s1 and $s2 and assigns them to the temporary register $s3   
            		j storeResult		  # Request another operator
            
        	divideProcess:		 
            		div  $s1, $s1, $s2		  # This divides the values stored in $s1 and $s2 and assigns them to the temporary register $s3
            		j storeResult		      # Request another operator
                    
        	moduloProcess:
	    		rem  $s1, $s1, $s2     	   # $s3 = $s1 % $t2     	
            		j storeResult		   # Request another operator
        
        storeResult:
        	sb $s1, res
        	j Loop      # for the loop      
                                            
        showResult:
        	la $a0, result
        	li $v0, 4	
        	syscall
        	
        	lb $a0, res
		li $v0,1
		syscall

        	j Question		      # Ask the user if he/she wishes to continue
      	 	
Question:
	# Asking the user if he/she wishes to continue
	la $a0, qsn
      	li $v0, 4
      	syscall
      	
	# Reading his/hers answer
      	li $v0, 12
      	syscall
      	move $s4,$v0
      		
      	beq $s4, 'Y', Continue  # if he/she answered yes goTo Continue
      	j exit	   	 	# exit the program
      	
Continue:
	# Print two blank lines
	li $v0, 4
	la $a0, CR 
	syscall
	j main    # Go to main

errorProcess1:
	li $v0, 4
	la $a0, error1      
	syscall
	j exit
	
errorProcess2:
	li $v0, 4
      	la $a0, error2      
        syscall
	j exit 
        	
              	
exit:	
	li $v0, 10
	syscall

        	        	
.data
    result:	.asciiz "\nThe result is: "
    error1:	.asciiz "\nError. Invalid Operator.\n" 
    error2:	.asciiz "\nError. Division with zero is not allowed.\n"                
    prompt1:    .asciiz "\nNumber: "
    prompt2:    .asciiz "\nNumber: "
    qsn:        .asciiz "\nDo you wish to continue? (Y or N)"
    symbol:	.asciiz	"Operator: "
    ans:	.byte ' '
    oper:	.byte ' '
    res:        .align 2 
    CR:		.asciiz "\n\n"
         
