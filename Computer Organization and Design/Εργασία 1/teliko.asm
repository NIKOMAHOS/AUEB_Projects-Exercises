#GERASIMOS KATSOURIS
#p3210072	
	
	
	
	
	
	
	.text
	.globl main
main:

	#ektupwnvw mhnuma gia arithmo
	la $a0,arithmos
	li $v0,4
	syscall
	
	#diavazw ton prwto arithmo
	li $v0,5
	syscall
	move $s1,$v0

	op:
	
		#ektypwnw mhnuma gia ton telesti
		la $a0,telestis
		li $v0,4
		syscall
		
		#diavasma telesti
		li $v0,12
		syscall
		move $s0,$v0
		
		
		#elegxos gia ton swsto telesti
		beq $s0,'+',arithmos2
		beq $s0,'-',arithmos2
		beq $s0,'*',arithmos2
		beq $s0,'/',arithmos2
		beq $s0,'%',arithmos2
		beq $s0,'=',arithmos2
		
		#periptosi  poy den einai swstos o telestis
		la $a0,lathostelestis
		li $v0,4
		syscall
		
		li $v0,10
		syscall
		
		
	arithmos2:
		
		beq $s0,'=',ison
		
		#estw oti o telestis den einai ison zhtaw deytero arithmo
		la $a0,arithmos
		li $v0,4
		syscall
		
		li $v0,5
		syscall
		move $s2,$v0 
		
		
		
		#elegxos an o deyteros arithmos einai 0
		
		bne $s2,0,sinexise
		
		
			
			bne $s0,'/',elegxos2
			beq $s0,'/',eginelathos
			
			elegxos2:
			
			bne $s0,'%',sinexise
			beq $s0,'%',eginelathos
			
		eginelathos:
				la $a0,lathosdivme0
				li $v0,4
				syscall
			
				li $v0,10
				syscall
		
		sinexise:
		
		#proxwrame stis praxeis analoga ton telesti
		
		
		beq $s0,'+',prosthesi
		beq $s0,'-',afairesi
		beq $s0,'*',pollaplasiasmos
		beq $s0,'/',diairesi
		beq $s0,'%',akerdiair
		
		
		prosthesi:
		add $s1,$s1,$s2
		j isotita
		
		
		afairesi:
		sub $s1,$s1,$s2
		j isotita
		
		
		pollaplasiasmos:
		mul $s1,$s1,$s2
		j isotita
		
		diairesi:
		div $s1,$s1,$s2
		j isotita
		
		akerdiair:
		rem $s1,$s1,$s2
		j isotita
		
		
		
		isotita:
		
		sb $s1,A
		
		#gia tin epanalipsi
		j op
		
		#otan o telestis einai ison
		ison:
		
		la $a0,apotelesma
		li $v0,4
		syscall
		
		lb $a0,A
		li $v0,1
		syscall
		
		la $a0,erotisi
		li $v0,4
		syscall
		
		li $v0,12
		syscall
		move $s4,$v0
		
		
		#epanalipsi i telos
		beq $s4,'y',main
		beq $s4,'n',exit
		
		exit:
			
			li $v0,10
			syscall
			
		
		
			
		
			
		
		

	
	.data
	
arithmos:.asciiz"Number:"
telestis:.asciiz"Operator:"
lathostelestis:.asciiz"Error.Invalid operator.\n"
lathosdivme0:.asciiz"Error.Divided by zero.\n"
apotelesma:.asciiz"The result is:"
erotisi:.asciiz"Do you want to continue with a new expression?(y/n)"
A: .byte
