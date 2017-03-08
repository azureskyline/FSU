;//AustriaCatherine_L010.asm - declarations of data types and arrays
;//L010 Wed Feb 22, 2017

.386
.model flat,stdcall
.stack 4096
ExitProcess proto, dwExitCode:dword

;// Four symbolic constants
	consthex = 14h
	constdeci = 20d
	constoctal = 24o
	constbin = 10100b

;// A bunch of data
.data
	;//indents make Steve happy
	val_byte BYTE 2
	val_sbyte SBYTE -12
	val_word WORD 12345
	val_sword SWORD -12345
	val_dword DWORD 12345678h
	val_sdword SDWORD -12345678
	val_real4 REAL4 -2.1
	myname BYTE "CatherineAustria",0

;// Arrays
	val_arr DWORD 60 DUP(?)
	val_arr2 DWORD 60 DUP(0abcdh)

.code
main proc
	mov	eax,consthex
	mov ebx,constdeci
	mov ecx,constoctal
	mov edx,constbin
	mov bl, val_byte  
	mov bh, val_sbyte
	mov cx, val_word
	mov ax, val_sword
	mov ebx, val_dword
	mov edx, val_sdword
	mov eax, val_real4

	invoke ExitProcess,0
main endp
end main